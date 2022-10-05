package br.com.ucsal.meta.agil.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.TecnologiaEntity;
import br.com.ucsal.meta.agil.entity.TimeEntity;

public class TimeDTO {
	
	private Long cdTime;
	@NotNull
	private String nmTime;
	private String flTime;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String dtInicioTime;
	private List<CerimoniaDTO> cerimonias;
	private FrameworkDTO framework;
	private List<TecnologiaDTO> tecnologias;
	private List<PerguntaDTO> perguntas;
	
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
	public FrameworkDTO getFramework() {
		return framework;
	}
	public void setFramework(FrameworkDTO framework) {
		this.framework = framework;
	}
	public List<CerimoniaDTO> getCerimonias() {
		return cerimonias;
	}
	public void setCerimonias(List<CerimoniaDTO> cerimonias) {
		this.cerimonias = cerimonias;
	}
	public List<TecnologiaDTO> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(List<TecnologiaDTO> tecnologias) {
		this.tecnologias = tecnologias;
	}
	public List<PerguntaDTO> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<PerguntaDTO> perguntas) {
		this.perguntas = perguntas;
	}
	public TimeEntity toEntity() {
		TimeEntity time = new TimeEntity();
		time.setCdTime(this.cdTime);
		time.setNmTime(this.nmTime);
		time.setFlTime(this.flTime);
		//time.setDtInicioTime(this.dtInicioTime);
		time.setCerimonias(dtoToCerimonias());
		time.setTecnologias(dtoToTecnologias());
		time.setFramework(this.framework.toEntity());
		time.setPerguntas(dtoToPerguntas());		
		return time;
	}	
	
	private List<CerimoniaEntity> dtoToCerimonias() { 
		List<CerimoniaEntity> cerimonias = new ArrayList<CerimoniaEntity>();
		for(CerimoniaDTO dto : this.cerimonias) {
			cerimonias.add(dto.toEntity());
		}
		return cerimonias;
	}
	
	private List<TecnologiaEntity> dtoToTecnologias() { 
		List<TecnologiaEntity> tecnologias = new ArrayList<TecnologiaEntity>();
		for(TecnologiaDTO dto : this.tecnologias) {
			tecnologias.add(dto.toEntity());
		}
		return tecnologias;
	}
	
	private List<PerguntaEntity> dtoToPerguntas() { 
		List<PerguntaEntity> perguntas = new ArrayList<PerguntaEntity>();
		for(PerguntaDTO dto : this.perguntas) {
			perguntas.add(dto.toEntity());
		}
		return perguntas;
	}
}
