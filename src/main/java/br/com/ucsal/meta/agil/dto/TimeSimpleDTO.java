package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.entity.ParticipanteEntity;
import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;
import br.com.ucsal.meta.agil.util.DataUtils;

public class TimeSimpleDTO {
	
	private Long cdTime;
	@NotNull
	private String nmTime;
	private String flTime;
	private String dtInicioTime;	
	private String dtFinalizacaoTime;
	private List<Integer> cerimonias;
	private Integer framework;
	private List<Integer> tecnologias;
	private List<Integer> participantes;
	
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
	public String getDtInicioTime() {
		return dtInicioTime;
	}
	public void setDtInicioTime(String dtInicioTime) {
		this.dtInicioTime = dtInicioTime;
	}
	public String getDtFinalizacaoTime() {
		return dtFinalizacaoTime;
	}
	public void setDtFinalizacaoTime(String dtFinalizacaoTime) {
		this.dtFinalizacaoTime = dtFinalizacaoTime;
	}
	public List<Integer> getCerimonias() {
		return cerimonias;
	}
	public void setCerimonias(List<Integer> cerimonias) {
		this.cerimonias = cerimonias;
	}
	public Integer getFramework() {
		return framework;
	}
	public void setFramework(Integer framework) {
		this.framework = framework;
	}
	public List<Integer> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(List<Integer> tecnologias) {
		this.tecnologias = tecnologias;
	}
	public List<Integer> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<Integer> participantes) {
		this.participantes = participantes;
	}
	public TimeEntity toEntity() {
		TimeEntity time = new TimeEntity();
		time.setCdTime(this.cdTime);
		time.setNmTime(this.nmTime);
		time.setFlTime(this.flTime);
		time.setDtInicioTime(DataUtils.stringToLocalDate(this.dtInicioTime));
		time.setDtFinalizacaoTime(DataUtils.stringToLocalDate(this.dtFinalizacaoTime));
		time.setCerimonias(StringToCerimonias());
		time.setTecnologias(StringToTecnologias());
		time.setFramework(stringToFramework());	
		time.setParticipantes(getParticipantesList());
		return time;
	}	
	
	private List<ParticipanteEntity> getParticipantesList() { 
		List<ParticipanteEntity> participantes = new ArrayList<ParticipanteEntity>();
		for(Integer cdParticipante : this.participantes) {
			ParticipanteEntity entity = new ParticipanteEntity();
			entity.setCdParticipante(cdParticipante.longValue());
			participantes.add(entity);
		}
		return participantes;
	}

	private FrameworkEntity stringToFramework() {
		FrameworkEntity entity = new FrameworkEntity();
		if(this.framework != null) {
			entity.setCdFramework(this.framework.longValue());
		}
		return entity;
	}
	
	private List<CerimoniaEntity> StringToCerimonias() { 
		List<CerimoniaEntity> cerimonias = new ArrayList<CerimoniaEntity>();
		for(Integer cdCerimonia : this.cerimonias) {
			CerimoniaEntity entity = new CerimoniaEntity();
			entity.setCdCerimonia(cdCerimonia.longValue());
			cerimonias.add(entity);
		}
		return cerimonias;
	}
	
	private List<TecnologiaEntity> StringToTecnologias() { 
		List<TecnologiaEntity> tecnologias = new ArrayList<TecnologiaEntity>();
		for(Integer cdTecnologia : this.tecnologias) {
			TecnologiaEntity entity = new TecnologiaEntity();
			entity.setCdTecnologia(cdTecnologia.longValue());
			tecnologias.add(entity);
		}
		return tecnologias;
	}
	

	
}
