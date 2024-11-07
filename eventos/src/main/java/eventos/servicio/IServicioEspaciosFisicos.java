package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoDeInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEspaciosFisicos {

	String alta(String nombre, String propietario, int capacidad, String direccion, double latitud, double longitud, String descripcion) throws RepositorioException;
	
	void asignarPuntosDeInteres(String id, LinkedList<PuntoDeInteres> puntosDeInteres) throws RepositorioException, EntidadNoEncontrada;
	
	void modificar(String id, String nombre, int capacidad, String descripcion) throws RepositorioException, EntidadNoEncontrada;
	
	void baja(String id) throws RepositorioException, EntidadNoEncontrada;
	
	void activar(String id) throws RepositorioException, EntidadNoEncontrada;
	
	LinkedList<EspacioFisico> buscarEspaciosFisicosByFecha(LocalDateTime fecha_inicio, LocalDateTime fecha_fin, int capacidad_min) throws RepositorioException, EntidadNoEncontrada;
	
}
