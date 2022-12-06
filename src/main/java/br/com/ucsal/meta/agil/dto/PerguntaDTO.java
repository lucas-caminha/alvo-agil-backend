package br.com.ucsal.meta.agil.dto;

import br.com.ucsal.meta.agil.entity.PerguntaEntity;

public class PerguntaDTO {
	
	private Long cdPergunta;
	private String descPergunta;
	private String flPergunta;
	private Double peso;
	private Integer pontuacao;
	
	public PerguntaDTO() {}
	
	public Long getCdPergunta() {
		return cdPergunta;
	}
	public void setCdPergunta(Long cdPergunta) {
		this.cdPergunta = cdPergunta;
	}
	public String getDescPergunta() {
		return descPergunta;
	}
	public void setDescPergunta(String descPergunta) {
		this.descPergunta = descPergunta;
	}
	public String getFlPergunta() {
		return flPergunta;
	}
	public void setFlPergunta(String flPergunta) {
		this.flPergunta = flPergunta;
	}	
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public PerguntaEntity toEntity() {
		PerguntaEntity pergunta = new PerguntaEntity();
		pergunta.setCdPergunta(this.cdPergunta);
		pergunta.setDescPergunta(this.descPergunta);
		pergunta.setFlPergunta(this.flPergunta);
		//pergunta.setPontuacao(this.pontuacao);
		return pergunta;
	}

}
