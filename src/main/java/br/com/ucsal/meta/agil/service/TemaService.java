package br.com.ucsal.meta.agil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.TemaRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class TemaService {

	@Autowired
	private TemaRepository temaRepository;
	@Autowired
	private CamadaService camadaService;
	@Autowired
	private PerguntaService perguntaService;

	public List<TemaEntity>getAllTemas(){
		return temaRepository.findAll();
	}
	
	public TemaEntity save (TemaEntity tema) {
		//Optional<TemaEntity> find = temaRepository.findByNmTema(tema.getNmTema());
		/**
		if(find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.TEMA_EXISTENTE);
		}
		**/
		
		CamadaEntity camada = camadaService.buscaCamadaPorId(tema.getCamada().getCdCamada().intValue());	
		tema.setCamada(camada);
		
		ArrayList<TemaEntity> temas = new ArrayList<TemaEntity>();
		temas.add(tema);
		
		if(tema.getPerguntas() != null) {
			for(PerguntaEntity pergunta : tema.getPerguntas()) {
				pergunta.setTemas(temas);
				perguntaService.save(pergunta);
			}
		}
		
		return temaRepository.save(tema);
	}
	
	public TemaEntity atualiza (TemaEntity tema) {
		Optional<TemaEntity> find= temaRepository.findByNmTema(tema.getNmTema());
		if(find.isPresent()) {
			find.get().setNmTema(tema.getNmTema());
			find.get().setFlTema(tema.getFlTema());
			find.get().setCdTema(tema.getCdTema());
			TemaEntity update = temaRepository.save(find.get());
			return update;
		}
		throw new NotFoundException (MessageUtil.TEMA_NAO_ENCONTRADO);
	}

	public TemaEntity delete(Integer id) {

		Long cdTema = Long.parseLong(id.toString());
		Optional<TemaEntity> tema = temaRepository.findById(cdTema);
		if (tema.isPresent()) {
			temaRepository.delete(tema.get());
			return tema.get();
		}

		throw new NotFoundException(MessageUtil.TEMA_NAO_ENCONTRADO);
	}

	public TemaEntity getTema(Long cdTema) {
		Optional<TemaEntity> tema = temaRepository.findById(cdTema);
		if(tema.isEmpty()) {
			return null;
		}
		return tema.get();
	}
	
	public TemaEntity buscaTemaPorId(Integer cdTema) {
		Long cdTemaL = Long.parseLong(cdTema.toString());
		Optional<TemaEntity> tema = temaRepository.findById(cdTemaL);
		if(tema.isPresent()) {
			return tema.get();
		}
		throw new NotFoundException(MessageUtil.TEMA_NAO_ENCONTRADO);
	}
	
	public TemaEntity buscaPorNmTema(String nmTema) {
		Optional<TemaEntity> find = temaRepository.findByNmTema(nmTema);
		if (find.isPresent()) {
			return find.get();
		}
		return null;
	}
	
}




