package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "cerimonia")
public class CerimoniaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdCerimonia;
	private String nmCerimonia;
	private String flCerimonia;
	@JsonIgnore
	@ManyToMany(mappedBy = "cerimonias")
	private List<TimeEntity> times;
		
	public CerimoniaEntity() {}
	
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
	public List<TimeEntity> getTimes() {
		return times;
	}
	public void setTimes(List<TimeEntity> times) {
		this.times = times;
	}
}
