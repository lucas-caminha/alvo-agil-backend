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
import br.com.ucsal.meta.agil.dto.CamadaDTO;
import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.service.CamadaService;

@RestController
@RequestMapping("/camada")
public class CamadaController {

	@Autowired
	private CamadaService camadaService;

	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<CamadaEntity>> getAllQuadranteAgeis() {
		List<CamadaEntity> camadas = camadaService.getAllCamadas();
		if (camadas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(camadas);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<CamadaEntity> addCamada(@RequestBody CamadaDTO dto) {
		CamadaEntity entity = dto.toEntity();
		entity.setCdCamada(null);
		CamadaEntity saved = camadaService.save(entity);
		if (saved.getCdCamada() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<CamadaEntity> atualizaCamada(@RequestBody CamadaDTO dto) {
		CamadaEntity entity = dto.toEntity();
		CamadaEntity updated = camadaService.atualiza(entity);
		if (updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<CamadaEntity> deleteCamada(@PathVariable(name = "id") Integer id) {
		CamadaEntity deleted = camadaService.deleta(id);
		if (deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
}