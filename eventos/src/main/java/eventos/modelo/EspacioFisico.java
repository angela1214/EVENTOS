package eventos.modelo;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import repositorio.Identificable;

@Entity
@Table(name="espacio_fisico")
public class EspacioFisico implements Identificable {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String id;

	private String nombre;
	
	private String propietario;
	
	private int capacidad;
	
	private String direccion;
	
	private double longitud;
	
	private double latitud;
	
	@ManyToMany
	@JoinTable(name = "espacio_fisico_ocupacion", joinColumns = { 
	@JoinColumn(name = "espacio_fisico_fk") }, 
	inverseJoinColumns = { @JoinColumn(name = "punto_interes_fk") })
	private  LinkedList<PuntoDeInteres> puntosDeInteres;
	
	@Lob
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private EstadoEspacio estado;
	
	public EspacioFisico () {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
