package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;

public class RodaCamadaDTO {
	
	/** Nome da Camada **/
	private String label;
	/** Temas **/ 
	private ArrayList <RodaTemaDTO> children;
	
	public RodaCamadaDTO() {
		this.children = new ArrayList<>();
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ArrayList<RodaTemaDTO> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<RodaTemaDTO> children) {
		this.children = children;
	}
	
}
