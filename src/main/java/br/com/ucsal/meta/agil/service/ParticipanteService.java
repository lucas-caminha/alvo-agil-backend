package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.ParticipanteRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class ParticipanteService {
	
	@Autowired
	private ParticipanteRepository participanteRepository;

	//private TimeService timeService;

	public List<ParticipanteEntity> getAllParticipantes() {
		return participanteRepository.findAll();
	}

	public ParticipanteEntity save(ParticipanteEntity participante) {
		return participanteRepository.save(participante);
	}

	public ParticipanteEntity atualiza(ParticipanteEntity participante) {

		Optional<ParticipanteEntity> find = participanteRepository.findById(participante.getCdParticipante());
		if (find.isPresent()) {
			find.get().setNmParticipante(participante.getNmParticipante());
			find.get().setFlParticipante(participante.getFlParticipante());
			find.get().setDtInicioParticipante(participante.getDtInicioParticipante());
			find.get().setDtFimParticipante(participante.getDtFimParticipante());
			if(participante.getTime() != null) {
				find.get().setTime(participante.getTime());
			}			
			ParticipanteEntity updated = participanteRepository.save(find.get());
			return updated;
		}

		throw new NotFoundException(MessageUtil.PARTICIPANTE_NAO_ENCONTRADO);
	}

	public ParticipanteEntity deleta(Integer id) {

		Long cdParticipante = Long.parseLong(id.toString());
		Optional<ParticipanteEntity> participante = participanteRepository.findById(cdParticipante);
		if (participante.isPresent()) {
			participanteRepository.delete(participante.get());
			return participante.get();
		}

		throw new NotFoundException(MessageUtil.PARTICIPANTE_NAO_ENCONTRADO);
	}
	
	public ParticipanteEntity buscaParticipantePorId(Integer cdParticipante) {
		Long cdParticipanteL = Long.parseLong(cdParticipante.toString());
		Optional<ParticipanteEntity> participante = participanteRepository.findByCdParticipante(cdParticipanteL);
		if(participante.isPresent()) {
			return participante.get();
		}
		throw new NotFoundException(MessageUtil.PARTICIPANTE_NAO_ENCONTRADO);
	}
	
}