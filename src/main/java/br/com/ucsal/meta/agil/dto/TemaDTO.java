package br.com.ucsal.meta.agil.dto;

import java.util.List;

import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;

public class TemaDTO {
	
	private Long cdTema;
	private String nmTema;
	private String flTema;
	private List<PerguntaEntity> perguntas;
	
	public TemaDTO() {}
	
	public Long getCdTema() {
		return cdTema;
	}
	public void setCdTema(Long cdTema) {
		this.cdTema = cdTema;
	}
	public String getNmTema() {
		return nmTema;
	}
	public void setNmTema(String nmTema) {
		this.nmTema = nmTema;
	}
	public String getFlTema() {
		return flTema;
	}
	public void setFlTema(String flTema) {
		this.flTema = flTema;
	}
	public List<PerguntaEntity> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<PerguntaEntity> perguntas) {
		this.perguntas = perguntas;
	}

	public TemaEntity toEntity() {
		TemaEntity tema = new TemaEntity();
		tema.setCdTema(this.cdTema);
		tema.setNmTema(this.nmTema);
		tema.setFlTema(this.flTema);
		tema.setPerguntas(dtoToPerguntas());
		return tema;
	}

	private List<PerguntaEntity> dtoToPerguntas() {

		return null;
	}

}
