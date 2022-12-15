package br.com.ucsal.meta.agil.dto.alvo;

import java.util.ArrayList;

public class AlvoTemaDTO {
	
	/** Nome do Tema **/
	private String label;
	/** Nota do Tema **/
	private Double nota;
	/** Perguntas **/ 
	private ArrayList <AlvoPerguntaDTO> children;
	
	public AlvoTemaDTO() {
		this.children = new ArrayList<>();
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ArrayList<AlvoPerguntaDTO> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<AlvoPerguntaDTO> children) {
		this.children = children;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}	

}
