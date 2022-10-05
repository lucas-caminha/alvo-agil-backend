package br.com.ucsal.meta.agil.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import br.com.ucsal.meta.agil.dto.CerimoniaDTO;
import br.com.ucsal.meta.agil.dto.FrameworkDTO;
import br.com.ucsal.meta.agil.dto.PerguntaDTO;
import br.com.ucsal.meta.agil.dto.TecnologiaDTO;
import br.com.ucsal.meta.agil.dto.TimeDTO;

/**
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/scripts/time-data.sql"})
@Sql({"/scripts/framework-data.sql"})
@Sql({"/scripts/tecnologia-data.sql"})
@Sql({"/scripts/cerimonia-data.sql"})
@Sql({"/scripts/pergunta-data.sql"})
class TimeControllerTest {

	@Autowired
    private MockMvc mock;
	@Autowired
	private Gson gson;
	
	private List<CerimoniaDTO> cerimonias;
	private FrameworkDTO framework;
	private List<TecnologiaDTO> tecnologias;
	private List<PerguntaDTO> perguntas;
	
	void init() {
		framework = new FrameworkDTO();
		cerimonias = new ArrayList<CerimoniaDTO>();
		tecnologias = new ArrayList<TecnologiaDTO>();
		perguntas = new ArrayList<PerguntaDTO>();
		
		framework.setCdFramework(1L);
		
		CerimoniaDTO c1 = new CerimoniaDTO();
		c1.setCdCerimonia(1L);
		CerimoniaDTO c2 = new CerimoniaDTO();
		c1.setCdCerimonia(2L);
		cerimonias.add(c1);
		cerimonias.add(c2);
		
		PerguntaDTO p1 = new PerguntaDTO();
		p1.setCdPergunta(1L);
		PerguntaDTO p2 = new PerguntaDTO();
		p2.setCdPergunta(2L);
		perguntas.add(p1);
		perguntas.add(p2);
		
		TecnologiaDTO t1 = new TecnologiaDTO();
		TecnologiaDTO t2 = new TecnologiaDTO();
		t1.setCdTecnologia(1L);
		t2.setCdTecnologia(2L);
		tecnologias.add(t1);
		tecnologias.add(t2);
	}
	
	
	@Test
	void deveRetornar201CreatedAoSalvarTimeComNomeNaoExistente() throws Exception {
		init();
		TimeDTO dto = new TimeDTO();
		dto.setNmTime("Time Teste - " + LocalDate.now());
		dto.setFlTime("N");	
		dto.setDtInicioTime(null);
		dto.setFramework(framework);
		dto.setCerimonias(cerimonias);
		dto.setPerguntas(perguntas);
		dto.setTecnologias(tecnologias);
		
		String json = gson.toJson(dto);	
		
		mock.perform(post("/time/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	void deveRetornar409ConflictAoSalvarTimeJaExistente() throws Exception {
		init();
		TimeDTO dto = new TimeDTO();
		dto.setNmTime("Time Obras");
		dto.setFlTime("N");	
		dto.setDtInicioTime("02/10/2022");
		dto.setFramework(framework);
		dto.setCerimonias(cerimonias);
		dto.setPerguntas(perguntas);
		dto.setTecnologias(tecnologias);
		String json = gson.toJson(dto);	
		
		mock.perform(post("/time/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void deveRetornar200OkAoAtualizarTimeJaExistente() throws Exception {
		init();
		TimeDTO dto = new TimeDTO();
		dto.setCdTime(1L);
		dto.setNmTime("Time Obras Atualizado");
		dto.setFlTime("N");	
		dto.setDtInicioTime(null);
		dto.setFramework(framework);
		dto.setCerimonias(cerimonias);
		dto.setPerguntas(perguntas);
		dto.setTecnologias(tecnologias);
		String json = gson.toJson(dto);	
		
		mock.perform(put("/time/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar404NotFoundAoTentarAtualizarTimeNaoExistente() throws Exception {
		init();
		TimeDTO dto = new TimeDTO();
		dto.setCdTime(999L);
		dto.setNmTime("XPTO");
		dto.setFlTime("S");	
		dto.setDtInicioTime(null);
		dto.setFramework(framework);
		dto.setCerimonias(cerimonias);
		dto.setPerguntas(perguntas);
		dto.setTecnologias(tecnologias);
		String json = gson.toJson(dto);	
		
		mock.perform(put("/time/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void deveRetornar200OkAoDeletarTimeExistente() throws Exception {		
		mock.perform(delete("/time/deleta/1"))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar4O4NotFoundAoDeletarTimeNaoExistente() throws Exception {		
		mock.perform(delete("/time/deleta/99"))
				.andExpect(status().isNotFound());
	}
	
	
}
**/