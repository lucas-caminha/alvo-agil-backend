package br.com.ucsal.meta.agil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ucsal.meta.agil.entity.AplicacaoEntity;

@Repository
public interface AplicacaoRepository extends JpaRepository<AplicacaoEntity, Long> {
	
	Optional<AplicacaoEntity> findByNmAplicacao(String nmAplicacao);
		
	Optional<AplicacaoEntity> findById(Long id);
}
