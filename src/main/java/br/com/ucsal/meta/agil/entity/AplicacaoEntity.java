package br.com.ucsal.meta.agil.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "aplicacao")
public class AplicacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdAplicacao;
	private String nmAplicacao;
	private String flAplicacao;
	@JsonManagedReference
	@OneToMany(mappedBy = "aplicacao")
	private List<CamadaEntity> camadas;
	
	public Long getCdAplicacao() {
		return cdAplicacao;
	}
	public void setCdAplicacao(Long cdAplicacao) {
		this.cdAplicacao = cdAplicacao;
	}
	public String getNmAplicacao() {
		return nmAplicacao;
	}
	public void setNmAplicacao(String nmAplicacao) {
		this.nmAplicacao = nmAplicacao;
	}
	public String getFlAplicacao() {
		return flAplicacao;
	}
	public void setFlAplicacao(String flAplicacao) {
		this.flAplicacao = flAplicacao;
	}
	public List<CamadaEntity> getCamadas() {
		return camadas;
	}
	public void setCamadas(List<CamadaEntity> camadas) {
		this.camadas = camadas;
	}
	
}
