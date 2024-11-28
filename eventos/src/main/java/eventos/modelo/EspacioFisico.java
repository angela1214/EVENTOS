package eventos.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import repositorio.Identificable;

@Entity
@Table(name="espacio_fisico")
public class EspacioFisico implements Identificable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String nombre;
	
	private String propietario;
	
	private int capacidad;
	
	private String direccion;
	
	private double latitud;
	
	private double longitud;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "espacio_fisico_puntos_de_interes", 
	joinColumns = { @JoinColumn(name = "espacio_fisico_fk") }, 
	inverseJoinColumns = { @JoinColumn(name = "punto_interes_fk") })
	private  List<PuntoDeInteres> puntosDeInteres;
	
	@Lob
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private EstadoEspacio estado;
	
	public EspacioFisico () {
		
	}

	public EspacioFisico (String nombre, String propietario, int capacidad, String direccion, double latitud,
			double longitud, String descripcion) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.descripcion = descripcion;
		this.estado = EstadoEspacio.ACTIVO;
	}
	
	// Getters
	
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
	
	// Setters

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "EspacioFisico [id=" + id + ", nombre=" + nombre + ", propietario=" + propietario + ", capacidad="
				+ capacidad + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", puntosDeInteres=" + puntosDeInteres + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}
	
}
