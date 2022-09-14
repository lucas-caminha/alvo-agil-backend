package br.com.ucsal.meta.agil.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ucsal.meta.agil.entity.FrameworkEntity;

@Repository
public interface FrameworkRepository extends JpaRepository<FrameworkEntity, Long>{

	Optional<FrameworkEntity> findByNmFramework(String nmFramework);

}
