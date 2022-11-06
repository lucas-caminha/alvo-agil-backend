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

import br.com.ucsal.meta.agil.dto.AplicacaoDTO;
import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.service.AplicacaoService;

@RestController
@RequestMapping("/aplicacao")
public class AplicacaoController {
	
	
	@Autowired
	private AplicacaoService aplicacaoService;

	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<AplicacaoEntity>> getAllAplicacao() {
		List<AplicacaoEntity> Aplicacao = aplicacaoService.getAllAplicacao();
		if (Aplicacao.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(Aplicacao);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<AplicacaoEntity> addAplicacao(@RequestBody AplicacaoDTO dto) {
		AplicacaoEntity entity = dto.toEntity();
		entity.setCdAplicacao(null);
		AplicacaoEntity saved = aplicacaoService.save(entity);
		if (saved.getCdAplicacao() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<AplicacaoEntity> atualizaAplicacao(@RequestBody AplicacaoDTO dto) {
		AplicacaoEntity entity = dto.toEntity();
		AplicacaoEntity updated = aplicacaoService.atualiza(entity);
		if (updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<AplicacaoEntity> deleteAplicacao(@PathVariable(name = "id") Integer id) {
		AplicacaoEntity deleted = aplicacaoService.deleta(id);
		if (deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
	
	/**
	@RequestMapping(method = RequestMethod.GET, value = "/time/{id}", produces = "application/json")
	public ResponseEntity<List<AplicacaoEntity>> getAplicacoesPorTime(@PathVariable(name = "id") Integer timeId) {
		List<AplicacaoEntity> aplicacaoPorTime = aplicacaoService.getAplicacaoPorTime(timeId);
		if(aplicacaoPorTime == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(aplicacaoPorTime);
	}
	**/
	
	
	

}
