package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.CamadaRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class CamadaService {

	@Autowired
	private CamadaRepository camadaRepository;

	public List<CamadaEntity> getAllCamadas() {
		return camadaRepository.findAll();
	}

	public CamadaEntity save(CamadaEntity camada) {
		Optional<CamadaEntity> find = camadaRepository.findByNmCamada(camada.getNmCamada());
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.CAMADA_EXISTENTE);
		}
		return camadaRepository.save(camada);
	}

	public CamadaEntity atualiza(CamadaEntity camada) {

		Optional<CamadaEntity> find = camadaRepository.findById(camada.getCdCamada());
		if (find.isPresent()) {
			find.get().setNmCamada(camada.getNmCamada());
			find.get().setFlCamada(camada.getFlCamada());
			find.get().setTemas(camada.getTemas());
			CamadaEntity updated = camadaRepository.save(find.get());
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
}