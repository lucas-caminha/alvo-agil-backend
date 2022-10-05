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

import br.com.ucsal.meta.agil.dto.TecnologiaDTO;
import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.service.TecnologiaService;

@RestController
@RequestMapping("/tecnologia")
public class TecnologiaController {
	
	@Autowired
	private TecnologiaService tecnologiaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<TecnologiaEntity>> getAllTecnologias() {
		List<TecnologiaEntity> tecnologias = tecnologiaService.getAllTecnologias();
		if(tecnologias.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(tecnologias);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<TecnologiaEntity> addTecnologia(@RequestBody TecnologiaDTO dto) {	
		TecnologiaEntity entity = dto.toEntity();
		entity.setCdTecnologia(null);
		TecnologiaEntity saved = tecnologiaService.save(entity);
		if(saved.getCdTecnologia() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<TecnologiaEntity> atualizaTecnologia(@RequestBody TecnologiaDTO dto) {	
		TecnologiaEntity entity = dto.toEntity();
		TecnologiaEntity updated = tecnologiaService.atualiza(entity);
		if(updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<TecnologiaEntity> deleteTecnologia(@PathVariable(name = "id") Integer id) {	
		TecnologiaEntity deleted = tecnologiaService.deleta(id);
		if(deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
	
	
	
	
	
	
	
	
	

}
