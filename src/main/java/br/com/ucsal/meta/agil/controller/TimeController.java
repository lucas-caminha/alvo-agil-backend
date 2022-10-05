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

import br.com.ucsal.meta.agil.dto.TimeDTO;
import br.com.ucsal.meta.agil.dto.TimeSimpleDTO;
import br.com.ucsal.meta.agil.entity.TimeEntity;
import br.com.ucsal.meta.agil.service.TimeService;

@RestController
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeService timeService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos", produces = "application/json")
	public ResponseEntity<List<TimeEntity>> getAllTimes() {
		List<TimeEntity> times = timeService.getAllTimes();
		if(times.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(times);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
	public ResponseEntity<TimeEntity> addTime(@RequestBody TimeDTO dto) {	
		TimeEntity entity = dto.toEntity();
		entity.setCdTime(null);
		TimeEntity saved = timeService.save(entity);
		if(saved.getCdTime() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/v2/add", produces = "application/json")
	public ResponseEntity<TimeEntity> addTimeSimple(@RequestBody TimeSimpleDTO dto) {	
		TimeEntity entity = dto.toEntity();
		entity.setCdTime(null);
		TimeEntity saved = timeService.save(entity);
		if(saved.getCdTime() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/atualiza", produces = "application/json")
	public ResponseEntity<TimeEntity> atualizaTime(@RequestBody TimeDTO dto) {	
		TimeEntity entity = dto.toEntity();
		TimeEntity updated = timeService.atualiza(entity);
		if(updated == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleta/{id}", produces = "application/json")
	public ResponseEntity<TimeEntity> deleteTime(@PathVariable(name = "id") Integer id) {	
		TimeEntity deleted = timeService.deleta(id);
		if(deleted == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(deleted);
	}
}
