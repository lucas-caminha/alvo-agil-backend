package br.com.ucsal.meta.agil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long> {

	Optional<AvaliacaoEntity> findByNmAvaliacao(String nmAvaliacao);
	
	Optional<AvaliacaoEntity> findById(Long id);

	Optional<List<AvaliacaoEntity>> findByTime(TimeEntity time);
	
}
