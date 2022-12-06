package br.com.ucsal.meta.agil.dto.alvo;

import java.util.ArrayList;

public class AlvoAvaliacaoDTO {
	
	private Integer cdAvaliacao;
	/** Nome da Avaliação **/
	private String label;
	private String dtAvaliacao;
	private Integer cdTime;
	private Integer cdAplicacao;
	private Integer notaTotal;
	/** Camada **/
	private ArrayList <AlvoCamadaDTO> children;

	public AlvoAvaliacaoDTO() {
		this.children = new ArrayList<>();
	}

	public Integer getCdAvaliacao() {
		return cdAvaliacao;
	}

	public void setCdAvaliacao(Integer cdAvaliacao) {
		this.cdAvaliacao = cdAvaliacao;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public ArrayList<AlvoCamadaDTO> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<AlvoCamadaDTO> children) {
		this.children = children;
	}
	public Integer getNotaTotal() {
		return notaTotal;
	}

	public void setNotaTotal(Integer notaTotal) {
		this.notaTotal = notaTotal;
	}
	
}
