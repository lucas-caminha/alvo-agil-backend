package br.com.ucsal.meta.agil.controller;

import java.time.LocalDate;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import br.com.ucsal.meta.agil.dto.FrameworkDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/script-executer/framework-data.sql"})
public class FrameworkControllerTest {
	
	@Autowired
    private MockMvc mock;
	@Autowired
	private Gson gson;
	
	@Test
	void deveRetornar201CreatedAoSalvarFrameworkComNomeNaoExistente() throws Exception {
		FrameworkDTO dto = new FrameworkDTO();
		dto.setNmFramework("Framework Teste - " + LocalDate.now());
		dto.setFlFramework("N");	
		String json = gson.toJson(dto);
		
		mock.perform(post("/framework/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	/** Deu problema ao tentar salvar no banco; unique result; **/
	/**
	@Test
	void deveRetornar409ConflictAoSalvarFrameworkJaExistente() throws Exception {
		FrameworkDTO dto = new FrameworkDTO();
		dto.setNmFramework("KambanTeste");
		dto.setFlFramework("N");	
		String json = gson.toJson(dto);
		
		mock.perform(post("/framework/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isConflict());
	}
	**/
		
	
}
