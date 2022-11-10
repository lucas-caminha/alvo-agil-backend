package br.com.ucsal.meta.agil.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ucsal.meta.agil.entity.TemaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

@Repository
public interface TemaRepository extends JpaRepository<TemaEntity, Long> {
	
	Optional<TemaEntity> findByNmTema (String nmTema);
	
	Optional<TemaEntity> findById(Long id);
	
}
