package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.AvaliacaoRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	public List<AvaliacaoEntity> getAllAvaliacoes() {
		return avaliacaoRepository.findAll();
	}
	
	public AvaliacaoEntity save(AvaliacaoEntity avaliacao) {
		Optional<AvaliacaoEntity> find = avaliacaoRepository.findByNmAvaliacao(avaliacao.getNmAvaliacao());
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.AVALIACAO_EXISTENTE);
		}
		return avaliacaoRepository.save(avaliacao);
	}
	
	/**
	public AvaliacaoEntity atualiza(AvaliacaoEntity avaliacao) {

		Optional<AvaliacaoEntity> find = avaliacaoRepository.findById(avaliacao.getCdAvaliacao());
		if (find.isPresent()) {
			find.get().setNmAvaliacao(avaliacao.getNmAvaliacao());
			find.get().setFlAvaliacao(avaliacao.getFlAvaliacao());
			AvaliacaoEntity updated = avaliacaoRepository.save(find.get());
			return updated;
		}

		throw new NotFoundException(MessageUtil.CAMADA_NAO_ENCONTRADA);
	}
	**/
	
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
}
