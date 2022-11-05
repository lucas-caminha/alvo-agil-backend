package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.AplicacaoRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class AplicacaoService {
	
	@Autowired
	private AplicacaoRepository aplicacaoRepository;
	@Autowired
	private TimeService timeService;
	
	public List<AplicacaoEntity> getAllAplicacao() {
		return aplicacaoRepository.findAll();
	}
	
	public AplicacaoEntity save(AplicacaoEntity aplicacao) {
		Optional<AplicacaoEntity> find = aplicacaoRepository.findByNmAplicacao(aplicacao.getNmAplicacao());
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.APLICACAO_EXISTENTE);
		}
		return aplicacaoRepository.save(aplicacao);
	}
	
	public AplicacaoEntity atualiza(AplicacaoEntity aplicacao) {

		Optional<AplicacaoEntity> find = aplicacaoRepository.findById(aplicacao.getCdAplicacao());
		if (find.isPresent()) {
			find.get().setNmAplicacao(aplicacao.getNmAplicacao());
			find.get().setFlAplicacao(aplicacao.getFlAplicacao());
			find.get().setCamadas(aplicacao.getCamadas());
			
			AplicacaoEntity updated = aplicacaoRepository.save(find.get());
			return updated;
		}

		throw new NotFoundException(MessageUtil.APLICACAO_NAO_ENCONTRADA);
	}
	
	public AplicacaoEntity deleta(Integer id) {

		Long cdAplicacao = Long.parseLong(id.toString());
		Optional<AplicacaoEntity> aplicacao = aplicacaoRepository.findById(cdAplicacao);
		if (aplicacao.isPresent()) {
			aplicacaoRepository.delete(aplicacao.get());
			return aplicacao.get();
		}

		throw new NotFoundException(MessageUtil.APLICACAO_NAO_ENCONTRADA);
	}
	
	public AplicacaoEntity getAplicacao(Long cdAplicacao) {
		Optional<AplicacaoEntity> aplicacao = aplicacaoRepository.findById(cdAplicacao);
		if(aplicacao.isEmpty()) {
			return null;
		}
		return aplicacao.get();
	}

	/**
	public List<AplicacaoEntity> getAplicacaoPorTime(Integer timeId) {
		Long cdTime = Long.parseLong(timeId.toString());
		TimeEntity time = timeService.buscaTimePorId(cdTime);
		
		Optional<List<AplicacaoEntity>> aplicacoes = aplicacaoRepository.findByTime(time);
		if (aplicacoes.isPresent()) {
			return aplicacoes.get();
		}
		
		throw new NotFoundException(MessageUtil.APLICACAO_NAO_ENCONTRADA);
	}
	 **/
}
