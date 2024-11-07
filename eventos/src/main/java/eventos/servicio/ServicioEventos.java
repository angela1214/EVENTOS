package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import eventos.modelo.Categoria;
import eventos.modelo.Evento;
import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEventos implements IServicioEventos {
	
	private Repositorio<Evento, String> repositorio = FactoriaRepositorios.getRepositorio(Evento.class);
	private Repositorio<EspacioFisico, String> repositorioEspaciosFisicos = FactoriaRepositorios.getRepositorio(EspacioFisico.class);

	public String alta(String nombre, String descripcion, String organizador, int plazas, Categoria categoria, 
			LocalDateTime fecha_inicio, LocalDateTime fecha_fin, String id_espacio_fisico)
			throws RepositorioException, EntidadNoEncontrada {
		
		EspacioFisico espacio_fisico = repositorioEspaciosFisicos.getById(id_espacio_fisico);
		
		// Control de integridad de los datos
		
		if (nombre == null || nombre.isEmpty()) 
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");

		if (descripcion == null || descripcion.isEmpty()) 
			throw new IllegalArgumentException("organizador: no debe ser nulo ni vacio");
		
		if (organizador == null || organizador.isEmpty()) 
			throw new IllegalArgumentException("organizador: no debe ser nulo ni vacio");
		
		if (plazas == 0 || plazas > espacio_fisico.getCapacidad()) 
			throw new IllegalArgumentException("plazas: no debe ser igual a 0 ni el número de plazas puede ser mayor a la capacidad del espacio físico");
		
		if (categoria == null) 
			throw new IllegalArgumentException("categoria: no debe ser nulo");
		
		if (fecha_inicio == null || fecha_fin== null || fecha_inicio.isAfter(fecha_fin)) 
			throw new IllegalArgumentException("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin");
		
		Ocupacion ocupacion = new Ocupacion(fecha_inicio, fecha_fin, espacio_fisico);
		Evento evento = new Evento(nombre, descripcion, organizador, plazas, categoria, ocupacion);
		
		String id = repositorio.add(evento);
		
		return id;
	}

	@Override
	public void modificar(String id, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_fin,
			int plazas, String id_espacio_fisico) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelar(String id) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<EventoResumen> getListadoResumen(int mes, int ano)
			throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		return null;
	}
	
}
