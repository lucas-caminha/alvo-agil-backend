package br.com.ucsal.meta.agil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.dto.alvo.AlvoAvaliacaoDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoCamadaDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoPerguntaDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoTemaDTO;
import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.RespostaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.AvaliacaoRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private TimeService timeService;
	@Autowired
	private AplicacaoService aplicacaoService;
	@Autowired
	private RespostaService respostaService;
	
	public List<AvaliacaoEntity> getAllAvaliacoes() {
		return avaliacaoRepository.findAll();
	}
	
	public AvaliacaoEntity save(AvaliacaoEntity avaliacao) {
		Optional<AvaliacaoEntity> find = avaliacaoRepository.findByNmAvaliacao(avaliacao.getNmAvaliacao());
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.AVALIACAO_EXISTENTE);
		}
		
		TimeEntity time = timeService.buscaTimePorId(avaliacao.getTime().getCdTime().intValue());
		avaliacao.setTime(time);
		
		AplicacaoEntity aplicacao = aplicacaoService.buscaAplicacaoPorId(avaliacao.getAplicacao().getCdAplicacao().intValue());
		avaliacao.setAplicacao(aplicacao);
		
		return avaliacaoRepository.save(avaliacao);
	}
	
	
	public AvaliacaoEntity atualiza(AvaliacaoEntity avaliacao) {

		Optional<AvaliacaoEntity> find = avaliacaoRepository.findById(avaliacao.getCdAvaliacao());
		if (find.isPresent()) {
			find.get().setNmAvaliacao(avaliacao.getNmAvaliacao());
			find.get().setNotaAvaliacao(avaliacao.getNotaAvaliacao());
			find.get().setFlAvaliacao(avaliacao.getFlAvaliacao());
			find.get().setTime(avaliacao.getTime());
			find.get().setAplicacao(avaliacao.getAplicacao());
			//find.get().setDtAvaliacao(avaliacao.getDtAvaliacao());
			AvaliacaoEntity updated = avaliacaoRepository.save(find.get());
			return updated;
		}

		throw new NotFoundException(MessageUtil.AVALIACAO_NAO_ENCONTRADA);
	}
	
	
	public AvaliacaoEntity deleta(Integer id) {

		Long cdAvaliacao = Long.parseLong(id.toString());
		Optional<AvaliacaoEntity> avaliacao = avaliacaoRepository.findById(cdAvaliacao);
		if (avaliacao.isPresent()) {
			avaliacaoRepository.delete(avaliacao.get());
			return avaliacao.get();
		}

		throw new NotFoundException(MessageUtil.AVALIACAO_NAO_ENCONTRADA);
	}

	public AvaliacaoEntity getCamada(Long cdAvaliacao) {
		Optional<AvaliacaoEntity> avaliacao = avaliacaoRepository.findById(cdAvaliacao);
		if(avaliacao.isEmpty()) {
			return null;
		}
		return avaliacao.get();
	}
	
	public AvaliacaoEntity buscaAvaliacaoPorId(Integer cdAvaliacao) {
		Long cdAvaliacaoL = Long.parseLong(cdAvaliacao.toString());
		Optional<AvaliacaoEntity> avaliacao = avaliacaoRepository.findById(cdAvaliacaoL);
		if(avaliacao.isPresent()) {
			return avaliacao.get();
		}
		throw new NotFoundException(MessageUtil.AVALIACAO_NAO_ENCONTRADA);
	}

	public List<AvaliacaoEntity> buscaAvaliacaoPorTime(Integer cdTime) {
		
		TimeEntity time = new TimeEntity();
		time.setCdTime(cdTime.longValue());
		
		Optional<List<AvaliacaoEntity>> avaliacoesPorTime = avaliacaoRepository.findByTime(time);		
		if(avaliacoesPorTime.isPresent()) {
			return avaliacoesPorTime.get();
		}
		
		throw new NotFoundException(MessageUtil.AVALIACAO_NAO_ENCONTRADA);
	}

	public AlvoAvaliacaoDTO avaliacaoEntityToAlvoAvaliacaoDTO(AvaliacaoEntity entity) {
		AlvoAvaliacaoDTO alvo = new AlvoAvaliacaoDTO();
		alvo.setCdAvaliacao(entity.getCdAvaliacao().intValue());
		alvo.setCdAplicacao(entity.getAplicacao().getCdAplicacao().intValue());
		alvo.setCdTime(entity.getTime().getCdTime().intValue());
		alvo.setDtAvaliacao(entity.getDtAvaliacao().toString());
		alvo.setLabel(entity.getNmAvaliacao());	
		alvo.setChildren(camadaEntityToAlvoCamadaDTO(entity.getAplicacao().getCamadas(), entity));
		alvo.setNotaTotal(entity.getNotaAvaliacao());
		return alvo;
	}

	private ArrayList<AlvoCamadaDTO> camadaEntityToAlvoCamadaDTO(List<CamadaEntity> camadas, AvaliacaoEntity avaliacao) {
		
		ArrayList<AlvoCamadaDTO> camadasAlvo = new ArrayList<AlvoCamadaDTO>();
		
		for(CamadaEntity c : camadas) {
			AlvoCamadaDTO camadaDTO = new AlvoCamadaDTO();
			camadaDTO.setLabel(c.getNmCamada());
			camadaDTO.setChildren(temaEntityToAlvoTemaDTO(c.getTemas(), avaliacao));		
			camadasAlvo.add(camadaDTO);
		}
		
		return camadasAlvo;
	}

	private ArrayList<AlvoTemaDTO> temaEntityToAlvoTemaDTO(List<TemaEntity> temas, AvaliacaoEntity avaliacao) {

		 ArrayList<AlvoTemaDTO> temasAlvo = new ArrayList<AlvoTemaDTO>();
		 
		 for(TemaEntity t : temas) {
			 AlvoTemaDTO temaDTO = new AlvoTemaDTO();
			 temaDTO.setLabel(t.getNmTema());
			 temaDTO.setChildren(perguntaEntityToAlvoPerguntaDTO(t.getPerguntas(), avaliacao));
			 temasAlvo.add(temaDTO);
		 }
		
		return temasAlvo;
	}

	private ArrayList<AlvoPerguntaDTO> perguntaEntityToAlvoPerguntaDTO(List<PerguntaEntity> perguntas, AvaliacaoEntity avaliacao) {

		ArrayList<AlvoPerguntaDTO> perguntasAlvo = new ArrayList<AlvoPerguntaDTO>();
		
		for(PerguntaEntity p : perguntas) {
			AlvoPerguntaDTO perguntaDTO = new AlvoPerguntaDTO();
			perguntaDTO.setCdPergunta(p.getCdPergunta().intValue());
			perguntaDTO.setLabel(p.getDescPergunta());
			perguntaDTO.setPeso(p.getPeso());
			RespostaEntity resposta = respostaService.buscaRespostasByAvaliacaoAndPergunta(avaliacao, p);
			perguntaDTO.setScore(resposta.getNota());
			perguntasAlvo.add(perguntaDTO);
		}
		
		return perguntasAlvo;
	}
	
}
