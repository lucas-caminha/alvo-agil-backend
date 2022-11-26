package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;

public class RodaDTO {
	
	private String idAvaliacao;
	/** Nome da Avaliação **/
	private String label;
	/** Camada **/
	private String dtAvaliacao;
	private ArrayList <RodaCamadaDTO> children;

	
	public RodaDTO() {
		this.children = new ArrayList<>();
	}
	
	public String getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(String idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ArrayList<RodaCamadaDTO> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<RodaCamadaDTO> children) {
		this.children = children;
	}
	public String getDtAvaliacao() {
		return dtAvaliacao;
	}
	public void setDtAvaliacao(String dtAvaliacao) {
		this.dtAvaliacao = dtAvaliacao;
	}
	
}
