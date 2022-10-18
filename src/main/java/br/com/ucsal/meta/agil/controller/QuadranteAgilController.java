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
import br.com.ucsal.meta.agil.dto.QuadranteAgilDTO;
import br.com.ucsal.meta.agil.entity.QuadranteAgilEntity;
import br.com.ucsal.meta.agil.service.QuadranteAgilService;

@RestController
@RequestMapping("/quadrante")
public class QuadranteAgilController {

	@Autowired
	private QuadranteAgilService quadranteAgilService;

	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<QuadranteAgilEntity>> getAllQuadranteAgeis() {
		List<QuadranteAgilEntity> quadranteAgeis = quadranteAgilService.getAllQuadranteAgeis();
		if (quadranteAgeis.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(quadranteAgeis);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<QuadranteAgilEntity> addQuadranteAgil(@RequestBody QuadranteAgilDTO dto) {
		QuadranteAgilEntity entity = dto.toEntity();
		entity.setCdQuadrante(null);
		QuadranteAgilEntity saved = quadranteAgilService.save(entity);
		if (saved.getCdQuadrante() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<QuadranteAgilEntity> atualizaQuadranteAgil(@RequestBody QuadranteAgilDTO dto) {
		QuadranteAgilEntity entity = dto.toEntity();
		QuadranteAgilEntity updated = quadranteAgilService.atualiza(entity);
		if (updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<QuadranteAgilEntity> deleteQuadranteAgil(@PathVariable(name = "id") Integer id) {
		QuadranteAgilEntity deleted = quadranteAgilService.deleta(id);
		if (deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
}