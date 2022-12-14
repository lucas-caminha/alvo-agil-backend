package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;

public class TemaDTO {
	
	private Long cdTema;
	private String nmTema;
	private String flTema;
	private Integer cdCamada;
	private List<PerguntaDTO> perguntas;
	
	public TemaDTO() {
		this.perguntas = new ArrayList<PerguntaDTO>();
	}
	
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
	public List<PerguntaDTO> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<PerguntaDTO> perguntas) {
		this.perguntas = perguntas;
	}
	public Integer getCdCamada() {
		return cdCamada;
	}
	public void setCdCamada(Integer cdCamada) {
		this.cdCamada = cdCamada;
	}

	public TemaEntity toEntity() {
		TemaEntity tema = new TemaEntity();
		tema.setCdTema(this.cdTema);
		tema.setNmTema(this.nmTema);
		tema.setFlTema(this.flTema);
		tema.getCamada().setCdCamada(this.cdCamada.longValue());
		tema.setPerguntas(dtoToPerguntas());
		return tema;
	}

	private List<PerguntaEntity> dtoToPerguntas() {
		List<PerguntaEntity> perguntas = new ArrayList<PerguntaEntity>();
		for(PerguntaDTO dto : this.perguntas) {
			perguntas.add(dto.toEntity());
		}
		return perguntas;
	}

}
