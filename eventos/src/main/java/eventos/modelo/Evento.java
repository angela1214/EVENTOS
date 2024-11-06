package eventos.modelo;

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
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String id;
	
	private String nombre;
	
	@Lob
	private String descripcion;
	
	private String organizador;
	
	private int plazas;
	
	private boolean cancelado;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@OneToOne
	@JoinColumn(name="ocupacion_fk")
	private Ocupacion ocupacion;
	
	public Evento() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
