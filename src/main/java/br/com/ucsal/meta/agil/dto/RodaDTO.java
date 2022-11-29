package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;

public class RodaDTO {
	
	private String cdAvaliacao;
	/** Nome da Avaliação **/
	private String label;
	/** Camada **/
	private String dtAvaliacao;
	private ArrayList <RodaCamadaDTO> children;

	
	public RodaDTO() {
		this.children = new ArrayList<>();
	}
	
	public String getCdAvaliacao() {
		return cdAvaliacao;
	}
	public void setCdAvaliacao(String idAvaliacao) {
		this.cdAvaliacao = idAvaliacao;
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
