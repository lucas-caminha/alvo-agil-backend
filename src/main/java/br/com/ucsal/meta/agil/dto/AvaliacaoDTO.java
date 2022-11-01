package br.com.ucsal.meta.agil.dto;

import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

public class AvaliacaoDTO {
	
	private Long cdAvaliacao;
	private String nmAvaliacao;
	private String flAvaliacao;
	private Double notaAvaliacao;
	private AplicacaoEntity aplicacao;
	private TimeEntity time;
	
	public AvaliacaoDTO() {}

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

	public AvaliacaoEntity toEntity() {
		AvaliacaoEntity entity = new AvaliacaoEntity();
		entity.setCdAvaliacao(this.cdAvaliacao);
		entity.setFlAvaliacao(this.flAvaliacao);
		entity.setNmAvaliacao(this.nmAvaliacao);
		entity.setNotaAvaliacao(this.notaAvaliacao);
		entity.setTime(this.time);
		entity.setAplicacao(this.aplicacao);
		return entity;
	}

}
