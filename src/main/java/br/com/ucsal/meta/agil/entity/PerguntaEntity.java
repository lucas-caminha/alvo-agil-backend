package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "pergunta")
public class PerguntaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdPergunta;
	private String descPergunta;
	private String flPergunta;
	private Double peso;
	private Integer pontuacao;
	@ManyToMany(mappedBy = "perguntas")
	private List<TemaEntity> temas;

	
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
	@JsonBackReference
	public List<TemaEntity> getTemas() {
		return temas;
	}
	public void setTemas(List<TemaEntity> temas) {
		this.temas = temas;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	
}
