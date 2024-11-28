package eventos.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import repositorio.Identificable;
import utils.LocalDateTimeAdapter;

@Entity
@Table(name="ocupacion")
public class Ocupacion implements Identificable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	@XmlJavaTypeAdapter(value=LocalDateTimeAdapter.class)
	private LocalDateTime fechaInicio;
	
	@XmlJavaTypeAdapter(value=LocalDateTimeAdapter.class)
	private LocalDateTime fechaFin;
	
	@ManyToOne
	@JoinColumn(name="espacio_fisico_fk")
	private EspacioFisico espacioFisico;
	
	public Ocupacion() {
		
	}
	
	public Ocupacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, EspacioFisico espacioFisico) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.espacioFisico = espacioFisico;
	}
	
	// Calculado
	public boolean activa() {
		return (this.fechaFin.isAfter(LocalDateTime.now()));
	}
	
	// Getters
	
	public String getId() {
		return id;
	}
	
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public EspacioFisico getEspacioFisico() {
		return espacioFisico;
	}
	
	// Setters
	
	public void setId(String id) {
		this.id = id;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setEspacioFisico(EspacioFisico espacioFisico) {
		this.espacioFisico = espacioFisico;
	}

	@Override
	public String toString() {
		return "Ocupacion [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", espacioFisico="
				+ espacioFisico + "]";
	}

}
