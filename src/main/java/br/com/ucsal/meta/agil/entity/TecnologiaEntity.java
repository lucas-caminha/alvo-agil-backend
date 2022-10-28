package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "tecnologia")
public class TecnologiaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdTecnologia;
	private String nmTecnologia;
	private String flTecnologia;	
	@JsonIgnore
	@ManyToMany(mappedBy = "tecnologias")
	private List<TimeEntity> times;
	
	public TecnologiaEntity() {}
	
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
	
}
