package br.com.ucsal.meta.agil.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name =  "vavaliacaonota")
@Immutable
public class AvaliacaoNotaView {

	@Id
	private Integer cdavaliacao;
	private String nmcamada;
	private String nmtema;
	private Double nota;
	
	public Integer getCdavaliacao() {
		return cdavaliacao;
	}
	public void setCdavaliacao(Integer cdavaliacao) {
		this.cdavaliacao = cdavaliacao;
	}
	public String getNmcamada() {
		return nmcamada;
	}
	public void setNmcamada(String nmcamada) {
		this.nmcamada = nmcamada;
	}
	public String getNmtema() {
		return nmtema;
	}
	public void setNmtema(String nmtema) {
		this.nmtema = nmtema;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}

}
