package br.com.ucsal.meta.agil.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.util.DataUtils;

public class ParticipanteDTO {
	
	private Long cdParticipante;
	@NotNull
	private String nmParticipante;
	private String flParticipante;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String dtInicioParticipante;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String dtFimParticipante;
	@NotNull
	private String emailParticipante;
	private FuncaoDTO funcao;
	
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
	public String getDtInicioParticipante() {
		return dtInicioParticipante;
	}
	public void setDtInicioParticipante(String dtInicioParticipante) {
		this.dtInicioParticipante = dtInicioParticipante;
	}
	public String getDtFimParticipante() {
		return dtFimParticipante;
	}
	public void setDtFimParticipante(String dtFimParticipante) {
		this.dtFimParticipante = dtFimParticipante;
	}
	public String getEmailParticipante() {
		return emailParticipante;
	}
	public void setEmailParticipante(String emailParticipante) {
		this.emailParticipante = emailParticipante;
	}
	public FuncaoDTO getFuncao() {
		return funcao;
	}
	public void setFuncao(FuncaoDTO funcao) {
		this.funcao = funcao;
	}
	
	public ParticipanteEntity toEntity() {
		ParticipanteEntity participante = new ParticipanteEntity();
		participante.setCdParticipante(this.cdParticipante);
		participante.setNmParticipante(this.nmParticipante);
		participante.setFlParticipante(this.flParticipante);
		participante.setDtInicioParticipante(DataUtils.stringToLocalDate(this.dtInicioParticipante));
		participante.setDtFimParticipante(DataUtils.stringToLocalDate(this.dtFimParticipante));
		participante.setEmailParticipante(this.emailParticipante);
		participante.setFuncao(this.funcao.toEntity());
		return participante;
	}
}



