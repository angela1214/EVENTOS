package eventos.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import repositorio.Identificable;
import utils.LocalDateTimeAdapter;

@Entity
@Table(name="ocupacion")
public class Ocupacion implements Identificable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String id;
	
	@XmlJavaTypeAdapter(value=LocalDateTimeAdapter.class)
	private LocalDateTime fechaInicio;
	
	@XmlJavaTypeAdapter(value=LocalDateTimeAdapter.class)
	private LocalDateTime fechaFin;
	
	@ManyToOne
	@JoinColumn(name="espacio_fisico_fk")
	private EspacioFisico espacioFisico;
	
	public Ocupacion() {
		
	}
	
	// Calculado
	public boolean activa() {
		return false;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	

}
