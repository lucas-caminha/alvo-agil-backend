package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.TimeEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.TimeRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class TimeService {
	
	@Autowired
	private TimeRepository timeRepository;
	
	public List<TimeEntity> getAllTimes() {
		return timeRepository.findAll();
	}

	public TimeEntity save(TimeEntity time) {
		Optional<TimeEntity> find = timeRepository.findByNmTime(time.getNmTime());	
		if(find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.TIME_EXISTENTE);
		}
		return timeRepository.save(time);
	}

	public TimeEntity atualiza(TimeEntity time) {
		
		Optional<TimeEntity> find = timeRepository.findById(time.getCdTime());	
		if(find.isPresent()) {
		
			find.get().setNmTime(time.getNmTime());
			find.get().setFlTime(time.getFlTime());
			find.get().setDtInicioTime(time.getDtInicioTime());
			find.get().setCerimonias(time.getCerimonias());
			find.get().setFramework(time.getFramework());
			find.get().setTecnologias(time.getTecnologias());
			find.get().setPerguntas(time.getPerguntas());
			TimeEntity updated = timeRepository.save(find.get());
			return updated;
		}
		
		throw new NotFoundException(MessageUtil.TIME_NAO_ENCONTRADO);
	}

	public TimeEntity deleta(Integer id)  {
		
		Long cdTime = Long.parseLong(id.toString());
		Optional<TimeEntity> Time = timeRepository.findById(cdTime);
		if(Time.isPresent()) {
			timeRepository.delete(Time.get());
			return Time.get();
		}
		
		throw new NotFoundException(MessageUtil.TIME_NAO_ENCONTRADO);
	}

	

}
