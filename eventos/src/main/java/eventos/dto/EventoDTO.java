package eventos.dto;

import java.io.Serializable;
import eventos.modelo.Categoria;

@SuppressWarnings("serial")
public class EventoDTO implements Serializable {

	private String id;
	private String nombre;
	private String descripcion;
	private String organizador;
	private int plazas;
	private boolean cancelado;
	private Categoria categoria;
	private OcupacionDTO ocupacion;

    public EventoDTO(String id, String nombre, String descripcion, String organizador, int plazas, boolean cancelado,
    		Categoria categoria, OcupacionDTO ocupacion) {
       
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

	public OcupacionDTO getOcupacion() {
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

	public void setOcupacion(OcupacionDTO ocupacion) {
		this.ocupacion = ocupacion;
	}

	@Override
	public String toString() {
		return "EventosDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", organizador="
				+ organizador + ", plazas=" + plazas + ", cancelado=" + cancelado + ", categoria=" + categoria
				+ ", ocupacion=" + ocupacion + "]";
	}

}