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

import br.com.ucsal.meta.agil.dto.PerguntaDTO;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.service.PerguntaService;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

	@Autowired
	private PerguntaService perguntaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<PerguntaEntity>> getAllPerguntas() {
		List<PerguntaEntity> Perguntas = perguntaService.getAllPerguntas();
		if(Perguntas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(Perguntas);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<PerguntaEntity> addPergunta(@RequestBody PerguntaDTO dto) {	
		PerguntaEntity entity = dto.toEntity();
		entity.setCdPergunta(null);
		PerguntaEntity saved = perguntaService.save(entity);
		if(saved.getCdPergunta() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<PerguntaEntity> atualizaPergunta(@RequestBody PerguntaDTO dto) {	
		PerguntaEntity entity = dto.toEntity();
		PerguntaEntity updated = perguntaService.atualiza(entity);
		if(updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<PerguntaEntity> deletePergunta(@PathVariable(name = "id") Integer id) {	
		PerguntaEntity deleted = perguntaService.deleta(id);
		if(deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/busca/{id}", produces = "application/json")
	public ResponseEntity<PerguntaEntity> getPerguntaaById(@PathVariable(name = "id") Integer perguntaId) {	
		PerguntaEntity pergunta = perguntaService.buscaPerguntaPorId(perguntaId);
		if(pergunta == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(pergunta);
	}
}
