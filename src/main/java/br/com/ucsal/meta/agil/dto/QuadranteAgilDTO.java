package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.QuadranteAgilEntity;

public class QuadranteAgilDTO {
	
	private Long cdQuadrante;
	private String nmQuadrante;
	private String flQuadrante;
	private List<PerguntaDTO> perguntas;
	
	public QuadranteAgilDTO() {}
	
	public Long getCdQuadrante() {
		return cdQuadrante;
	}
	public void setCdQuadrante(Long cdQuadrante) {
		this.cdQuadrante = cdQuadrante;
	}
	public String getNmQuadrante() {
		return nmQuadrante;
	}
	public void setNmQuadrante(String nmQuadrante) {
		this.nmQuadrante = nmQuadrante;
	}
	public String getFlQuadrante() {
		return flQuadrante;
	}
	public void setFlQuadrante(String flQuadrante) {
		this.flQuadrante = flQuadrante;
	}
	public List<PerguntaDTO> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<PerguntaDTO> perguntas) {
		this.perguntas = perguntas;
	}
	
	public QuadranteAgilEntity toEntity() {
		QuadranteAgilEntity entity = new QuadranteAgilEntity();
		entity.setCdQuadrante(this.cdQuadrante);
		entity.setNmQuadrante(this.nmQuadrante);
		entity.setFlQuadrante(this.flQuadrante);
		entity.setPerguntas(dtoToPerguntas());
		return entity;
	}
	
	private List<PerguntaEntity> dtoToPerguntas() { 
		List<PerguntaEntity> perguntas = new ArrayList<PerguntaEntity>();
		for(PerguntaDTO dto : this.perguntas) {
			perguntas.add(dto.toEntity());
		}
		return perguntas;
	}

}
