package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.ParticipanteRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class ParticipanteService {
	
	@Autowired
	private ParticipanteRepository participanteRepository;

	public List<ParticipanteEntity> getAllPartcipante() {
		return participanteRepository.findAll();
	}

	public ParticipanteEntity save(ParticipanteEntity participante) {
		Optional<ParticipanteEntity> find = ParticipanteRepository
				.findByNmParticipante(participante.getNmParticipante());
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.PARTICIPANTE_EXISTENTE);
		}
		return participanteRepository.save(participante);
	}

	public ParticipanteEntity atualiza(ParticipanteEntity participante) {

		Optional<ParticipanteEntity> find = participanteRepository.findById(participante.getCdParticipante());
		if (find.isPresent()) {
			find.get().setNmParticipante(participante.getNmParticipante());
			find.get().setFlParticipante(participante.getFlParticipante());
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
}