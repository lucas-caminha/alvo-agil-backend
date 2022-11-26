package br.com.ucsal.meta.agil.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "time")
public class TimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdTime;
	private String nmTime;
	private String flTime;
	private LocalDate dtInicioTime;
	private LocalDate dtFinalizacaoTime;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "timecerimonia", joinColumns = @JoinColumn(name = "cdCerimonia", referencedColumnName = "cdTime"),
			inverseJoinColumns = @JoinColumn(name = "cdTime", referencedColumnName = "cdCerimonia"))
	private List<CerimoniaEntity> cerimonias;
	@ManyToOne
	@JoinColumn(name = "cdFramework")
	private FrameworkEntity framework;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "timetecnologia", joinColumns = @JoinColumn(name = "cdTecnologia", referencedColumnName = "cdTime"), 
				inverseJoinColumns = @JoinColumn(name = "cdTime", referencedColumnName = "cdTecnologia"))
	private List<TecnologiaEntity> tecnologias;	
	@JsonManagedReference
	@OneToMany(mappedBy = "time", fetch = FetchType.EAGER)
	private List<ParticipanteEntity> participantes;
	
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
	public LocalDate getDtFinalizacaoTime() {
		return dtFinalizacaoTime;
	}
	public void setDtFinalizacaoTime(LocalDate dtFinalizacaoTime) {
		this.dtFinalizacaoTime = dtFinalizacaoTime;
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
	public List<ParticipanteEntity> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<ParticipanteEntity> participantes) {
		this.participantes = participantes;
	}
		
}
