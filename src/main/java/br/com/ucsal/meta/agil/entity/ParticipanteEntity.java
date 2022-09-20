package br.com.ucsal.meta.agil.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "participante")
public class ParticipanteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdParticipante;
	private String nmParticipante;
	private String flParticipante;
	private LocalDate dtInicioParticipante;
	private LocalDate dtFimParticipante;
	private String emailParticipante;
	@ManyToOne
	@JoinColumn(name = "cdTime")
	private TimeEntity time;
	@ManyToOne
	@JoinColumn(name = "cdFuncao")
	private FuncaoEntity funcao;

	public ParticipanteEntity() {
	}

	public Long getCdParticipante() {
		return cdParticipante;
	}

	public void setCdParticipante(Long cdParticipante) {
		this.cdParticipante = cdParticipante;
	}

	public String getNmParticipante() {
		return nmParticipante;
	}

	public void setNmParticipante(String nmParticipante) {
		this.nmParticipante = nmParticipante;
	}

	public String getFlParticipante() {
		return flParticipante;
	}

	public void setFlParticipante(String flParticipante) {
		this.flParticipante = flParticipante;
	}

	public LocalDate getDtInicioParticipante() {
		return dtInicioParticipante;
	}

	public void setDtInicioParticipante(LocalDate dtInicioParticipante) {
		this.dtInicioParticipante = dtInicioParticipante;
	}

	public LocalDate getDtFimParticipante() {
		return dtFimParticipante;
	}

	public void setDtFimParticipante(LocalDate dtFimParticipante) {
		this.dtFimParticipante = dtFimParticipante;
	}

	public String getEmailParticipante() {
		return emailParticipante;
	}

	public void setEmailParticipante(String emailParticipante) {
		this.emailParticipante = emailParticipante;
	}

	public TimeEntity getTime() {
		return time;
	}

	public void setTime(TimeEntity time) {
		this.time = time;
	}

	public FuncaoEntity getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncaoEntity funcao) {
		this.funcao = funcao;
	}

}
