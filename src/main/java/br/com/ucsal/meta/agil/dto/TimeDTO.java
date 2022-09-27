package br.com.ucsal.meta.agil.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

public class TimeDTO {
	
	private Long cdTime;
	@NotNull
	private String nmTime;
	private String flTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dtInicioTime;
	private List<CerimoniaEntity> cerimonias;
	private FrameworkEntity framework;
	private List<TecnologiaEntity> tecnologias;
	private List<PerguntaEntity> perguntas;
	
	public Long getCdTime() {
		return cdTime;
	}
	public void setCdTime(Long cdTime) {
		this.cdTime = cdTime;
	}
	public String getNmTime() {
		return nmTime;
	}
	public void setNmTime(String nmTime) {
		this.nmTime = nmTime;
	}
	public String getFlTime() {
		return flTime;
	}
	public void setFlTime(String flTime) {
		this.flTime = flTime;
	}
	public LocalDate getDtInicioTime() {
		return dtInicioTime;
	}
	public void setDtInicioTime(LocalDate dtInicioTime) {
		this.dtInicioTime = dtInicioTime;
	}
	public List<CerimoniaEntity> getCerimonias() {
		return cerimonias;
	}
	public void setCerimonias(List<CerimoniaEntity> cerimonias) {
		this.cerimonias = cerimonias;
	}
	public FrameworkEntity getFramework() {
		return framework;
	}
	public void setFramework(FrameworkEntity framework) {
		this.framework = framework;
	}
	public List<TecnologiaEntity> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(List<TecnologiaEntity> tecnologias) {
		this.tecnologias = tecnologias;
	}
	public List<PerguntaEntity> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<PerguntaEntity> perguntas) {
		this.perguntas = perguntas;
	}
	
	public TimeEntity toEntity() {
		TimeEntity time = new TimeEntity();
		time.setCdTime(this.cdTime);
		time.setNmTime(this.nmTime);
		time.setFlTime(this.flTime);
		time.setDtInicioTime(this.dtInicioTime);
		time.setCerimonias(this.cerimonias);
		time.setTecnologias(this.tecnologias);
		time.setFramework(this.framework);
		time.setPerguntas(this.perguntas);		
		return time;
	}	

}
