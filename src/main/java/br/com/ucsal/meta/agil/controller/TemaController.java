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
import br.com.ucsal.meta.agil.dto.TemaDTO;
import br.com.ucsal.meta.agil.entity.TemaEntity;
import br.com.ucsal.meta.agil.service.TemaService;

@RestController
@RequestMapping("/tema")
public class TemaController {
	@Autowired
	private TemaService TemaService;

	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<TemaEntity>> getAllTemas() {
		List<TemaEntity> Temas = TemaService.getAllTemas();
		if (Temas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(Temas);
	}

@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
public ResponseEntity<TemaEntity> addTema(@RequestBody TemaDTO dto) {
	TemaEntity entity = dto.toEntity();
	entity.setCdTema(null);
	TemaEntity saved = TemaService.save(entity);
	if (saved.getCdTema() == null) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	return ResponseEntity.status(HttpStatus.CREATED).body(saved);
}
@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
public ResponseEntity<TemaEntity> atualizaTema(@RequestBody TemaDTO dto) {
	TemaEntity entity = dto.toEntity();
	TemaEntity updated = TemaService.atualiza(entity);
	if (updated == null) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}
	return ResponseEntity.status(HttpStatus.OK).body(updated);
}
@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
public ResponseEntity<TemaEntity> deleteTema(@PathVariable(name = "id") Integer id) {
	TemaEntity deleted = TemaService.delete(id);
	if (deleted == null) {
		return ResponseEntity.noContent().build();
	}
	return ResponseEntity.status(HttpStatus.OK).body(deleted);
}
}

