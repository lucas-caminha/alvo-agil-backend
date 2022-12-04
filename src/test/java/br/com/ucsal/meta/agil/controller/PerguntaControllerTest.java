package br.com.ucsal.meta.agil.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import br.com.ucsal.meta.agil.dto.PerguntaDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/scripts/pergunta-data.sql"})
class PerguntaControllerTest {

	@Autowired
    private MockMvc mock;
	@Autowired
	private Gson gson;
	
	@Test
	void deveRetornar201CreatedAoSalvarPerguntaComNomeNaoExistente() throws Exception {
		PerguntaDTO dto = new PerguntaDTO();
		dto.setDescPergunta("Pergunta Lero Lero Teste Teste? - ");
		dto.setFlPergunta("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/pergunta/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	/**
	@Test
	void deveRetornar409ConflictAoSalvarPerguntaJaExistente() throws Exception {
		PerguntaDTO dto = new PerguntaDTO();
		dto.setDescPergunta("O cuidado em identificar pontos críticos no consenso sobre a necessidade de qualificação nos obriga à análise das direções preferenciais no sentido do progresso?");
		dto.setFlPergunta("N");	
		String json = gson.toJson(dto);	
		
		mock.perform(post("/pergunta/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());
	}
	**/
	
	@Test
	void deveRetornar200OkAoAtualizarPerguntaJaExistente() throws Exception {
		PerguntaDTO dto = new PerguntaDTO();
		dto.setCdPergunta(1L);
		dto.setDescPergunta("O cuidado em identificar pontos críticos no consenso sobre a necessidade de qualificação nos obriga à análise das direções preferenciais no sentido do progresso? Atualizado");
		dto.setFlPergunta("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/pergunta/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar404NotFoundAoTentarAtualizarPerguntaNaoExistente() throws Exception {
		PerguntaDTO dto = new PerguntaDTO();
		dto.setCdPergunta(999L);
		dto.setDescPergunta("XPTO?");
		dto.setFlPergunta("S");	
		String json = gson.toJson(dto);	
		
		mock.perform(put("/pergunta/atualiza")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void deveRetornar200OkAoDeletarPerguntaExistente() throws Exception {		
		mock.perform(delete("/pergunta/deleta/3"))
				.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar4O4NotFoundAoDeletarPerguntaNaoExistente() throws Exception {		
		mock.perform(delete("/pergunta/deleta/99"))
				.andExpect(status().isNotFound());
	}
	


}
