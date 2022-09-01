package br.com.ucsal.meta.agil.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "framework")
public class FrameworkEntity {

	@Id
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
