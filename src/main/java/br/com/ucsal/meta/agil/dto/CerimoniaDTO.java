package br.com.ucsal.meta.agil.dto;

import javax.validation.constraints.NotNull;

import br.com.ucsal.meta.agil.entity.CerimoniaEntity;

public class CerimoniaDTO {
	
	private Long cdCerimonia;
	@NotNull
	private String nmCerimonia;
	private String flCerimonia;
	
	public Long getCdCerimonia() {
		return cdCerimonia;
	}
	public void setCdCerimonia(Long cdCerimonia) {
		this.cdCerimonia = cdCerimonia;
	}
	public String getNmCerimonia() {
		return nmCerimonia;
	}
	public void setNmCerimonia(String nmCerimonia) {
		this.nmCerimonia = nmCerimonia;
	}
	public String getFlCerimonia() {
		return flCerimonia;
	}
	public void setFlCerimonia(String flCerimonia) {
		this.flCerimonia = flCerimonia;
	}
	
	public CerimoniaEntity toEntity() {
		CerimoniaEntity cerimonia = new CerimoniaEntity();
		cerimonia.setNmCerimonia(this.nmCerimonia);
		cerimonia.setFlCerimonia(this.flCerimonia);
		cerimonia.setCdCerimonia(this.cdCerimonia);
		return cerimonia;
	}

}

