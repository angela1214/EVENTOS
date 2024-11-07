package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import eventos.modelo.Categoria;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEventos {

	String alta(String nombre, String descripcion, String organizador, int plazas, Categoria categoria, LocalDateTime fecha_inicio, LocalDateTime fecha_fin, String id_espacio_fisico) throws RepositorioException, EntidadNoEncontrada;
	
	void modificar(String id, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_fin, int plazas, String id_espacio_fisico) throws RepositorioException, EntidadNoEncontrada;
	
	void cancelar(String id) throws RepositorioException, EntidadNoEncontrada;
	
	LinkedList<EventoResumen> getListadoResumen(int mes, int ano) throws RepositorioException, EntidadNoEncontrada;

}