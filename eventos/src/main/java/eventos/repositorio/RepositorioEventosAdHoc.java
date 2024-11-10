package eventos.repositorio;

import java.util.List;

import eventos.modelo.Evento;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Eventos).
 */
public interface RepositorioEventosAdHoc extends RepositorioString<Evento> {

	//Consultas concretas que se quieran tener definidas
	
	public boolean removeOcupacion(String id) throws RepositorioException;
	
	public List<Evento> getEventosMes(int mes, int ano) throws RepositorioException; 
		
}
