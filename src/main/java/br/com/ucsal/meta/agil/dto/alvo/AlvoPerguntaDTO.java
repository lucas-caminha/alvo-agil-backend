package br.com.ucsal.meta.agil.dto.alvo;

public class AlvoPerguntaDTO {
	
	/** Código da Pergunta **/
	private Integer cdPergunta;
	/** Nome da Pergunta **/
	private String label;
	/** Valor da Resposta **/
	private Integer score;
	/** Peso da Pergunta **/
	private Integer peso;
	
	public AlvoPerguntaDTO() {}
	
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
	public Integer getCdPergunta() {
		return cdPergunta;
	}
	public void setCdPergunta(Integer cdPergunta) {
		this.cdPergunta = cdPergunta;
	}

}
