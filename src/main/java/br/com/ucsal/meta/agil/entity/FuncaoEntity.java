package br.com.ucsal.meta.agil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "funcao")
public class FuncaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdFuncao;
	private String nmFuncao;
	private String flFuncao;

	public FuncaoEntity() {}

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
	
	
}
