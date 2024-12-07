package eventos.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import eventos.modelo.EspacioFisico;
import eventos.servicio.IServicioEspaciosFisicos;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ListadoEspaciosWeb implements Serializable {

	private List<EspacioFisico> espacios = new ArrayList<>();
	private String id_espacio;
	
	private IServicioEspaciosFisicos servicioEspacios;
	
	public ListadoEspaciosWeb() throws RepositorioException {
		this.servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
		this.espacios = this.servicioEspacios.getAll();
	}

	public void cambiarEstado(String id, String estado) throws RepositorioException, EntidadNoEncontrada {
		if(estado == "ACTIVO") {
			this.servicioEspacios.baja(id);
			
		} else {
			this.servicioEspacios.activar(id);
		}
		this.espacios = this.servicioEspacios.getAll();
	}

	public List<EspacioFisico> getEspacios() {
		return espacios;
	}

	public String getId_espacio() {
		return id_espacio;
	}

	public void setEspacios(List<EspacioFisico> espacios) {
		this.espacios = espacios;
	}

	public void setId_espacio(String id_espacio) {
		this.id_espacio = id_espacio;
	}

}
