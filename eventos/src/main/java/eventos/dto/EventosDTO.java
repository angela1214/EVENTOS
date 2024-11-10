package eventos.dto;

import java.io.Serializable;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.modelo.EstadoEspacio;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoDeInteres;

@SuppressWarnings("serial")
public class EventosDTO implements Serializable {

	private String id;
	private String nombre;
	private String descripcion;
	private String organizador;
	private int plazas;
	private boolean cancelado;
	private Categoria categoria;
	private Ocupacion ocupacion;

    public EventosDTO(String id, String nombre, String descripcion, String organizador, int plazas, boolean cancelado,
    		Categoria categoria, Ocupacion ocupacion) {
       
    	this.id = id;
    	this.nombre = nombre;
    	this.descripcion = descripcion;
    	this.organizador = organizador;
    	this.plazas = plazas;
    	this.cancelado = cancelado;
    	this.categoria = categoria;
    	this.ocupacion = ocupacion;
    	
    }

	public String getId() {
        return id;
    }
	
    public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getOrganizador() {
		return organizador;
	}

	public int getPlazas() {
		return plazas;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

}
