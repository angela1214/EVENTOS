package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoDeInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEspaciosFisicos implements IServicioEspaciosFisicos {
	
	private Repositorio<EspacioFisico, String> repositorio = FactoriaRepositorios.getRepositorio(EspacioFisico.class);

	public String alta(String nombre, String propietario, int capacidad, String direccion, double latitud, 
			double longitud, String descripcion) throws RepositorioException {

		// Control de integridad de los datos
		
		if (nombre == null || nombre.isEmpty()) 
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		
		if (propietario == null || propietario.isEmpty()) 
			throw new IllegalArgumentException("propietario: no debe ser nulo ni vacio");
		
		if (capacidad == 0) 
			throw new IllegalArgumentException("capacidad: no debe ser igual a 0");
		
		if (direccion == null || direccion.isEmpty())
			throw new IllegalArgumentException("direccion: no debe ser nulo ni vacio");

		if (latitud < -90.0 || latitud > 90.0) 
			throw new IllegalArgumentException("latitud: no debe ser menor a -90º ni mayor a 90º");

		if (longitud < -180.0 || longitud > 180.0) 
			throw new IllegalArgumentException("longitud: no debe ser menor a -180º ni mayor a 180º");
		
		if (descripcion == null || descripcion.isEmpty()) 
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		
		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccion, latitud, longitud, descripcion);
		
		String id = repositorio.add(espacio);
		
		return id;
	}

	public void asignarPuntosDeInteres(String id, LinkedList<PuntoDeInteres> puntosDeInteres)
			throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	public void modificar(String id, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	public void baja(String id) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	public void activar(String id) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	public LinkedList<EspacioFisico> buscarEspaciosFisicosByFecha(LocalDateTime fecha_inicio, LocalDateTime fecha_fin,
			int capacidad_min) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		return null;
	}

	
}
