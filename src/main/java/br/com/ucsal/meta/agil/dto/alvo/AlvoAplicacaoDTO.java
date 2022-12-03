package br.com.ucsal.meta.agil.dto.alvo;

import java.util.ArrayList;

public class AlvoAplicacaoDTO {
	
	private String cdAplicacao;
	/** Nome da Avaliação **/
	private String label;
	/** Camada **/
	private String dtAvaliacao;
	private ArrayList <AlvoCamadaDTO> children;

	
	public AlvoAplicacaoDTO() {
		this.children = new ArrayList<>();
	}
	
	public String getCdAplicacao() {
		return cdAplicacao;
	}
	public void setCdAplicacao(String cdAplicacao) {
		this.cdAplicacao = cdAplicacao;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ArrayList<AlvoCamadaDTO> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<AlvoCamadaDTO> children) {
		this.children = children;
	}
	public String getDtAvaliacao() {
		return dtAvaliacao;
	}
	public void setDtAvaliacao(String dtAvaliacao) {
		this.dtAvaliacao = dtAvaliacao;
	}
	
}
