package br.com.ucsal.meta.agil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "resposta")
public class RespostaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdResposta;
	@ManyToOne
	@JoinColumn(name = "cdPergunta", nullable = false)
	private PerguntaEntity pergunta;
	@ManyToOne
	@JoinColumn(name = "cdAvaliacao", nullable = false)
	private AvaliacaoEntity avaliacao;
	private Integer nota;
	
	public RespostaEntity() {}
	
	public Long getCdResposta() {
		return cdResposta;
	}
	public void setCdResposta(Long cdResposta) {
		this.cdResposta = cdResposta;
	}
	@JsonBackReference
	public PerguntaEntity getPergunta() {
		return pergunta;
	}
	public void setPergunta(PerguntaEntity pergunta) {
		this.pergunta = pergunta;
	}
	@JsonBackReference
	public AvaliacaoEntity getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(AvaliacaoEntity avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}	
}
