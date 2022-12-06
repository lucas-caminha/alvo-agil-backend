package br.com.ucsal.meta.agil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.RespostaEntity;

public interface RespostaRepository extends JpaRepository<RespostaEntity, Long> {

	Optional<List<RespostaEntity>> findByAvaliacao(AvaliacaoEntity avaliacao);

	Optional<RespostaEntity> findByAvaliacaoAndPergunta(AvaliacaoEntity avaliacao, PerguntaEntity pergunta);
	
}
