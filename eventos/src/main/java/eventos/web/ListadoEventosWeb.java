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

import eventos.dto.EventoDTO;
import eventos.servicio.IServicioEventos;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ListadoEventosWeb implements Serializable {

	private List<EventoDTO> eventos = new ArrayList<>();
	private String organizador;
	
	private IServicioEventos servicioEventos;
	
	@Inject
	private FacesContext facesContext;
	
	public ListadoEventosWeb() throws RepositorioException {
		this.servicioEventos = FactoriaServicios.getServicio(IServicioEventos.class);
	}
	
	@PostConstruct
	public void init() {
		Map<String,Object> sesion = this.facesContext.getExternalContext().getSessionMap();
        if (sesion != null) {
            this.organizador = (String) sesion.get("user");
            try {
				this.getEventosOrganizador();
			} catch (RepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }     
	}
	
	public void getEventosOrganizador() throws RepositorioException {
		if(this.organizador.equals("admin")) {
        	this.eventos = this.servicioEventos.getAll();
        } else if (this.organizador != null) {
        	this.eventos = this.servicioEventos.getEventosByOrganizador(this.organizador);
        }
	}

	public List<EventoDTO> getEventos() {
		return eventos;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setEventos(List<EventoDTO> eventos) {
		this.eventos = eventos;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

}
