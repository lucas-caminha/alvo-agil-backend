package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "quadranteagil")
public class QuadranteAgilEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdQuadrante;
	private String nmQuadrante;
	private String flQuadrante;
	@OneToMany
	@JoinColumn(name = "cdPergunta")
	private List<PerguntaEntity> perguntas;
	
	public QuadranteAgilEntity() {}
	
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
	public List<PerguntaEntity> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<PerguntaEntity> perguntas) {
		this.perguntas = perguntas;
	}
	
}
