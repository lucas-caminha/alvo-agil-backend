package br.com.ucsal.meta.agil.controller;

import java.time.LocalDate;
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

import br.com.ucsal.meta.agil.dto.alvo.AlvoAplicacaoDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoAvaliacaoDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoCamadaDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoPerguntaDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoTemaDTO;
import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.RespostaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;
import br.com.ucsal.meta.agil.service.AlvoService;
import br.com.ucsal.meta.agil.service.AplicacaoService;
import br.com.ucsal.meta.agil.service.AvaliacaoService;
import br.com.ucsal.meta.agil.service.CamadaService;
import br.com.ucsal.meta.agil.service.PerguntaService;
import br.com.ucsal.meta.agil.service.RespostaService;
import br.com.ucsal.meta.agil.service.TemaService;
import br.com.ucsal.meta.agil.service.TimeService;

@RestController
@RequestMapping("/agil")
public class AlvoController {
	
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
	@Autowired
	private TimeService timeService;
	@Autowired
	private AlvoService alvoService;
	@Autowired
	private RespostaService respostaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/aplicacao/{id}", produces = "application/json")
	public ResponseEntity<String> getAplicacaoById(@PathVariable(name = "id") Integer cdAplicacao) {	
		
		AplicacaoEntity aplicacao = aplicacaoService.buscaAplicacaoPorId(cdAplicacao);	
		
		AlvoAvaliacaoDTO roda = new AlvoAvaliacaoDTO();
		roda.setLabel(aplicacao.getNmAplicacao());
		ArrayList<AlvoCamadaDTO> camadasRoda = new ArrayList<AlvoCamadaDTO>();
		
		for(CamadaEntity camada : aplicacao.getCamadas()) {
			AlvoCamadaDTO c = new AlvoCamadaDTO();
			c.setLabel(camada.getNmCamada());
			ArrayList<AlvoTemaDTO> temasRoda = new ArrayList<AlvoTemaDTO>();
			
			for(TemaEntity tema : camada.getTemas()) {
				AlvoTemaDTO t = new AlvoTemaDTO();
				t.setLabel(tema.getNmTema());
				ArrayList<AlvoPerguntaDTO> perguntasRoda = new ArrayList<AlvoPerguntaDTO>();
				
				for(PerguntaEntity pergunta : tema.getPerguntas()) {
					AlvoPerguntaDTO p = new AlvoPerguntaDTO();
					p.setLabel(pergunta.getDescPergunta());
					//p.setScore(pergunta.getPontuacao());
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
	public ResponseEntity<AplicacaoEntity> addAplicacao(@RequestBody AlvoAplicacaoDTO dto) {	
		
		AplicacaoEntity aplicacao = new AplicacaoEntity();
		aplicacao.setNmAplicacao(dto.getLabel());
		aplicacao.setFlAplicacao("S");
		AplicacaoEntity aplicacaoSalva = aplicacaoService.save(aplicacao);
		
		ArrayList<AplicacaoEntity> aplicacoes = new ArrayList<AplicacaoEntity>();
		aplicacoes.add(aplicacaoSalva);
		
		List<CamadaEntity> camadas = new ArrayList<CamadaEntity>();
		for(AlvoCamadaDTO c : dto.getChildren()) {
			
			CamadaEntity camada = new CamadaEntity();
			camada.setNmCamada(c.getLabel());
			camada.setFlCamada("S");
			camada.setAplicacoes(aplicacoes);
			
			CamadaEntity camadaSalva = camadaService.save(camada);
			
			List<TemaEntity> temas = new ArrayList<TemaEntity>();
			for(AlvoTemaDTO t : c.getChildren()) {
				TemaEntity tema = new TemaEntity();
				tema.setNmTema(t.getLabel());
				tema.setFlTema("S");
				tema.setCamada(camadaSalva);
				TemaEntity temaSalvo = temaService.save(tema);
				
				List<PerguntaEntity> perguntas = new ArrayList<PerguntaEntity>();
				for(AlvoPerguntaDTO p : t.getChildren()) {
					PerguntaEntity pergunta = new PerguntaEntity();
					pergunta.setDescPergunta(p.getLabel());
					pergunta.setFlPergunta("S");
					pergunta.setPeso(p.getPeso());
					//pergunta.setPontuacao(p.getScore());
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
		
		AplicacaoEntity atualizado = aplicacaoService.atualiza(aplicacaoSalva);
	
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/avaliacao/add", produces = "application/json")
	public ResponseEntity<AlvoAvaliacaoDTO> addAvaliacao(@RequestBody AlvoAvaliacaoDTO dto) {	
		
		Integer notaAvaliacao = 0;
		
		AvaliacaoEntity avaliacao = new AvaliacaoEntity();
		avaliacao.setNmAvaliacao(dto.getLabel());
		avaliacao.setFlAvaliacao("S");
		LocalDate dtAvaliacao = LocalDate.parse(dto.getDtAvaliacao());
		avaliacao.setDtAvaliacao(dtAvaliacao);
	
		/** Time em que a Avaliação foi realizada **/
		TimeEntity time = timeService.buscaTimePorId(dto.getCdTime());
		avaliacao.setTime(time);
		
		/** Aplicacão que foi utilizada **/
		AplicacaoEntity aplicacao = aplicacaoService.buscaAplicacaoPorId(dto.getCdAplicacao());
		avaliacao.setAplicacao(aplicacao);
		
		/** Salva Avaliação **/
		AvaliacaoEntity avaliacaoSalva = avaliacaoService.save(avaliacao);
		
		Integer notaCamada = 0;
		Integer pesoCamadaTotal = 0;
		
		List<CamadaEntity> camadas = new ArrayList<CamadaEntity>();	
		for(AlvoCamadaDTO alvoDTO : dto.getChildren()) {
			CamadaEntity camada = new CamadaEntity();
			camada.setNmCamada(alvoDTO.getLabel());
			

			
			List<TemaEntity> temas = new ArrayList<TemaEntity>();
			for(AlvoTemaDTO temaDTO : alvoDTO.getChildren()) {
				TemaEntity tema = new TemaEntity();
				tema.setNmTema(temaDTO.getLabel());
				
				Integer notaTema = 0;
				Integer pesoTemaTotal = 0;
				
				List<PerguntaEntity> perguntas = new ArrayList<PerguntaEntity>();
				for(AlvoPerguntaDTO perguntaDTO : temaDTO.getChildren()) {
					PerguntaEntity perguntaEntity = new PerguntaEntity();
					perguntaEntity.setCdPergunta(perguntaDTO.getCdPergunta().longValue());
					perguntaEntity.setDescPergunta(perguntaDTO.getLabel());
					perguntaEntity.setPeso(perguntaDTO.getPeso());
					perguntas.add(perguntaEntity);
								
					PerguntaEntity perguntaFind = perguntaService.buscaPerguntaPorId(perguntaEntity.getCdPergunta().intValue());
					
					/** Salva as respostas da Avaliação **/
					RespostaEntity resposta = new RespostaEntity();					
					resposta.setNota(perguntaDTO.getScore());
					resposta.setAvaliacao(avaliacaoSalva);
					resposta.setPergunta(perguntaFind);
					resposta.setNota(perguntaDTO.getScore());	
					respostaService.save(resposta);
					
					/** Soma pesos **/
					pesoTemaTotal += perguntaEntity.getPeso();
					/** Soma notas do Tema **/
					notaTema += (perguntaEntity.getPeso() * resposta.getNota());
				}
				
				/** Nota final do Tema **/
				notaTema = notaTema/pesoTemaTotal;		
				
				notaCamada += notaTema;
				
				tema.setPerguntas(perguntas);
				temas.add(tema);
			}
			
			camada.setTemas(temas);
			camadas.add(camada);
		}		
		
		pesoCamadaTotal = camadas.size();
		notaCamada = notaCamada / pesoCamadaTotal;
		
		notaAvaliacao += notaCamada;
		
		//notaAvaliacao += notaCamada;

		/** Calcula nota final da Avaliação **/
		//Integer notaFinal = alvoService.calculaNotas(camadas);		
		avaliacaoSalva.setNotaAvaliacao(notaAvaliacao);
		
		AvaliacaoEntity atualizado = avaliacaoService.atualiza(avaliacaoSalva);
		
		/** Transforma entidade no formato Alvo **/
		AlvoAvaliacaoDTO alvo = avaliacaoService.avaliacaoEntityToAlvoAvaliacaoDTO(atualizado);
		
		return ResponseEntity.status(HttpStatus.OK).body(alvo);
	}
	


	@RequestMapping(method = RequestMethod.GET, value = "/avaliacao/{id}", produces = "application/json")
	public ResponseEntity<AlvoAvaliacaoDTO> getAvaliacaoById(@PathVariable(name = "id") Integer cdAvaliacao) {	
		AvaliacaoEntity avaliacao = avaliacaoService.buscaAvaliacaoPorId(cdAvaliacao);	
		AlvoAvaliacaoDTO alvo = avaliacaoService.avaliacaoEntityToAlvoAvaliacaoDTO(avaliacao);		
		return ResponseEntity.status(HttpStatus.OK).body(alvo);
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/aplicacao/todos", produces = "application/json")
	public ResponseEntity<List<AlvoAplicacaoDTO>> getAplicacoes() {		
		List<AplicacaoEntity> aplicacoes = aplicacaoService.getAllAplicacoes();	
		List<AlvoAplicacaoDTO> alvoAplicacoes = alvoService.entityListToAplicacaoDTOList(aplicacoes);	
		return ResponseEntity.status(HttpStatus.OK).body(alvoAplicacoes);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "avaliacao/time/{id}", produces = "application/json")
	public ResponseEntity<List<AlvoAvaliacaoDTO>> getAvaliacaoByTime(@PathVariable(name = "id") Integer cdTime) {	
		List<AvaliacaoEntity> avaliacoes = avaliacaoService.buscaAvaliacaoPorTime(cdTime);
		if(avaliacoes == null) {
			return ResponseEntity.noContent().build();
		}		
		List<AlvoAvaliacaoDTO> alvoAvaliacoes = alvoService.entityListToAlvoAvaliacaoDTOList(avaliacoes);	
		return ResponseEntity.status(HttpStatus.OK).body(alvoAvaliacoes);
	}


	
}
