package eventos.repositorio;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import eventos.modelo.Categoria;
import eventos.modelo.Evento;
import eventos.modelo.Ocupacion;
import repositorio.RepositorioException;
import repositorio.RepositorioJPA;
import utils.EntityManagerHelper;


public class RepositorioEventosAdHocJPA extends RepositorioJPA<Evento> implements RepositorioEventosAdHoc {
	
	@Override
	public Class<Evento> getClase() {
		return Evento.class;
	}

	@Override
	public boolean removeOcupacion(String id) throws RepositorioException {
		
		EntityManager em = EntityManagerHelper.getEntityManager();	
		EntityTransaction transaction = em.getTransaction();
		
		try {
			
			transaction.begin();
			
			String updateEventosQuery = "UPDATE Evento e SET e.ocupacion = NULL WHERE e.ocupacion.id = :id";
	        Query queryEventos = em.createQuery(updateEventosQuery);
	        queryEventos.setParameter("id", id);
	        queryEventos.executeUpdate();

			String queryString = "DELETE FROM Ocupacion o WHERE o.id = :id";

			Query query = em.createQuery(queryString);
	        query.setParameter("id", id);
			int filasBorradas = query.executeUpdate();
			
			transaction.commit();
			
			return filasBorradas==1;
				
		} catch (Exception e) {

			if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw new RepositorioException("Error al borrar la ocupación con id " + id, e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> getEventosMes(int mes, int ano) throws RepositorioException {
		
		EntityManager em = EntityManagerHelper.getEntityManager();
	    
		try {
						
			LocalDateTime inicioMes = LocalDateTime.of(ano, mes, 1, 0, 0, 0, 0);
	        
	        YearMonth yearMonth = YearMonth.of(ano, mes);
	        int ultimoDiaMes = yearMonth.lengthOfMonth();
	        
	        LocalDateTime finMes = LocalDateTime.of(ano, mes, ultimoDiaMes, 23, 59, 59, 99);
	      
	        Query query = em.createNamedQuery("Evento.getEventosMes");
	        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
	        
	        List<Evento> eventos = query.getResultList();
	        
	        /*
	         * Problemas al realizar la consulta con JPQL (solo me devuelve una fila).
	         * Ocurre lo mismo con las nombradas, solo se devuelve la primera fila.
	         * 
	         
			final String queryString = "SELECT e FROM Evento e JOIN e.ocupacion o WHERE o.fechaFin BETWEEN :inicio AND :fin";

			Query query = em.createQuery(queryString);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			query.setParameter("inicio", inicioMes);
	        query.setParameter("fin", finMes);	 
			
			List<Evento> eventos = query.getResultList();
			
			*/
  
			return eventos;
				
		} catch (Exception e) {
	        throw new RepositorioException("Error al buscar las entidades en el mes " + mes + " del año " + ano, e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

}
