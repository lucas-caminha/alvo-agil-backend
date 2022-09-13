package br.com.ucsal.meta.agil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ucsal.meta.agil.entity.TecnologiaEntity;

public interface TecnologiaRepository extends JpaRepository<TecnologiaEntity, Long>{

	Optional<TecnologiaEntity> findByNmTecnologia(String nmTecnologia);

}
