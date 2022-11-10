package br.com.ucsal.meta.agil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ucsal.meta.agil.entity.PerguntaEntity;

public interface PerguntaRepository extends JpaRepository<PerguntaEntity, Long>{

	Optional<List<PerguntaEntity>> findByDescPergunta(String descPergunta);

	Optional<PerguntaEntity> findById(Long id);
	
}
