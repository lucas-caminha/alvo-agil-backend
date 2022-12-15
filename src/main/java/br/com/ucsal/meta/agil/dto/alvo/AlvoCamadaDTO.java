package br.com.ucsal.meta.agil.dto.alvo;

import java.util.ArrayList;

public class AlvoCamadaDTO {
	
	/** Nome da Camada **/
	private String label;
	/** Nota da Camada **/
	private Double nota;
	/** Temas **/ 
	private ArrayList <AlvoTemaDTO> children;
	
	public AlvoCamadaDTO() {
		this.children = new ArrayList<>();
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
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
