package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.Categoria;

public class EventoResumen {

	private String id;
	private String nombre;
	private String descripcion;
	private LocalDateTime fechaIncio;
	private Categoria categoria;
	private String nombre_espacioFisico;
	private String direccion_espacioFisico;
	private  List<String> nombre_puntosDeInteres_espacioFisico;
	
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


	public LocalDateTime getFechaIncio() {
		return fechaIncio;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public String getNombre_espacioFisico() {
		return nombre_espacioFisico;
	}


	public String getDireccion_espacioFisico() {
		return direccion_espacioFisico;
	}


	public List<String> getNombre_puntosDeInteres_espacioFisico() {
		return nombre_puntosDeInteres_espacioFisico;
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


	public void setFechaIncio(LocalDateTime fechaIncio) {
		this.fechaIncio = fechaIncio;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public void setNombre_espacioFisico(String nombre_espacioFisico) {
		this.nombre_espacioFisico = nombre_espacioFisico;
	}


	public void setDireccion_espacioFisico(String direccion_espacioFisico) {
		this.direccion_espacioFisico = direccion_espacioFisico;
	}


	public void setNombre_puntosDeInteres_espacioFisico(List<String> nombre_puntosDeInteres_espacioFisico) {
		this.nombre_puntosDeInteres_espacioFisico = nombre_puntosDeInteres_espacioFisico;
	}
	
}
