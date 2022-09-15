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
import br.com.ucsal.meta.agil.dto.CerimoniaDTO;
import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.service.CerimoniaService;

@RestController
@RequestMapping("/cerimonia")
public class CerimoniaController {

	@Autowired
	private CerimoniaService cerimoniaService;

	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<CerimoniaEntity>> getAllCerimonias() {
		List<CerimoniaEntity> Cerimonias = cerimoniaService.getAllCerimonias();
		if (Cerimonias.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(Cerimonias);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<CerimoniaEntity> addCerimonia(@RequestBody CerimoniaDTO dto) {
		CerimoniaEntity entity = dto.toEntity();
		entity.setCdCerimonia(null);
		CerimoniaEntity saved = cerimoniaService.save(entity);
		if (saved.getCdCerimonia() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<CerimoniaEntity> atualizaCerimonia(@RequestBody CerimoniaDTO dto) {
		CerimoniaEntity entity = dto.toEntity();
		CerimoniaEntity updated = cerimoniaService.atualiza(entity);
		if (updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<CerimoniaEntity> deleteCerimonia(@PathVariable(name = "id") Integer id) {
		CerimoniaEntity deleted = cerimoniaService.deleta(id);
		if (deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
}