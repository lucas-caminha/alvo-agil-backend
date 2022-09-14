package br.com.ucsal.meta.agil.dto;

import java.time.LocalDate;

import br.com.ucsal.meta.agil.entity.ParticipanteEntity;

public class ParticipanteDTO {
	private Long cdParticipante;
	private String nmParticipante;
	private String flParticipante;
	private LocalDate dtInicioParticipante;
	private LocalDate dtFimParticipante;
	private String emailParticipante;
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
	
	public ParticipanteEntity toEntity() {
		ParticipanteEntity participante = new ParticipanteEntity();
		participante.setCdParticipante(this.cdParticipante);
		participante.setNmParticipante(this.nmParticipante);
		participante.setFlParticipante(this.flParticipante);
		participante.setDtInicioParticipante(this.dtInicioParticipante);
		participante.setDtFimParticipante(this.dtFimParticipante);
		participante.setEmailParticipante(this.emailParticipante);

		
		return participante;
	}
}



