package br.com.ucsal.meta.agil.dto;

import javax.validation.constraints.NotNull;

import br.com.ucsal.meta.agil.entity.FrameworkEntity;

public class FrameworkDTO {
	
	private Long cdFramework;
	@NotNull
	private String nmFramework;
	private String flFramework;
	
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
	public Long getCdFramework() {
		return cdFramework;
	}
	public void setCdFramework(Long id) {
		this.cdFramework = id;
	}
	
	public FrameworkEntity toEntity() {
		FrameworkEntity framework = new FrameworkEntity();
		framework.setCdFramework(this.cdFramework);
		framework.setNmFramework(this.nmFramework);
		framework.setFlFramework(this.flFramework);
		return framework;
	}

}
