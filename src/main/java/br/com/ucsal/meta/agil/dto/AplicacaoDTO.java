package br.com.ucsal.meta.agil.dto;

import javax.validation.constraints.NotNull;

import br.com.ucsal.meta.agil.entity.AplicacaoEntity;

public class AplicacaoDTO {
	
	private Long cdAplicacao;
	@NotNull
	private String nmAplicacao;
	private String flAplicacao;
	
	public Long getCdAplicacao() {
		return cdAplicacao;
	}
	public void setCdAplicacao(Long cdAplicacao) {
		this.cdAplicacao = cdAplicacao;
	}
	public String getNmAplicacao() {
		return nmAplicacao;
	}
	public void setNmAplicacao(String nmAplicacao) {
		this.nmAplicacao = nmAplicacao;
	}
	public String getFlAplicacao() {
		return flAplicacao;
	}
	public void setFlAplicacao(String flAplicacao) {
		this.flAplicacao = flAplicacao;
	}
	
	public AplicacaoEntity toEntity() {
		AplicacaoEntity aplicacao = new AplicacaoEntity();
		aplicacao.setNmAplicacao(this.nmAplicacao);
		aplicacao.setFlAplicacao(this.flAplicacao);
		aplicacao.setCdAplicacao(this.cdAplicacao);
		return aplicacao;
	}

}
