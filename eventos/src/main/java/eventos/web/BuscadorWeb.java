package eventos.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import eventos.modelo.EspacioFisico;
import eventos.servicio.IServicioEspaciosFisicos;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class BuscadorWeb implements Serializable {
	
    private Date fecha_inicio;
    private Date fecha_fin;
    private Integer capacidad = 0;
    private List<EspacioFisico> espacios = new ArrayList<>();
    private boolean first = true;

    private IServicioEspaciosFisicos servicioEspaciosFisicos;

    public BuscadorWeb() throws RepositorioException {
        this.servicioEspaciosFisicos = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
    }
    
    public void buscarEspacio() {
    	
        // comprobación de campos
        try {
        	
        	LocalDateTime fechaInicio = null;
        	LocalDateTime fechaFin = null;
        	
        	if(fecha_inicio != null && fecha_fin != null) {
        		fechaInicio = LocalDateTime.ofInstant(fecha_inicio.toInstant(), ZoneId.systemDefault());
                fechaFin =  LocalDateTime.ofInstant(fecha_fin.toInstant(), ZoneId.systemDefault());
        	}
        	
			this.espacios = this.servicioEspaciosFisicos.buscarEspaciosFisicosByFecha(fechaInicio, fechaFin, this.capacidad);
			this.first = false;
			
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public List<EspacioFisico> getEspacios() {
		return espacios;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public void setEspacios(List<EspacioFisico> espacios) {
		this.espacios = espacios;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	
}