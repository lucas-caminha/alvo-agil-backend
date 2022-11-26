package br.com.ucsal.meta.agil.dto;

public class RodaPerguntaDTO {
	
	/** Nome da pergunta **/
	private String label;
	/** Valor da Resposta **/
	private Integer score;
	/** Peso da Pergunta **/
	private Integer peso;
	
	public RodaPerguntaDTO() {}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
