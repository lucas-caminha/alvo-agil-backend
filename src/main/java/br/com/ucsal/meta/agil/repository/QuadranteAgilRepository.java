package br.com.ucsal.meta.agil.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.entity.QuadranteAgilEntity;

@Repository
public interface QuadranteAgilRepository extends JpaRepository<QuadranteAgilEntity, Long> {
	
	Optional<QuadranteAgilEntity> findByNmQuadrante(String nmCerimonia);

}
