package br.com.ucsal.meta.agil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ucsal.meta.agil.entity.CamadaEntity;

@Repository
public interface CamadaRepository extends JpaRepository<CamadaEntity, Long> {
	
	Optional<CamadaEntity> findByNmCamada(String nmCerimonia);
	
	Optional<CamadaEntity> findById(Long id);

}
