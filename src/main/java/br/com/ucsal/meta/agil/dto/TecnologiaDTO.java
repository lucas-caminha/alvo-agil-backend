package br.com.ucsal.meta.agil.dto;

import java.util.List;

import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

public class TecnologiaDTO {
	
	private Long cdTecnologia;
	private String nmTecnologia;
	private String flTecnologia;
	private List<TimeEntity>times;
	
	
	

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

	public List<TimeEntity> getTimes() {
		return times;
	}

	public void setTimes(List<TimeEntity> times) {
		this.times = times;
	}


	
	public TecnologiaEntity toEntity() {
		
		TecnologiaEntity tecnologia = new TecnologiaEntity();
		tecnologia.setNmTecnologia(this.nmTecnologia);
		tecnologia.setFlTecnologia(this.flTecnologia);
		tecnologia.setTimes(this.times);
		return tecnologia;
	}

}
