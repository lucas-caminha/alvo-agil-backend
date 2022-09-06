package br.com.ucsal.meta.agil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ucsal.meta.agil.entity.TimeEntity;

public interface TimeRepository extends JpaRepository<TimeEntity, Long>{

}
