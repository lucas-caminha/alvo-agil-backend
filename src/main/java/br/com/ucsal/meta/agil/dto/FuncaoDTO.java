package br.com.ucsal.meta.agil.dto;

import br.com.ucsal.meta.agil.entity.FuncaoEntity;

public class FuncaoDTO {
	
	private Long cdFuncao;
	private String nmFuncao;
	private String flFuncao;
	
	public FuncaoDTO() {}

	public Long getCdFuncao() {
		return cdFuncao;
	}
	public void setCdFuncao(Long cdFuncao) {
		this.cdFuncao = cdFuncao;
	}
	public String getNmFuncao() {
		return nmFuncao;
	}
	public void setNmFuncao(String nmFuncao) {
		this.nmFuncao = nmFuncao;
	}
	public String getFlFuncao() {
		return flFuncao;
	}
	public void setFlFuncao(String flFuncao) {
		this.flFuncao = flFuncao;
	}

	public FuncaoEntity toEntity() {
		FuncaoEntity entity = new FuncaoEntity();
		entity.setCdFuncao(cdFuncao);
		entity.setNmFuncao(nmFuncao);
		entity.setFlFuncao(flFuncao);
		return entity;
	}

}
