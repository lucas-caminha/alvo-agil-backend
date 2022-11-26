package br.com.ucsal.meta.agil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ucsal.meta.agil.dto.AvaliacaoDTO;
import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService avaliacaoService;

	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<AvaliacaoEntity>> getAllAvaliacoes() {
		List<AvaliacaoEntity> avaliacoes = avaliacaoService.getAllAvaliacoes();
		if (avaliacoes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(avaliacoes);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<AvaliacaoEntity> addAvaliacao(@RequestBody AvaliacaoDTO dto) {
		AvaliacaoEntity entity = dto.toEntity();
		entity.setCdAvaliacao(null);
		AvaliacaoEntity saved = avaliacaoService.save(entity);
		if (saved.getCdAvaliacao() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<AvaliacaoEntity> deleteAvaliacao(@PathVariable(name = "id") Integer id) {
		AvaliacaoEntity deleted = avaliacaoService.deleta(id);
		if (deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/busca/{id}", produces = "application/json")
	public ResponseEntity<AvaliacaoEntity> getAvaliacaoById(@PathVariable(name = "id") Integer avaliacaoId) {	
		AvaliacaoEntity entity = avaliacaoService.buscaAvaliacaoPorId(avaliacaoId);
		if(entity == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
}