package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;

public class RodaTemaDTO {
	
	/** Nome do Tema **/
	private String label;
	/** Perguntas **/ 
	private ArrayList <RodaPerguntaDTO> children;
	
	public RodaTemaDTO() {
		this.children = new ArrayList<>();
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ArrayList<RodaPerguntaDTO> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<RodaPerguntaDTO> children) {
		this.children = children;
	}

}
