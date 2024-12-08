package eventos.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class OcupacionDTO implements Serializable {

	private String id;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private EspacioFisicoDTO espacioFisicoDTO;
	
    public OcupacionDTO(String id, LocalDateTime fechaInicio, LocalDateTime fechaFin, EspacioFisicoDTO espacioFisico) {
       
    	this.id = id;
    	this.fechaInicio = fechaInicio;
    	this.fechaFin = fechaFin;
    	this.espacioFisicoDTO = espacioFisico;
    	
    }

	public String getId() {
		return id;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public EspacioFisicoDTO getEspacioFisicoDTO() {
		return espacioFisicoDTO;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setEspacioFisicoDTO(EspacioFisicoDTO espacioFisicoDTO) {
		this.espacioFisicoDTO = espacioFisicoDTO;
	}

	@Override
	public String toString() {
		return "OcupacionDTO [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", espacioFisicoDTO=" + espacioFisicoDTO + "]";
	}

}
