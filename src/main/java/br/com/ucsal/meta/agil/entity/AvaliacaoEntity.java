package br.com.ucsal.meta.agil.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "avaliacao")
public class AvaliacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdAvaliacao;
	private String nmAvaliacao;
	private String flAvaliacao;
	private Double notaAvaliacao;
	private LocalDate dtAvaliacao;
	@OneToOne
	@JoinColumn(name = "cdAplicacao")
	private AplicacaoEntity aplicacao;
	@ManyToOne
	@JoinColumn(name = "cdTime")
	private TimeEntity time;
	@JsonManagedReference
	@OneToMany(mappedBy = "avaliacao")
	private List<RespostaEntity> respostas;
	
	public AvaliacaoEntity() {}
	
	public Long getCdAvaliacao() {
		return cdAvaliacao;
	}
	public void setCdAvaliacao(Long cdAvaliacao) {
		this.cdAvaliacao = cdAvaliacao;
	}
	public String getNmAvaliacao() {
		return nmAvaliacao;
	}
	public void setNmAvaliacao(String nmAvaliacao) {
		this.nmAvaliacao = nmAvaliacao;
	}
	public String getFlAvaliacao() {
		return flAvaliacao;
	}
	public void setFlAvaliacao(String flAvaliacao) {
		this.flAvaliacao = flAvaliacao;
	}
	public Double getNotaAvaliacao() {
		return notaAvaliacao;
	}
	public void setNotaAvaliacao(Double notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}
	public AplicacaoEntity getAplicacao() {
		return aplicacao;
	}
	public void setAplicacao(AplicacaoEntity aplicacao) {
		this.aplicacao = aplicacao;
	}
	public TimeEntity getTime() {
		return time;
	}
	public void setTime(TimeEntity time) {
		this.time = time;
	}
	public List<RespostaEntity> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<RespostaEntity> respostas) {
		this.respostas = respostas;
	}
	public LocalDate getDtAvaliacao() {
		return dtAvaliacao;
	}
	public void setDtAvaliacao(LocalDate dtAvaliacao) {
		this.dtAvaliacao = dtAvaliacao;
	}

}
