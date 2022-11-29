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

import br.com.ucsal.meta.agil.dto.ParticipanteDTO;
import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.service.ParticipanteService;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

	@Autowired
	private ParticipanteService participanteService;

	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<ParticipanteEntity>> getAllParticipante() {
		List<ParticipanteEntity> participantes = participanteService.getAllParticipantes();

		if (participantes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(participantes);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<ParticipanteEntity> addParticipante(@RequestBody ParticipanteDTO dto) { // dados do front
		ParticipanteEntity entity = dto.toEntity();
		entity.setCdParticipante(null);
		ParticipanteEntity saved = participanteService.save(entity);
		if (saved.getCdParticipante() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<ParticipanteEntity> atualizaParticipante(@RequestBody ParticipanteDTO dto) {
		ParticipanteEntity entity = dto.toEntity();
		ParticipanteEntity updated = participanteService.atualiza(entity);
		if (updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<ParticipanteEntity> deleteParticipante(@PathVariable(name = "id") Integer id) {
		ParticipanteEntity deleted = participanteService.deleta(id);
		if (deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/busca/{id}", produces = "application/json")
	public ResponseEntity<ParticipanteEntity> getParticipanteById(@PathVariable(name = "id") Integer participanteId) {	
		ParticipanteEntity entity = participanteService.buscaParticipantePorId(participanteId);
		if(entity == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
}
