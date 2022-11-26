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
	private List<String> cerimonias;
	private String framework;
	private List<String> tecnologias;
	private List<String> participantes;
	
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
	public List<String> getCerimonias() {
		return cerimonias;
	}
	public void setCerimonias(List<String> cerimonias) {
		this.cerimonias = cerimonias;
	}
	public String getFramework() {
		return framework;
	}
	public void setFramework(String framework) {
		this.framework = framework;
	}
	public List<String> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(List<String> tecnologias) {
		this.tecnologias = tecnologias;
	}
	public List<String> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<String> participantes) {
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
		time.setParticipantes(stringToParticipantes());
		return time;
	}	
	
	private List<ParticipanteEntity> stringToParticipantes() { 
		List<ParticipanteEntity> participantes = new ArrayList<ParticipanteEntity>();
		for(String cdParticipante : this.participantes) {
			ParticipanteEntity entity = new ParticipanteEntity();
			entity.setCdParticipante(converteStringParaLong(cdParticipante));
			participantes.add(entity);
		}
		return participantes;
	}

	private FrameworkEntity stringToFramework() {
		FrameworkEntity entity = new FrameworkEntity();
		entity.setCdFramework(converteStringParaLong(framework));
		return entity;
	}
	
	private List<CerimoniaEntity> StringToCerimonias() { 
		List<CerimoniaEntity> cerimonias = new ArrayList<CerimoniaEntity>();
		for(String cdCerimonia : this.cerimonias) {
			CerimoniaEntity entity = new CerimoniaEntity();
			entity.setCdCerimonia(converteStringParaLong(cdCerimonia));
			cerimonias.add(entity);
		}
		return cerimonias;
	}
	
	private List<TecnologiaEntity> StringToTecnologias() { 
		List<TecnologiaEntity> tecnologias = new ArrayList<TecnologiaEntity>();
		for(String cdTecnologia : this.tecnologias) {
			TecnologiaEntity entity = new TecnologiaEntity();
			entity.setCdTecnologia(converteStringParaLong(cdTecnologia));
			tecnologias.add(entity);
		}
		return tecnologias;
	}
	
	private Long converteStringParaLong(String id) {
		try {
			if(id != null && !id.isEmpty()) {
				return Long.parseLong(id);
			}		
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}
	
}
