package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	@ManyToOne
	@JoinColumn(name = "cdAplicacao")
	private AplicacaoEntity aplicacao;
	
	public CamadaEntity() {
		aplicacao = new AplicacaoEntity();
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
	public AplicacaoEntity getAplicacao() {
		return aplicacao;
	}
	public void setAplicacao(AplicacaoEntity aplicacao) {
		this.aplicacao = aplicacao;
	}
	
}
