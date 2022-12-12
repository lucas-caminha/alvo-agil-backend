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

import br.com.ucsal.meta.agil.dto.FrameworkDTO;
import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.service.FrameworkService;

@RestController
@RequestMapping("/framework")
public class FrameworkController {
	
	@Autowired
	private FrameworkService frameworkService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<FrameworkEntity>> getAllFrameworks() {
		List<FrameworkEntity> frameworks = frameworkService.getAllFrameworks();
		if(frameworks.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(frameworks);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<FrameworkEntity> addFramework(@RequestBody FrameworkDTO dto) {	
		FrameworkEntity entity = dto.toEntity();
		entity.setCdFramework(null);
		FrameworkEntity saved = frameworkService.save(entity);
		if(saved.getCdFramework() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<FrameworkEntity> atualizaFramework(@RequestBody FrameworkDTO dto) {	
		FrameworkEntity entity = dto.toEntity();
		FrameworkEntity updated = frameworkService.atualiza(entity);
		if(updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<FrameworkEntity> deleteFramework(@PathVariable(name = "id") Integer id) {	
		FrameworkEntity deleted = frameworkService.deleta(id);
		if(deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/busca/{id}", produces = "application/json")
	public ResponseEntity<FrameworkEntity> getFrameworkById(@PathVariable(name = "id") Integer frameworkId) {	
		FrameworkEntity entity = frameworkService.buscaFrameworkPorId(frameworkId);
		if(entity == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
}
