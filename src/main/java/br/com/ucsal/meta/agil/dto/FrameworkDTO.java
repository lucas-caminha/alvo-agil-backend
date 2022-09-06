package br.com.ucsal.meta.agil.dto;

import br.com.ucsal.meta.agil.entity.FrameworkEntity;

public class FrameworkDTO {
	
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
	
	public FrameworkEntity toEntity() {
		FrameworkEntity framework = new FrameworkEntity();
		framework.setNmFramework(this.nmFramework);
		framework.setFlFramework(this.flFramework);
		return framework;
	}

}
