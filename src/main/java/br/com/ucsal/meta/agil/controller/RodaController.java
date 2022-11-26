package br.com.ucsal.meta.agil.controller;

import java.util.ArrayList;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ucsal.meta.agil.dto.RodaCamadaDTO;
import br.com.ucsal.meta.agil.dto.RodaDTO;
import br.com.ucsal.meta.agil.dto.RodaPerguntaDTO;
import br.com.ucsal.meta.agil.dto.RodaTemaDTO;

@RestController
@RequestMapping("/roda")
public class RodaController {
	
	@Autowired
	private Gson gson;
	
	@RequestMapping(method = RequestMethod.GET, value = "/agil", produces = "application/json")
	public ResponseEntity<String> getAllFuncoes() {
		RodaDTO roda = new RodaDTO();
		
		roda.setLabel("Avaliação 26/11/2022 - Time Obras");
		roda.setDtAvaliacao("2022-11-26");
		
		RodaCamadaDTO camada = new RodaCamadaDTO();
		camada.setLabel("Segurança");
		
		RodaTemaDTO tema = new RodaTemaDTO();
		tema.setLabel("Confidenciabilidade");
		
		RodaPerguntaDTO pergunta = new RodaPerguntaDTO();
		pergunta.setLabel("Time faz reuniões diárias?");
		pergunta.setPeso(5);
		pergunta.setScore(3);
		
		ArrayList<RodaCamadaDTO> camadas = new ArrayList<RodaCamadaDTO>();
		camadas.add(camada);
		
		ArrayList<RodaTemaDTO> temas = new ArrayList<RodaTemaDTO>();
		temas.add(tema);
		
		ArrayList<RodaPerguntaDTO> perguntas = new ArrayList<RodaPerguntaDTO>();
		perguntas.add(pergunta);
		
		tema.setChildren(perguntas);
		camada.setChildren(temas);
		roda.setChildren(camadas);
		
		String json = gson.toJson(roda);
		if(json.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

}
