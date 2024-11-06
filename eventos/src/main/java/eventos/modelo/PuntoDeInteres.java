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
	
	private int distancia;
	
	@Lob
	private String urlWikipedia;
	
	public PuntoDeInteres() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
