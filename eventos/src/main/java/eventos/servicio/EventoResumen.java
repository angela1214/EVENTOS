package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.modelo.PuntoDeInteres;

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
	
	public void setId(String id) {
		this.id = id;
	}
	
}
