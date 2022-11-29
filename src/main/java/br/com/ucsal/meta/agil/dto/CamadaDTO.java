package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;

public class CamadaDTO {
	
	private Long cdCamada;
	private String nmCamada;
	private String flCamada;
	private Integer cdAplicacao;
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
	public Integer getCdAplicacao() {
		return cdAplicacao;
	}
	public void setCdAplicacao(Integer cdAplicacao) {
		this.cdAplicacao = cdAplicacao;
	}

	public CamadaEntity toEntity() {
		CamadaEntity entity = new CamadaEntity();
		entity.setCdCamada(this.cdCamada);
		entity.setNmCamada(this.nmCamada);
		entity.setFlCamada(this.flCamada);
		entity.getAplicacao().setCdAplicacao(this.cdAplicacao.longValue());
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

}
