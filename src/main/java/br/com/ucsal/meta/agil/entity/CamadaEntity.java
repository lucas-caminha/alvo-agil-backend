package br.com.ucsal.meta.agil.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "camada")
public class CamadaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdCamada;
	private String nmCamada;
	private String flCamada;
	@JsonManagedReference
	@OneToMany(mappedBy = "camada")
	private List<TemaEntity> temas;
	@JsonIgnore
	@ManyToMany(mappedBy = "camadas")
	private List<AplicacaoEntity> aplicacoes;	
	@JsonIgnore
	@Transient
	private Double notaCamada;
	
	public CamadaEntity() {
		aplicacoes = new ArrayList<AplicacaoEntity>();
	}

	public Long getCdCamada() {
		return cdCamada;
	}
	public void setCdCamada(Long cdCamada) {
		this.cdCamada = cdCamada;
	}
	public String getNmCamada() {
		return nmCamada;
	}
	public void setNmCamada(String nmCamada) {
		this.nmCamada = nmCamada;
	}
	public String getFlCamada() {
		return flCamada;
	}
	public void setFlCamada(String flCamada) {
		this.flCamada = flCamada;
	}
	public List<TemaEntity> getTemas() {
		return temas;
	}
	public void setTemas(List<TemaEntity> temas) {
		this.temas = temas;
	}
	@JsonBackReference
	public List<AplicacaoEntity> getAplicacoes() {
		return aplicacoes;
	}
	public void setAplicacoes(List<AplicacaoEntity> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}
	@JsonIgnore
	public Double getNotaCamada() {
		return notaCamada;
	}
	public void setNotaCamada(Double notaCamada) {
		this.notaCamada = notaCamada;
	}	
}
