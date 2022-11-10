package br.com.ucsal.meta.agil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.entity.FuncaoEntity;

public interface FuncaoRepository extends JpaRepository<FuncaoEntity, Long>{

	Optional<List<FuncaoEntity>> findByNmFuncao(String nmFuncao);
	
	Optional<FuncaoEntity> findById(Long id);
	
}
