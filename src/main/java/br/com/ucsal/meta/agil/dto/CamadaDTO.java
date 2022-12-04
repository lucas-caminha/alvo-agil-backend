package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;

public class CamadaDTO {
	
	private Long cdCamada;
	private String nmCamada;
	private String flCamada;
	private List<Integer> aplicacoes;
	private List<TemaDTO> temas;

	
	public CamadaDTO() {}
	
	public Long getCdCamada() {
		return cdCamada;
	}
	public void setCdCamada(Long cdCamada) {
		this.cdCamada = cdCamada;
	}
	public String getNmCamada() {
		return nmCamada;
	}
	public void setNmCamada(String nmCamada) {
		this.nmCamada = nmCamada;
	}
	public String getFlCamada() {
		return flCamada;
	}
	public void setFlCamada(String flCamada) {
		this.flCamada = flCamada;
	}
	public List<TemaDTO> getTemas() {
		return temas;
	}
	public void setTemas(List<TemaDTO> perguntas) {
		this.temas = perguntas;
	}
	public List<Integer> getAplicacoes() {
		return aplicacoes;
	}
	public void setAplicacoes(List<Integer> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public CamadaEntity toEntity() {
		CamadaEntity entity = new CamadaEntity();
		entity.setCdCamada(this.cdCamada);
		entity.setNmCamada(this.nmCamada);
		entity.setFlCamada(this.flCamada);
		entity.setAplicacoes(dtoToAplicacao());
		entity.setTemas(dtoToTemas());
		return entity;
	}
	
	private List<TemaEntity> dtoToTemas() { 
		List<TemaEntity> temas = new ArrayList<TemaEntity>();
		for(TemaDTO dto : this.temas) {
			temas.add(dto.toEntity());
		}
		return temas;
	}
	
	private List<AplicacaoEntity> dtoToAplicacao() {	
		List<AplicacaoEntity> aps = new ArrayList<AplicacaoEntity>();
		
		for(Integer cdAplicacao : aplicacoes) {
			AplicacaoEntity a = new AplicacaoEntity();
			a.setCdAplicacao(cdAplicacao.longValue());
			aps.add(a);
		}
		
		return aps;
	}

}
