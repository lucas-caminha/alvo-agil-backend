package br.com.ucsal.meta.agil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long> {

	Optional<AvaliacaoEntity> findByNmAvaliacao(String nmAvaliacao);
	
	Optional<AvaliacaoEntity> findById(Long id);
}
