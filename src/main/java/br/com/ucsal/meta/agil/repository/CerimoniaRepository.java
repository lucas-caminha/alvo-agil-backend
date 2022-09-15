package br.com.ucsal.meta.agil.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ucsal.meta.agil.entity.CerimoniaEntity;

@Repository
public interface CerimoniaRepository extends JpaRepository<CerimoniaEntity, Long> {
	Optional<CerimoniaEntity> findByNmCerimonia(String nmCerimonia);

}
