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

import br.com.ucsal.meta.agil.dto.FuncaoDTO;
import br.com.ucsal.meta.agil.entity.FuncaoEntity;
import br.com.ucsal.meta.agil.service.FuncaoService;

@RestController
@RequestMapping("/funcao")
public class FuncaoController {
	
	@Autowired
	private FuncaoService funcaoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<FuncaoEntity>> getAllFuncoes() {
		List<FuncaoEntity> funcoes = funcaoService.getAllFuncoes();
		if(funcoes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(funcoes);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<FuncaoEntity> addFuncao(@RequestBody FuncaoDTO dto) {	
		FuncaoEntity entity = dto.toEntity();
		entity.setCdFuncao(null);
		FuncaoEntity saved = funcaoService.save(entity);
		if(saved.getCdFuncao() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<FuncaoEntity> atualizaFuncao(@RequestBody FuncaoDTO dto) {	
		FuncaoEntity entity = dto.toEntity();
		FuncaoEntity updated = funcaoService.atualiza(entity);
		if(updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<FuncaoEntity> deleteFuncao(@PathVariable(name = "id") Integer id) {	
		FuncaoEntity deleted = funcaoService.deleta(id);
		if(deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/busca/{id}", produces = "application/json")
	public ResponseEntity<FuncaoEntity> getFuncaoById(@PathVariable(name = "id") Integer funcaoId) {	
		FuncaoEntity entity = funcaoService.buscaFuncaoPorId(funcaoId);
		if(entity == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
}
