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

import br.com.ucsal.meta.agil.dto.FuncaoDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/scripts/funcao-data.sql"})
class FuncaoControllerTest {
	
	@Autowired
    private MockMvc mock;
	@Autowired
	private Gson gson;
	
	@Test
	void deveRetornar201CreatedAoSalvarFuncaoComNomeNaoExistente() throws Exception {
		FuncaoDTO dto = new FuncaoDTO();
		dto.setNmFuncao("Funcao Teste - " + LocalDate.now());
		dto.setFlFuncao("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/funcao/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	void deveRetornar409ConflictAoSalvarFuncaoJaExistente() throws Exception {
		FuncaoDTO dto = new FuncaoDTO();
		dto.setNmFuncao("Software Developer III");
		dto.setFlFuncao("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/funcao/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void deveRetornar200OkAoAtualizarFuncaoJaExistente() throws Exception {
		FuncaoDTO dto = new FuncaoDTO();
		dto.setCdFuncao(1L);
		dto.setNmFuncao("Java Atualizado");
		dto.setFlFuncao("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/funcao/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar404NotFoundAoTentarAtualizarFuncaoNaoExistente() throws Exception {
		FuncaoDTO dto = new FuncaoDTO();
		dto.setCdFuncao(999L);
		dto.setNmFuncao("XPTO");
		dto.setFlFuncao("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/funcao/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void deveRetornar200OkAoDeletarFuncaoExistente() throws Exception {		
		mock.perform(delete("/funcao/deleta/6"))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar4O4NotFoundAoDeletarFuncaoNaoExistente() throws Exception {		
		mock.perform(delete("/funcao/deleta/99"))
				.andExpect(status().isNotFound());
	}

}
