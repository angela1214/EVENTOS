package eventos.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eventos.modelo.Evento;
import repositorio.RepositorioException;
import repositorio.RepositorioJPA;
import utils.EntityManagerHelper;


public class RepositorioEventosAdHocJPA extends RepositorioJPA<Evento> implements RepositorioEventosAdHoc {

	EntityManager em = EntityManagerHelper.getEntityManager();	
	
	@Override
	public Class<Evento> getClase() {
		return Evento.class;
	}

	@Override
	public boolean removeOcupacion(String id) throws RepositorioException {
		
		String queryString = "DELETE FROM ocupacion o" +
							"WHERE o.id = " + id;
		
		Query query = em.createQuery(queryString);
		int filasBorradas = query.executeUpdate();

	    return filasBorradas==1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> getEventosMes(int mes, int ano) throws RepositorioException {
		
		String queryString = "SELECT * " +
				"FROM evento e " + 
				"JOIN ocupacion o ON e.ocupacion_id = o.id " +
                "WHERE MONTH(o.fecha) = " + mes + " AND YEAR(o.fecha) = " + ano;

		Query query = em.createQuery(queryString);
				
		return query.getResultList();
	}

}
