package br.com.ucsal.meta.agil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "pergunta")
public class PerguntaEntity {

	@Id
	private Long cdPergunta;
	private String descPergunta;
	private String flPergunta;
	@ManyToMany(mappedBy = "perguntas")
	private List<TimeEntity> times;
	
	public PerguntaEntity() {}
	
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
	
}
