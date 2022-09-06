package br.com.ucsal.meta.agil.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "time")
public class TimeEntity {

	@Id
	private Long cdTime;
	private String nmTime;
	private String flTime;
	private LocalDate dtInicioTime;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "timecerimonia", joinColumns = @JoinColumn(name = "cdCerimonia", referencedColumnName = "cdTime"),
			inverseJoinColumns = @JoinColumn(name = "cdTime", referencedColumnName = "cdCerimonia"))
	private List<CerimoniaEntity> cerimonias;
	@ManyToOne
	@JoinColumn(name = "cdFramework")
	private FrameworkEntity framework;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "timetecnologia", joinColumns = @JoinColumn(name = "cdTecnologia", referencedColumnName = "cdTime"), 
				inverseJoinColumns = @JoinColumn(name = "cdTime", referencedColumnName = "cdTecnologia"))
	private List<TecnologiaEntity> tecnologias;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "timepergunta", joinColumns = @JoinColumn(name = "cdPergunta", referencedColumnName = "cdTime"), 
				inverseJoinColumns = @JoinColumn(name = "cdTime", referencedColumnName = "cdPergunta"))
	private List<PerguntaEntity> perguntas;
	
	public TimeEntity() {}
	
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
	
		
}
