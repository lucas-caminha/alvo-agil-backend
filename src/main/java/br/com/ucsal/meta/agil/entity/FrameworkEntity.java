package br.com.ucsal.meta.agil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "framework")
public class FrameworkEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdFramework;
	private String nmFramework;
	private String flFramework;
	
	public FrameworkEntity() {}
	
	public Long getCdFramework() {
		return cdFramework;
	}
	public void setCdFramework(Long cdFramework) {
		this.cdFramework = cdFramework;
	}
	public String getNmFramework() {
		return nmFramework;
	}
	public void setNmFramework(String nmFramework) {
		this.nmFramework = nmFramework;
	}
	public String getFlFramework() {
		return flFramework;
	}
	public void setFlFramework(String flFramework) {
		this.flFramework = flFramework;
	}

}
