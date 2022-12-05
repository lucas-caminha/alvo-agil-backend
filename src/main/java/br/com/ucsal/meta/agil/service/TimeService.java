package br.com.ucsal.meta.agil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.TimeRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class TimeService {
	
	@Autowired
	private TimeRepository timeRepository;
	@Autowired
	private FrameworkService frameworkService;
	@Autowired
	private TecnologiaService tecnologiaService;
	@Autowired
	private CerimoniaService cerimoniaService;
	@Autowired
	private ParticipanteService participanteService;
	
	
	public List<TimeEntity> getAllTimes() {
		return timeRepository.findAll();
	}

	public TimeEntity save(TimeEntity time) {
		Optional<TimeEntity> find = timeRepository.findByNmTime(time.getNmTime());	
		if(find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.TIME_EXISTENTE);
		}
		
		time.setTecnologias(getTecnologias(time));
		time.setCerimonias(getCerimonias(time));
		time.setFramework(getFramework(time));
		time.setParticipantes(getParticipantes(time));
		
		return timeRepository.save(time);
	}

	public TimeEntity atualiza(TimeEntity time) {	
		Optional<TimeEntity> find = timeRepository.findById(time.getCdTime());	
		
		if(find.isPresent()) {	
			find.get().setNmTime(time.getNmTime());
			find.get().setFlTime(time.getFlTime());
			find.get().setDtInicioTime(time.getDtInicioTime());
			find.get().setDtFinalizacaoTime(time.getDtFinalizacaoTime());
			find.get().setCerimonias(getCerimonias(time));			
			find.get().setFramework(getFramework(time));			
			find.get().setTecnologias(getTecnologias(time));
			time.setCerimonias(find.get().getCerimonias());
			time.setTecnologias(find.get().getTecnologias());
			removeParticipantesDoTime(find.get());
			find.get().setParticipantes(getParticipantesEAtualiza(time));
			TimeEntity updated = timeRepository.save(find.get());
			
			return updated;
		}
		
		throw new NotFoundException(MessageUtil.TIME_NAO_ENCONTRADO);
	}

	@Transactional
	public TimeEntity deleta(Integer id)  {		
		Long cdTime = Long.parseLong(id.toString());
		Optional<TimeEntity> time = timeRepository.findById(cdTime);
		if(time.isPresent()) {
			removeTimeDeParticipante(time.get());
			timeRepository.delete(time.get());
			return time.get();
		}
		
		throw new NotFoundException(MessageUtil.TIME_NAO_ENCONTRADO);
	}
	
	/** Remove participantes existentes no time **/
	private void removeParticipantesDoTime(TimeEntity time) {		
		for(ParticipanteEntity p : time.getParticipantes()) {
			ParticipanteEntity participante = participanteService.buscaParticipantePorId(p.getCdParticipante().intValue());
			participante.setTime(null);
			participanteService.atualiza(participante);
		}	
	}
	
	private List<ParticipanteEntity> removeTimeDeParticipante(TimeEntity time) {
		List<ParticipanteEntity> participantes = getParticipantesEAtualiza(time);
		for(ParticipanteEntity e : participantes) {
			e.setTime(null);
			participanteService.atualiza(e);
		}
		return participantes;
	}
	
	private List<ParticipanteEntity> getParticipantes(TimeEntity time) {
		List<ParticipanteEntity> participantes = new ArrayList<>();
		for(ParticipanteEntity pt : time.getParticipantes()) {
			ParticipanteEntity participante = participanteService.buscaParticipantePorId(pt.getCdParticipante().intValue());
			participante.setTime(time);
			participantes.add(participante);
		}
		return participantes;
	}
	
	private List<ParticipanteEntity> getParticipantesEAtualiza(TimeEntity time) {
		List<ParticipanteEntity> participantes = new ArrayList<>();
		for(ParticipanteEntity pt : time.getParticipantes()) {
			ParticipanteEntity participante = participanteService.buscaParticipantePorId(pt.getCdParticipante().intValue());
			participante.setTime(time);
			participanteService.atualiza(participante);
			participantes.add(participante);
		}
		return participantes;
	}

	private List<TecnologiaEntity> getTecnologias(TimeEntity time) {
		List<TecnologiaEntity> tecnologias = new ArrayList<>();
		for(TecnologiaEntity tec : time.getTecnologias()) {
			TecnologiaEntity tecnologia = tecnologiaService.getTecnologia(tec.getCdTecnologia());
			tecnologias.add(tecnologia);
		}
		return tecnologias;
	}
		
	private List<CerimoniaEntity> getCerimonias(TimeEntity time) {
		List<CerimoniaEntity> cerimonias = new ArrayList<>();
		for(CerimoniaEntity ceri : time.getCerimonias()) {
			CerimoniaEntity cerimonia = cerimoniaService.getCerimonia(ceri.getCdCerimonia());
			cerimonias.add(cerimonia);
		}
		return cerimonias;
	}
	
	private FrameworkEntity getFramework(TimeEntity time) {
		if(time.getFramework() == null) {
			return null;
		}
		if(time.getFramework().getCdFramework() != null) {
			FrameworkEntity framework = frameworkService.getFramework(time.getFramework().getCdFramework());
			return framework;
		}
		return null;
	}

	public TimeEntity buscaTimePorId(Integer cdTime) {
		Long cdTimeL = Long.parseLong(cdTime.toString());
		Optional<TimeEntity> time = timeRepository.findById(cdTimeL);
		if(time.isPresent()) {
			return time.get();
		}
		throw new NotFoundException(MessageUtil.TIME_NAO_ENCONTRADO);
	}
	

}
