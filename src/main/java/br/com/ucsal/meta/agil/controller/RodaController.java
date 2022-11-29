package br.com.ucsal.meta.agil.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ucsal.meta.agil.dto.RodaCamadaDTO;
import br.com.ucsal.meta.agil.dto.RodaDTO;
import br.com.ucsal.meta.agil.dto.RodaPerguntaDTO;
import br.com.ucsal.meta.agil.dto.RodaTemaDTO;
import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;
import br.com.ucsal.meta.agil.service.AplicacaoService;
import br.com.ucsal.meta.agil.service.AvaliacaoService;
import br.com.ucsal.meta.agil.service.CamadaService;
import br.com.ucsal.meta.agil.service.PerguntaService;
import br.com.ucsal.meta.agil.service.TemaService;

@RestController
@RequestMapping("/roda")
public class RodaController {
	
	@Autowired
	private Gson gson;
	@Autowired
	private AplicacaoService aplicacaoService;
	@Autowired
	private AvaliacaoService avaliacaoService;
	@Autowired
	private CamadaService camadaService;
	@Autowired
	private TemaService temaService;
	@Autowired
	private PerguntaService perguntaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/aplicacao/{id}", produces = "application/json")
	public ResponseEntity<String> getAplicacaoById(@PathVariable(name = "id") Integer cdAplicacao) {	
		
		AplicacaoEntity aplicacao = aplicacaoService.buscaAplicacaoPorId(cdAplicacao);	
		
		RodaDTO roda = new RodaDTO();
		roda.setLabel(aplicacao.getNmAplicacao());
		ArrayList<RodaCamadaDTO> camadasRoda = new ArrayList<RodaCamadaDTO>();
		
		for(CamadaEntity camada : aplicacao.getCamadas()) {
			RodaCamadaDTO c = new RodaCamadaDTO();
			c.setLabel(camada.getNmCamada());
			ArrayList<RodaTemaDTO> temasRoda = new ArrayList<RodaTemaDTO>();
			
			for(TemaEntity tema : camada.getTemas()) {
				RodaTemaDTO t = new RodaTemaDTO();
				t.setLabel(tema.getNmTema());
				ArrayList<RodaPerguntaDTO> perguntasRoda = new ArrayList<RodaPerguntaDTO>();
				
				for(PerguntaEntity pergunta : tema.getPerguntas()) {
					RodaPerguntaDTO p = new RodaPerguntaDTO();
					p.setLabel(pergunta.getDescPergunta());
					p.setScore(pergunta.getPontuacao());
					p.setPeso(pergunta.getPeso());
					perguntasRoda.add(p);
				}
				
				t.setChildren(perguntasRoda);
				temasRoda.add(t);
			}
			
			c.setChildren(temasRoda);		
			camadasRoda.add(c);
		}
		
		roda.setChildren(camadasRoda);
		
		String json = gson.toJson(roda);
	
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/aplicacao/add", produces = "application/json")
	public ResponseEntity<AplicacaoEntity> addAplicacao(@RequestBody RodaDTO dto) {	
		
		AplicacaoEntity aplicacao = new AplicacaoEntity();
		aplicacao.setNmAplicacao(dto.getLabel());
		aplicacao.setFlAplicacao("S");
		
		AplicacaoEntity aplicacaoSalva = aplicacaoService.save(aplicacao);
		
		List<CamadaEntity> camadas = new ArrayList<CamadaEntity>();
		for(RodaCamadaDTO c : dto.getChildren()) {
			CamadaEntity camada = new CamadaEntity();
			camada.setNmCamada(c.getLabel());
			camada.setFlCamada("S");
			camada.setAplicacao(aplicacaoSalva);
			CamadaEntity camadaSalva = camadaService.save(camada);
			
			List<TemaEntity> temas = new ArrayList<TemaEntity>();
			for(RodaTemaDTO t : c.getChildren()) {
				TemaEntity tema = new TemaEntity();
				tema.setNmTema(t.getLabel());
				tema.setFlTema("S");
				tema.setCamada(camadaSalva);
				TemaEntity temaSalvo = temaService.save(tema);
				
				List<PerguntaEntity> perguntas = new ArrayList<PerguntaEntity>();
				for(RodaPerguntaDTO p : t.getChildren()) {
					PerguntaEntity pergunta = new PerguntaEntity();
					pergunta.setDescPergunta(p.getLabel());
					pergunta.setFlPergunta("S");
					pergunta.setPeso(p.getPeso());
					pergunta.setPontuacao(p.getScore());
					ArrayList<TemaEntity> ts = new ArrayList<TemaEntity>();
					ts.add(temaSalvo);			
					pergunta.setTemas(ts);
					PerguntaEntity perguntaSalva = perguntaService.save(pergunta);
					
					perguntas.add(perguntaSalva);
				}
				
				temaSalvo.setPerguntas(perguntas);
				temas.add(temaSalvo);
			}
			camadaSalva.setTemas(temas);
			camadas.add(camadaSalva);
		}
		aplicacaoSalva.setCamadas(camadas);

		//String json = gson.toJson(aplicacaoSalva);
	
		return ResponseEntity.status(HttpStatus.OK).body(aplicacaoSalva);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/avaliacao/{id}", produces = "application/json")
	public ResponseEntity<String> getAvaliacaoById(@PathVariable(name = "id") Integer cdAvaliacao) {	
		
		AvaliacaoEntity avaliacao = avaliacaoService.buscaAvaliacaoPorId(cdAvaliacao);	
		
		RodaDTO roda = new RodaDTO();
		roda.setLabel(avaliacao.getNmAvaliacao());
		ArrayList<RodaCamadaDTO> camadasRoda = new ArrayList<RodaCamadaDTO>();
		
		for(CamadaEntity camada : avaliacao.getAplicacao().getCamadas()) {
			RodaCamadaDTO c = new RodaCamadaDTO();
			c.setLabel(camada.getNmCamada());
			ArrayList<RodaTemaDTO> temasRoda = new ArrayList<RodaTemaDTO>();
			
			for(TemaEntity tema : camada.getTemas()) {
				RodaTemaDTO t = new RodaTemaDTO();
				t.setLabel(tema.getNmTema());
				ArrayList<RodaPerguntaDTO> perguntasRoda = new ArrayList<RodaPerguntaDTO>();
				
				for(PerguntaEntity pergunta : tema.getPerguntas()) {
					RodaPerguntaDTO p = new RodaPerguntaDTO();
					p.setLabel(pergunta.getDescPergunta());
					p.setScore(pergunta.getPontuacao());
					p.setPeso(pergunta.getPeso());
					perguntasRoda.add(p);
				}
				
				t.setChildren(perguntasRoda);
				temasRoda.add(t);
			}
			
			c.setChildren(temasRoda);		
			camadasRoda.add(c);
		}
		
		roda.setChildren(camadasRoda);
		
		String json = gson.toJson(roda);
	
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}


}
