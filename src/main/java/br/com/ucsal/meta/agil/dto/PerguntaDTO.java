package br.com.ucsal.meta.agil.dto;

import java.util.List;

import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

public class PerguntaDTO {
	
	private Long cdPergunta;
	private String descPergunta;
	private String flPergunta;
	private List<TimeEntity> times;
	
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
	public List<TimeEntity> getTimes() {
		return times;
	}
	public void setTimes(List<TimeEntity> times) {
		this.times = times;
	}

	public PerguntaEntity toEntity() {
		PerguntaEntity pergunta = new PerguntaEntity();
		pergunta.setCdPergunta(this.cdPergunta);
		pergunta.setDescPergunta(this.descPergunta);
		pergunta.setFlPergunta(this.flPergunta);
		pergunta.setTimes(this.times);
		return null;
	}

}
