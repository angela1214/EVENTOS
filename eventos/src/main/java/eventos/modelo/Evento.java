package eventos.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import repositorio.Identificable;

@Entity
@Table(name="evento")
public class Evento implements Identificable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String nombre;
	
	@Lob
	private String descripcion;
	
	private String organizador;
	
	private int plazas;
	
	private boolean cancelado;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ocupacion_fk")
	private Ocupacion ocupacion;
	
	public Evento() {
		
	}
	
	public Evento(String nombre, String descripcion, String organizador, int plazas,
			Categoria categoria, Ocupacion ocupacion ) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.plazas = plazas;
		this.categoria = categoria;
		this.ocupacion = ocupacion;
	}
	
	// Getters
	
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
	
	// Setters
	
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

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", organizador="
				+ organizador + ", plazas=" + plazas + ", cancelado=" + cancelado + ", categoria=" + categoria
				+ ", ocupacion=" + ocupacion + "]";
	}

}
