package eventos.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (EspacioFisico).
 */
public interface RepositorioEspaciosFisicosAdHoc extends RepositorioString<EspacioFisico> {

	// Consultas concretas que se quieran tener definidas
	
	public List<Ocupacion> getOcupacionesDeEspacioFisico (String espacio) throws RepositorioException;
	
	public List<Ocupacion> getOcupacionesActivasDeEspacioFisico (String espacio) throws RepositorioException;
	
	public List<EspacioFisico> getEspaciosFisicosLibres (LocalDateTime fecha_inicio, LocalDateTime fecha_fin, int capacidad_min) throws RepositorioException;

}
