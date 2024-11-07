package eventos.repositorio;

import eventos.modelo.EspacioFisico;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (EspacioFisico).
 */
public interface RepositorioEspaciosFisicosAdHoc extends RepositorioString<EspacioFisico> {

	/*
		Consultas concretas que se quieran tener definidas
	
	public default List<Encuesta> getByActivas() throws RepositorioException {
	}
	
	public List<Encuesta> getBySinVotos();
	
	public List<Encuesta> getByNumeroOpcionesMayorQue(int numero);
	
	*/
	
}
