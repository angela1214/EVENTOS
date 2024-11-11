package eventos.dto;

import java.io.Serializable;
import java.util.List;


import eventos.modelo.EstadoEspacio;
import eventos.modelo.PuntoDeInteres;

@SuppressWarnings("serial")
public class EspacioFisicoDTO implements Serializable {

	private String id;
	private String nombre;
	private String propietario;
	private int capacidad;
	private String direccion;
	private double latitud;
	private double longitud;
	private  List<PuntoDeInteres> puntosDeInteres;
	private String descripcion;
	private EstadoEspacio estado;

    public EspacioFisicoDTO(String id, String nombre, String propietario, int capacidad, String direccion,
    		double latitud, double longitud, List<PuntoDeInteres> puntos, String descripcion, EstadoEspacio estado) {
       
    	this.id = id;
    	this.nombre = nombre;
    	this.propietario = propietario;
    	this.capacidad = capacidad;
    	this.direccion = direccion;
    	this.latitud = latitud;
    	this.longitud = longitud;
    	this.puntosDeInteres = puntos;
    	this.descripcion = descripcion;
    	this.estado = estado;
    	
    }

	public String getId() {
        return id;
    }
	
    public String getNombre() {
		return nombre;
	}

	public String getPropietario() {
		return propietario;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public List<PuntoDeInteres> getPuntosDeInteres() {
		return puntosDeInteres;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public EstadoEspacio getEstado() {
		return estado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public void setPuntosDeInteres(List<PuntoDeInteres> puntosDeInteres) {
		this.puntosDeInteres = puntosDeInteres;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(EstadoEspacio estado) {
		this.estado = estado;
	}

	public void setId(String id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "EspacioFisicoDTO [id=" + id + ", nombre=" + nombre + ", propietario=" + propietario + ", capacidad="
				+ capacidad + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", puntosDeInteres=" + puntosDeInteres + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}
	
	

}
