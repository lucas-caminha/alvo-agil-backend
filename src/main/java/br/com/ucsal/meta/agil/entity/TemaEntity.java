package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "tema")
public class TemaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdTema;
	private String nmTema;
	private String flTema;
	@OneToMany(mappedBy = "tema")
	private List<PerguntaEntity> perguntas;
	@ManyToOne
	@JoinColumn(name = "cdCamada")
	private CamadaEntity camada;
	
	public TemaEntity() {}
	
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
	
}
