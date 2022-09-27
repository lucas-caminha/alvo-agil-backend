package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.TecnologiaRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class TecnologiaService {
	
	@Autowired
	private TecnologiaRepository tecnologiaRepository;

	public List<TecnologiaEntity> getAllTecnologias() {
		return tecnologiaRepository.findAll();
	}

	public TecnologiaEntity save(TecnologiaEntity tecnologia) {
		Optional<TecnologiaEntity> find = tecnologiaRepository.findByNmTecnologia(tecnologia.getNmTecnologia());	
		if(find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.TECNOLOGIA_EXISTENTE);
		}
		return tecnologiaRepository.save(tecnologia);
	}

	public TecnologiaEntity atualiza(TecnologiaEntity tecnologia) {
		Optional<TecnologiaEntity> find = tecnologiaRepository.findById(tecnologia.getCdTecnologia());	
		if(find.isPresent()) {
			find.get().setNmTecnologia(tecnologia.getNmTecnologia());
			find.get().setFlTecnologia(tecnologia.getFlTecnologia());
			find.get().setTimes(tecnologia.getTimes());
			
			TecnologiaEntity updated = tecnologiaRepository.save(find.get());
			return updated;
		}
		
		throw new NotFoundException(MessageUtil.TECNOLOGIA_NAO_ENCONTRADA);
	}
	

	public TecnologiaEntity deleta(Integer id) {
		Long cdTecnologia = Long.parseLong(id.toString());
		Optional<TecnologiaEntity> tecnologia = tecnologiaRepository.findById(cdTecnologia);
		if(tecnologia.isPresent()) {
			tecnologiaRepository.delete(tecnologia.get());
			return tecnologia.get();
		}
		
		throw new NotFoundException(MessageUtil.TECNOLOGIA_NAO_ENCONTRADA);
	}

	public TecnologiaEntity getTecnologia(Long cdTecnologia) {
		Optional<TecnologiaEntity> tecnologia = tecnologiaRepository.findById(cdTecnologia);
		if(tecnologia.isEmpty()) {
			return null;
		}
		return tecnologia.get();
	}


}
