package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "tecnologia")
public class TecnologiaEntity {

	@Id
	private Long cdTecnologia;
	private String nmTecnologia;
	private String flTecnogia;	
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
	public String getFlTecnogia() {
		return flTecnogia;
	}
	public void setFlTecnogia(String flTecnogia) {
		this.flTecnogia = flTecnogia;
	}
	public List<TimeEntity> getTimes() {
		return times;
	}
	public void setTimes(List<TimeEntity> times) {
		this.times = times;
	}
	
}
