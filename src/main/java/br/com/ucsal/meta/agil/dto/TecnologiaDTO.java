package br.com.ucsal.meta.agil.dto;

import javax.validation.constraints.NotNull;

import br.com.ucsal.meta.agil.entity.TecnologiaEntity;

public class TecnologiaDTO {
	
	private Long cdTecnologia;
	@NotNull
	private String nmTecnologia;
	private String flTecnologia;
	
	public Long getCdTecnologia() {
		return cdTecnologia;
	}

	public void setCdTecnologia(Long cdTecnologia) {
		this.cdTecnologia = cdTecnologia;
	}

	public String getNmTecnologia() {
		return nmTecnologia;
	}

	public void setNmTecnologia(String nmTecnologia) {
		this.nmTecnologia = nmTecnologia;
	}

	public String getFlTecnologia() {
		return flTecnologia;
	}

	public void setFlTecnologia(String flTecnologia) {
		this.flTecnologia = flTecnologia;
	}
	
	public TecnologiaEntity toEntity() {		
		TecnologiaEntity tecnologia = new TecnologiaEntity();
		tecnologia.setNmTecnologia(this.nmTecnologia);
		tecnologia.setFlTecnologia(this.flTecnologia);
		return tecnologia;
	}

}
