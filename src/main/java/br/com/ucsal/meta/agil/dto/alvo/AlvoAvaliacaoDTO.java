package br.com.ucsal.meta.agil.dto.alvo;

import java.util.ArrayList;

public class AlvoAvaliacaoDTO {
	
	private String cdAvaliacao;
	/** Nome da Avaliação **/
	private String label;
	/** Camada **/
	private String dtAvaliacao;
	private ArrayList <AlvoCamadaDTO> children;
	private Integer cdTime;
	private Integer cdAplicacao;

	
	public AlvoAvaliacaoDTO() {
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
	public Integer getCdTime() {
		return cdTime;
	}
	public void setCdTime(Integer cdTime) {
		this.cdTime = cdTime;
	}
	public Integer getCdAplicacao() {
		return cdAplicacao;
	}
	public void setCdAplicacao(Integer cdAplicacao) {
		this.cdAplicacao = cdAplicacao;
	}
	
}
