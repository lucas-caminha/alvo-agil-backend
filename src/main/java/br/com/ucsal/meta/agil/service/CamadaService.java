package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.CamadaRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class CamadaService {

	@Autowired
	private CamadaRepository camadaRepository;
	@Autowired
	private AplicacaoService aplicacaoService;

	public List<CamadaEntity> getAllCamadas() {
		List<CamadaEntity> camadas = camadaRepository.findAll();
		return camadas;
	}

	public CamadaEntity save(CamadaEntity camada) {
		
		Optional<CamadaEntity> find = camadaRepository.findByNmCamada(camada.getNmCamada());
		/**
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.CAMADA_EXISTENTE);
		}
		**/
		
		//AplicacaoEntity aplicacao = aplicacaoService.buscaAplicacaoPorId(camada.getAplicacao().getCdAplicacao().intValue());	
		//camada.setAplicacao(aplicacao);
		
		return camadaRepository.save(camada);
	}

	public CamadaEntity atualiza(CamadaEntity camada) {
		CamadaEntity find = buscaCamadaPorId(camada.getCdCamada().intValue());
		
		//AplicacaoEntity aplicacao = aplicacaoService.buscaAplicacaoPorId(camada.getAplicacao().getCdAplicacao().intValue());	
		
		/**
		if(aplicacao != null) {
			find.setAplicacao(aplicacao);
		}
		**/
				
		if (find != null) {
			find.setNmCamada(camada.getNmCamada());
			find.setFlCamada(camada.getFlCamada());
			//find.setTemas(camada.getTemas());
			CamadaEntity updated = camadaRepository.save(find);
			return updated;
		}
		
		throw new NotFoundException(MessageUtil.CAMADA_NAO_ENCONTRADA);
	}

	public CamadaEntity deleta(Integer id) {
		Long cdCamada = Long.parseLong(id.toString());
		Optional<CamadaEntity> camada = camadaRepository.findById(cdCamada);
		if (camada.isPresent()) {
			camadaRepository.delete(camada.get());
			return camada.get();
		}
		throw new NotFoundException(MessageUtil.CAMADA_NAO_ENCONTRADA);
	}

	public CamadaEntity getCamada(Long cdcamada) {
		Optional<CamadaEntity> camada = camadaRepository.findById(cdcamada);
		if(camada.isEmpty()) {
			return null;
		}
		return camada.get();
	}
	
	public CamadaEntity buscaCamadaPorId(Integer cdCamada) {
		Long cdCamadaL = Long.parseLong(cdCamada.toString());
		Optional<CamadaEntity> camada = camadaRepository.findById(cdCamadaL);
		if(camada.isPresent()) {
			return camada.get();
		}
		throw new NotFoundException(MessageUtil.CAMADA_NAO_ENCONTRADA);
	}
}