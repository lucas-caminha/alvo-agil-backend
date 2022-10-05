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

import br.com.ucsal.meta.agil.dto.CerimoniaDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/scripts/cerimonia-data.sql"})
public class CerimoniaControllerTest {

	@Autowired
    private MockMvc mock;
	@Autowired
	private Gson gson;
	
	@Test
	void deveRetornar201CreatedAoSalvarCerimoniaComNomeNaoExistente() throws Exception {
		CerimoniaDTO dto = new CerimoniaDTO();
		dto.setNmCerimonia("Cerimonia Testes - " + LocalDate.now());
		dto.setFlCerimonia("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/cerimonia/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	void deveRetornar409ConflictAoSalvarCerimoniaJaExistente() throws Exception {
		CerimoniaDTO dto = new CerimoniaDTO();
		dto.setNmCerimonia("Sprint planning");
		dto.setFlCerimonia("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/cerimonia/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void deveRetornar200OkAoAtualizarCerimoniaJaExistente() throws Exception {
		CerimoniaDTO dto = new CerimoniaDTO();
		dto.setCdCerimonia(1L);
		dto.setNmCerimonia("Sprint planning Atualizado");
		dto.setFlCerimonia("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/cerimonia/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar404NotFoundAoTentarAtualizarCerimoniaNaoExistente() throws Exception {
		CerimoniaDTO dto = new CerimoniaDTO();
		dto.setCdCerimonia(999L);
		dto.setNmCerimonia("XPTO");
		dto.setFlCerimonia("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/cerimonia/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void deveRetornar200OkAoDeletarCerimoniaExistente() throws Exception {		
		mock.perform(delete("/cerimonia/deleta/3"))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar4O4NotFoundAoDeletarCerimoniaNaoExistente() throws Exception {		
		mock.perform(delete("/cerimonia/deleta/99"))
				.andExpect(status().isNotFound());
	}
	

}
