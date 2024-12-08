package eventos.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import repositorio.RepositorioException;
import repositorio.RepositorioJPA;
import utils.EntityManagerHelper;


public class RepositorioEventosAdHocJPA extends RepositorioJPA<Evento> implements RepositorioEventosAdHoc {
	
	@Override
	public Class<Evento> getClase() {
		return Evento.class;
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<Evento> getEventosMes(int mes, int anio) throws RepositorioException {
		
		EntityManager em = EntityManagerHelper.getEntityManager();
	    
		try {
	         
	        final String queryString = "SELECT e FROM Evento e JOIN e.ocupacion o WHERE FUNCTION('YEAR', o.fechaInicio) = :anio AND FUNCTION('MONTH', o.fechaInicio) = :mes";

			Query query = em.createQuery(queryString);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			query.setParameter("anio", anio);
	        query.setParameter("mes", mes);	 
			
			List<Evento> eventos = query.getResultList();
  
			return eventos;
				
		} catch (Exception e) {
	        throw new RepositorioException("Error al buscar las entidades en el mes " + mes + " del año " + anio, e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<Evento> getEventosByOrganizador(String organizador) throws RepositorioException {

		EntityManager em = EntityManagerHelper.getEntityManager();
	    
		try {
			
	        final String queryString = "SELECT e " + 
	        						   "FROM Evento e " +
	        						   "WHERE e.organizador = :organizador";

			Query query = em.createQuery(queryString);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			query.setParameter("organizador", organizador);
			
			
			List<Evento> eventos = query.getResultList();
  
			return eventos;
				
		} catch (Exception e) {
	        throw new RepositorioException("Error al buscar las entidades del organizador " + organizador);
		}
		
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

}
