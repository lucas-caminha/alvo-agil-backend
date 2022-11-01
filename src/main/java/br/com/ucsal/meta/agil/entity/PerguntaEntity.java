package br.com.ucsal.meta.agil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pergunta")
public class PerguntaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdPergunta;
	private String descPergunta;
	private String flPergunta;
	private Double peso;
	@ManyToOne
	@JoinColumn(name = "cdTema")
	private TemaEntity tema;
	
	public PerguntaEntity() {}
	
	public Long getCdPergunta() {
		return cdPergunta;
	}
	public void setCdPergunta(Long cdPergunta) {
		this.cdPergunta = cdPergunta;
	}
	public String getDescPergunta() {
		return descPergunta;
	}
	public void setDescPergunta(String descPergunta) {
		this.descPergunta = descPergunta;
	}
	public String getFlPergunta() {
		return flPergunta;
	}
	public void setFlPergunta(String flPergunta) {
		this.flPergunta = flPergunta;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public TemaEntity getTema() {
		return tema;
	}
	public void setTema(TemaEntity tema) {
		this.tema = tema;
	}
	
}
