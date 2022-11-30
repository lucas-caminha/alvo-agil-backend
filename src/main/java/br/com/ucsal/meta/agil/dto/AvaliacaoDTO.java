package br.com.ucsal.meta.agil.dto;

import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

public class AvaliacaoDTO {
	
	private Long cdAvaliacao;
	private String nmAvaliacao;
	private String flAvaliacao;
	private Double notaAvaliacao;
	private Integer cdAplicacao;
	private Integer cdTime;
	
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
	public Integer getCdAplicacao() {
		return cdAplicacao;
	}
	public void setCdAplicacao(Integer cdAplicacao) {
		this.cdAplicacao = cdAplicacao;
	}
	public Integer getCdTime() {
		return cdTime;
	}
	public void setCdTime(Integer cdTime) {
		this.cdTime = cdTime;
	}

	public AvaliacaoEntity toEntity() {
		AvaliacaoEntity entity = new AvaliacaoEntity();
		entity.setCdAvaliacao(this.cdAvaliacao);
		entity.setFlAvaliacao(this.flAvaliacao);
		entity.setNmAvaliacao(this.nmAvaliacao);
		entity.setNotaAvaliacao(this.notaAvaliacao);
		TimeEntity time = new TimeEntity();
		time.setCdTime(this.cdTime.longValue());	
		entity.setTime(time);
		AplicacaoEntity aplicacao = new AplicacaoEntity();
		aplicacao.setCdAplicacao(this.cdAplicacao.longValue());
		entity.setAplicacao(aplicacao);
		return entity;
	}

}
