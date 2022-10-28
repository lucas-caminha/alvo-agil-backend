package br.com.ucsal.meta.agil.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import br.com.ucsal.meta.agil.dto.TecnologiaDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/scripts/tecnologia-data.sql"})
public class TecnologiaControllerTest {

	@Autowired
    private MockMvc mock;
	@Autowired
	private Gson gson;
	
	@Test
	void deveRetornar201CreatedAoSalvarTecnologiaComNomeNaoExistente() throws Exception {
		TecnologiaDTO dto = new TecnologiaDTO();
		dto.setNmTecnologia("Tecnologia Testes - " + LocalDate.now());
		dto.setFlTecnologia("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/tecnologia/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	void deveRetornar409ConflictAoSalvarTecnologiaJaExistente() throws Exception {
		TecnologiaDTO dto = new TecnologiaDTO();
		dto.setNmTecnologia("Java");
		dto.setFlTecnologia("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/tecnologia/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void deveRetornar200OkAoAtualizarTecnologiaJaExistente() throws Exception {
		TecnologiaDTO dto = new TecnologiaDTO();
		dto.setCdTecnologia(1L);
		dto.setNmTecnologia("Java Atualizado");
		dto.setFlTecnologia("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/tecnologia/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar404NotFoundAoTentarAtualizarTecnologiaNaoExistente() throws Exception {
		TecnologiaDTO dto = new TecnologiaDTO();
		dto.setCdTecnologia(999L);
		dto.setNmTecnologia("XPTO");
		dto.setFlTecnologia("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/tecnologia/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void deveRetornar200OkAoDeletarTecnologiaExistente() throws Exception {		
		mock.perform(delete("/tecnologia/deleta/6"))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar4O4NotFoundAoDeletarTecnologiaNaoExistente() throws Exception {		
		mock.perform(delete("/tecnologia/deleta/99"))
				.andExpect(status().isNotFound());
	}
	
}
