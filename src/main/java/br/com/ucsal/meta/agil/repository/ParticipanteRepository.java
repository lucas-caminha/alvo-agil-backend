package br.com.ucsal.meta.agil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ucsal.meta.agil.entity.ParticipanteEntity;

public interface ParticipanteRepository extends JpaRepository<ParticipanteEntity, Long>{

	static Optional<ParticipanteEntity> findByNmParticipante(String nmParticipante) {
		// TODO Auto-generated method stub
		return null;
	}

}
