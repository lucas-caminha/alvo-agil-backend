package br.com.ucsal.meta.agil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "avaliacao")
public class AvaliacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdAvaliacao;
	private String nmAvaliacao;
	private String flAvaliacao;
	private Integer notaAvaliacao;
	@OneToOne
	@JoinColumn(name = "cdAplicacao")
	private AplicacaoEntity aplicacao;
	@ManyToOne
	@JoinColumn(name = "cdTime")
	private TimeEntity time;
	
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
	public Integer getNotaAvaliacao() {
		return notaAvaliacao;
	}
	public void setNotaAvaliacao(Integer notaAvaliacao) {
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

}
