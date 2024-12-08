package eventos.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.dto.EspacioFisicoDTO;
import eventos.servicio.IServicioEspaciosFisicos;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ListadoEspaciosWeb implements Serializable {

	private List<EspacioFisicoDTO> espacios = new ArrayList<>();
	private String id_espacio;
	private String propietario;
	
	private IServicioEspaciosFisicos servicioEspacios;
	
	@Inject
	private FacesContext facesContext;
	
	public ListadoEspaciosWeb() throws RepositorioException {
		this.servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
	}
	
	@PostConstruct
	public void init() {
		Map<String,Object> sesion = this.facesContext.getExternalContext().getSessionMap();

        if (sesion != null) {
            this.propietario = (String) sesion.get("user");
            try {
				this.getEspaciosPropietario();
			} catch (RepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }     
	}
	
	public void getEspaciosPropietario() throws RepositorioException {
		if(this.propietario.equals("admin")) {
        	this.espacios = this.servicioEspacios.getAll();
        } else if (this.propietario != null) {
        	this.espacios = this.servicioEspacios.getEspacioFisicoByPropietario(this.propietario);
        }
	}

	public void cambiarEstado(String id, String estado) throws RepositorioException, EntidadNoEncontrada {
		if(estado == "ACTIVO") {
			this.servicioEspacios.baja(id);
			
		} else {
			this.servicioEspacios.activar(id);
		}
        this.getEspaciosPropietario();
	}

	public List<EspacioFisicoDTO> getEspacios() {
		return espacios;
	}

	public String getId_espacio() {
		return id_espacio;
	}

	public void setEspacios(List<EspacioFisicoDTO> espacios) {
		this.espacios = espacios;
	}

	public void setId_espacio(String id_espacio) {
		this.id_espacio = id_espacio;
	}

}
