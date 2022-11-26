package br.com.ucsal.meta.agil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

public interface ParticipanteRepository extends JpaRepository<ParticipanteEntity, Long>{

	Optional<ParticipanteEntity> findByNmParticipante(String nmParticipante);

	Optional<List<ParticipanteEntity>> findByTime(TimeEntity time);
	
	Optional<ParticipanteEntity> findByCdParticipante(Long cdParticipante);

}
