package eventos.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.modelo.PuntoDeInteres;
import eventos.servicio.IServicioEspaciosFisicos;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class AltaEspacioWeb implements Serializable {
	
    private String nombre;
    private String propietario;
    private Integer capacidad = 0;
    private String direccion;
	private Double latitud = 0.00;
	private Double longitud = 0.00;
	private  List<PuntoDeInteres> puntosDeInteres;
	private String descripcion;
	private String id_espacio;
	private boolean creado;
	
	@Inject
	private FacesContext facesContext;
	
    private IServicioEspaciosFisicos servicioEspacios;

    public AltaEspacioWeb() throws RepositorioException {
        this.servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
    }
    
    public void altaEspacio() {
    	
        try {
			this.id_espacio = this.servicioEspacios.alta(this.nombre, this.propietario,
			this.capacidad,this.direccion, this.latitud, this.longitud, this.descripcion);
			this.creado = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Espacio físico [ID="+id_espacio+"]", "Ha sido dado de alta correctamente."));
			this.clearFields();
        } catch (Exception e) {
        	
        	this.creado = false;
        	String title="";
        	String message = e.getMessage();
        
        	String[] partes = e.getMessage().split(":", 2);
            if (partes.length > 0) {
            	title =  partes[0].trim();
                message = partes[1].trim();
            }      
            
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
        }
        
    }
    
    public void clearFields() {
    	this.nombre = null;
        this.propietario = null;
        this.capacidad = 0;
        this.direccion= null ;
    	this.latitud = 0.00;
    	this.longitud = 0.00;
    	this.puntosDeInteres = null;
    	this.descripcion = null;
    	this.id_espacio= null;	
    }

	public String getNombre() {
		return nombre;
	}

	public String getPropietario() {
		return propietario;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public Double getLatitud() {
		return latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public List<PuntoDeInteres> getPuntosDeInteres() {
		return puntosDeInteres;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public void setPuntosDeInteres(List<PuntoDeInteres> puntosDeInteres) {
		this.puntosDeInteres = puntosDeInteres;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isCreado() {
		return creado;
	}

	public void setCreado(boolean creado) {
		this.creado = creado;
	}

	public String getId_espacio() {
		return id_espacio;
	}

	public void setId_espacio(String id_espacio) {
		this.id_espacio = id_espacio;
	}
	
}