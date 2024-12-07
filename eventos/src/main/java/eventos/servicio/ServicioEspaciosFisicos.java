package eventos.servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import eventos.dto.EspacioFisicoDTO;
import eventos.modelo.EspacioFisico;
import eventos.modelo.EstadoEspacio;
import eventos.modelo.Evento;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoDeInteres;
import eventos.repositorio.RepositorioEspaciosFisicosAdHoc;
import eventos.repositorio.RepositorioEspaciosFisicosAdHocJPA;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;

public class ServicioEspaciosFisicos implements IServicioEspaciosFisicos {

	private  RepositorioEspaciosFisicosAdHocJPA repositorioEspacios = FactoriaRepositorios.getRepositorio(RepositorioEspaciosFisicosAdHoc.class);

	public String alta(String nombre, String propietario, int capacidad, String direccion, double latitud, 
			double longitud, String descripcion) throws RepositorioException {

		
		// Control de integridad de los datos

		if (nombre == null || nombre.isEmpty()) 
			throw new IllegalArgumentException("Nombre: No debe ser nulo ni vacio.");
		
		if (descripcion == null || descripcion.isEmpty()) 
			throw new IllegalArgumentException("Descripcion: No debe ser nulo ni vacio.");
		
		if (propietario == null || propietario.isEmpty()) 
			throw new IllegalArgumentException("Propietario: No debe ser nulo ni vacio.");
		
		if (capacidad == 0) 
			throw new IllegalArgumentException("Capacidad: No debe ser igual a 0.");
		
		if (direccion == null || direccion.isEmpty())
			throw new IllegalArgumentException("Direccion: No debe ser nulo ni vacio.");

		if (latitud < -90.0 || latitud > 90.0) 
			throw new IllegalArgumentException("Latitud: No debe ser menor a -90º ni mayor a 90º.");

		if (longitud < -180.0 || longitud > 180.0) 
			throw new IllegalArgumentException("Longitud: No debe ser menor a -180º ni mayor a 180º.");

		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccion, latitud, longitud, descripcion);
		
		String id = repositorioEspacios.add(espacio);
		
		return id;
	}

	public void asignarPuntosDeInteres(String id, List<PuntoDeInteres> puntosDeInteres)
			throws RepositorioException, EntidadNoEncontrada {
		
		EspacioFisico espacio = repositorioEspacios.getById(id);
		
		espacio.setPuntosDeInteres(puntosDeInteres);

		repositorioEspacios.update(espacio);
	}

	public void modificar(String id, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		EspacioFisico espacio = repositorioEspacios.getById(id);
		
		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		
		if (capacidad == 0)
			throw new IllegalArgumentException("capacidad: no debe ser igual a 0");
		
		if (descripcion == null || descripcion.isEmpty()) 
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		
		espacio.setNombre(nombre);
		espacio.setCapacidad(capacidad);
		espacio.setDescripcion(descripcion);
		
		repositorioEspacios.update(espacio);

	}

	public void baja(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
			
		EspacioFisico espacio = repositorioEspacios.getById(id);
		
		List<Ocupacion> ocupaciones = repositorioEspacios.getOcupacionesActivasDeEspacioFisico(id);

		if (ocupaciones.isEmpty()) {
			espacio.setEstado(EstadoEspacio.CERRADO_TEMPORALMENTE);
		}
		
		repositorioEspacios.update(espacio);
	}

	public void activar(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
			
		EspacioFisico espacio = repositorioEspacios.getById(id);
		
		espacio.setEstado(EstadoEspacio.ACTIVO);
		
		repositorioEspacios.update(espacio);
		
	}

	public List<EspacioFisico> buscarEspaciosFisicosByFecha (LocalDateTime fecha_inicio, LocalDateTime fecha_fin,
			int capacidad_min) throws RepositorioException, EntidadNoEncontrada {
		
		if (fecha_inicio == null || fecha_fin== null || fecha_inicio.isAfter(fecha_fin)) 
			throw new IllegalArgumentException("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin");
		
		if (capacidad_min == 0) 
			throw new IllegalArgumentException("capacidad: no debe ser igual a 0");
		
		return repositorioEspacios.getEspaciosFisicosLibres(fecha_inicio, fecha_fin, capacidad_min);
		
	}

	@Override
	public EspacioFisicoDTO getEspacioFisico(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		EspacioFisico espacioFisico = repositorioEspacios.getById(id);
		return transformToDTO(espacioFisico);
	}

	private EspacioFisicoDTO transformToDTO(EspacioFisico espacio) {        
        return new EspacioFisicoDTO(espacio.getId(), espacio.getNombre(), espacio.getPropietario(), espacio.getCapacidad(), espacio.getDireccion(),
        		espacio.getLatitud(), espacio.getLongitud(), espacio.getPuntosDeInteres(), espacio.getDescripcion(), espacio.getEstado());
    }

	@Override
	public List<EspacioFisico> getAll() throws RepositorioException {
		return repositorioEspacios.getAll();
	}
	
}
