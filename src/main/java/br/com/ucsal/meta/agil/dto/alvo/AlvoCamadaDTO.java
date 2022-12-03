package br.com.ucsal.meta.agil.dto.alvo;

import java.util.ArrayList;

public class AlvoCamadaDTO {
	
	/** Nome da Camada **/
	private String label;
	/** Temas **/ 
	private ArrayList <AlvoTemaDTO> children;
	
	public AlvoCamadaDTO() {
		this.children = new ArrayList<>();
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ArrayList<AlvoTemaDTO> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<AlvoTemaDTO> children) {
		this.children = children;
	}
	
}
