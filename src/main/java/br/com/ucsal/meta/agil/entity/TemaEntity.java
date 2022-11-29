package br.com.ucsal.meta.agil.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "tema")
public class TemaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdTema;
	private String nmTema;
	private String flTema;
	@JsonManagedReference
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "temapergunta", joinColumns = @JoinColumn(name = "cdPergunta", referencedColumnName = "cdTema"), 
	inverseJoinColumns = @JoinColumn(name = "cdTema", referencedColumnName = "cdPergunta"))
	private List<PerguntaEntity> perguntas;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cdCamada")
	private CamadaEntity camada;
	
	public TemaEntity() {
		camada = new CamadaEntity();
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
	public List<PerguntaEntity> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<PerguntaEntity> perguntas) {
		this.perguntas = perguntas;
	}
	@JsonBackReference
	public CamadaEntity getCamada() {
		return camada;
	}
	public void setCamada(CamadaEntity camada) {
		this.camada = camada;
	}
	
}
