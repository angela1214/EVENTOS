package eventos.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import repositorio.Identificable;

@Entity
@Table(name="puntos_de_interes")
public class PuntoDeInteres implements Identificable {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String id;
	
	private String nombre;
	
	@Lob
	private String descripcion;
	
	private double distancia;
	
	@Lob
	private String urlWikipedia;
	
	public PuntoDeInteres() {
		
	}
	
	public PuntoDeInteres(String nombre, String descripcion, double distancia, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.distancia = distancia;
		this.urlWikipedia = url;
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

	public double getDistancia() {
		return distancia;
	}

	public String getUrlWikipedia() {
		return urlWikipedia;
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

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	@Override
	public String toString() {
		return "PuntoDeInteres [nombre=" + nombre + ", descripcion=" + descripcion + ", distancia="
				+ distancia + ", urlWikipedia=" + urlWikipedia + "]";
	}

}
