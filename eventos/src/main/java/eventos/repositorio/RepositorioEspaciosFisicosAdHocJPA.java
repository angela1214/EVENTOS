package eventos.repositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import eventos.modelo.EspacioFisico;
import eventos.modelo.EstadoEspacio;
import eventos.modelo.Evento;
import eventos.modelo.Ocupacion;
import repositorio.RepositorioException;
import repositorio.RepositorioJPA;
import utils.EntityManagerHelper;


public class RepositorioEspaciosFisicosAdHocJPA extends RepositorioJPA<EspacioFisico> implements RepositorioEspaciosFisicosAdHoc {
	
	@Override
	public Class<EspacioFisico> getClase() {
		return EspacioFisico.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocupacion> getOcupacionesDeEspacioFisico(String espacio) throws RepositorioException {
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		EspacioFisico espacioFisico = em.find(EspacioFisico.class, espacio);
		
		String queryString = "SELECT o " +
							"FROM Ocupacion o " +
							"WHERE o.espacioFisico = :espacio";
		
		Query query = em.createQuery(queryString);
		query.setParameter("espacio", espacioFisico);
		
		return query.getResultList();
	}
	
	@Override
	public List<Ocupacion> getOcupacionesActivasDeEspacioFisico(String espacio) throws RepositorioException {
		
		List<Ocupacion> ocupaciones = this.getOcupacionesDeEspacioFisico(espacio);
		return ocupaciones.stream().filter(o -> o.activa()).collect(Collectors.toList());
	 
	}
	

	@Override
	public List<EspacioFisico> getEspaciosFisicosLibres (LocalDateTime fecha_inicio, LocalDateTime fecha_fin, int capacidad_min)
			throws RepositorioException {
		
		List<EspacioFisico> espaciosConCapacidad = getAll().stream().filter(e -> e.getCapacidad() >= capacidad_min).collect(Collectors.toList());
		List<EspacioFisico> espaciosDisponibles = new ArrayList<>();
		
		espaciosConCapacidad.forEach(espacio -> {
			
			boolean disponible = true;
			
			String id = espacio.getId();
			List<Ocupacion> ocupacionesActuales;
			
			try {
				
				if (espacio.getEstado() == EstadoEspacio.CERRADO_TEMPORALMENTE) {
					disponible = false;
				}
				
				ocupacionesActuales = this.getOcupacionesActivasDeEspacioFisico(id);
				
				for (int i=0; i < ocupacionesActuales.size(); i++) {
					Ocupacion ocupacion = ocupacionesActuales.get(i);
					if (fecha_fin.isAfter(ocupacion.getFechaInicio()) && fecha_inicio.isBefore(ocupacion.getFechaFin())) {
						disponible = false;
					}
				}

				if (disponible) {
					espaciosDisponibles.add(espacio);
				}	
				
			} catch (RepositorioException e1) {
				e1.printStackTrace();
			}
			
		});
		
		return espaciosDisponibles;
		
	}

	@Override
	public List<EspacioFisico> getEspaciosByPropietario(String propietario) throws RepositorioException {
		
		EntityManager em = EntityManagerHelper.getEntityManager();
	    
		try {
			
	        final String queryString = "SELECT e " + 
	        						   "FROM EspacioFisico e " +
	        						   "WHERE e.propietario = :propietario";

			Query query = em.createQuery(queryString);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			query.setParameter("propietario", propietario);
			
			
			List<EspacioFisico> espacios = query.getResultList();
  
			return espacios;
				
		} catch (Exception e) {
	        throw new RepositorioException("Error al buscar las entidades del propietario " + propietario);
		}
		
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
}
