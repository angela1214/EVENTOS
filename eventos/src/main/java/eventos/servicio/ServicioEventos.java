package eventos.servicio;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import eventos.dto.EventosDTO;
import eventos.modelo.Categoria;
import eventos.modelo.Evento;
import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoDeInteres;
import eventos.repositorio.RepositorioEspaciosFisicosAdHoc;
import eventos.repositorio.RepositorioEspaciosFisicosAdHocJPA;
import eventos.repositorio.RepositorioEventosAdHoc;
import eventos.repositorio.RepositorioEventosAdHocJPA;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;

public class ServicioEventos implements IServicioEventos {
	
	private  RepositorioEventosAdHocJPA repositorioEventos = FactoriaRepositorios.getRepositorio(RepositorioEventosAdHoc.class);
	private  RepositorioEspaciosFisicosAdHocJPA repositorioEspaciosFisicos = FactoriaRepositorios.getRepositorio(RepositorioEspaciosFisicosAdHoc.class);

	public String alta(String nombre, String descripcion, String organizador, int plazas, Categoria categoria, 
			LocalDateTime fecha_inicio, LocalDateTime fecha_fin, String id_espacio_fisico)
			throws RepositorioException, EntidadNoEncontrada {
		
		EspacioFisico espacio_fisico = repositorioEspaciosFisicos.getById(id_espacio_fisico);
		
		// Control de integridad de los datos
		
		if (nombre == null || nombre.isEmpty()) 
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");

		if (descripcion == null || descripcion.isEmpty()) 
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		
		if (organizador == null || organizador.isEmpty()) 
			throw new IllegalArgumentException("organizador: no debe ser nulo ni vacio");
		
		if (plazas == 0 || plazas > espacio_fisico.getCapacidad()) 
			throw new IllegalArgumentException("plazas: no debe ser igual a 0 ni el número de plazas puede ser mayor a la capacidad del espacio físico");
		
		if (categoria == null) 
			throw new IllegalArgumentException("categoria: no debe ser nulo");
		
		if (fecha_inicio == null || fecha_fin== null || fecha_inicio.isAfter(fecha_fin)) 
			throw new IllegalArgumentException("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin");
		
		Evento evento = new Evento(nombre, descripcion, organizador, plazas, categoria, fecha_inicio, fecha_fin, espacio_fisico);
		
		String id = repositorioEventos.add(evento);
		
		return id;
	}

	@Override
	public void modificar(String id, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_fin,
			int plazas, String id_espacio_fisico) throws RepositorioException, EntidadNoEncontrada {
		
		Evento evento = repositorioEventos.getById(id);
		EspacioFisico espacio_fisico = repositorioEspaciosFisicos.getById(id_espacio_fisico);

		if (descripcion == null || descripcion.isEmpty()) 
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		
		if (plazas == 0 || plazas > espacio_fisico.getCapacidad()) 
			throw new IllegalArgumentException("plazas: no debe ser igual a 0 ni el número de plazas puede ser mayor a la capacidad del espacio físico");
		
		if (fecha_inicio == null || fecha_fin== null || fecha_inicio.isAfter(fecha_fin)) 
			throw new IllegalArgumentException("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin");
		
		evento.setDescripcion(descripcion);
		evento.setPlazas(plazas);
		
		Ocupacion ocupacion = evento.getOcupacion();
		ocupacion.setFechaInicio(fecha_inicio);
		ocupacion.setFechaFin(fecha_fin);
		ocupacion.setEspacioFisico(espacio_fisico);
		evento.setOcupacion(ocupacion);
		
		repositorioEventos.update(evento);
	}

	@Override
	public void cancelar(String id) throws RepositorioException, EntidadNoEncontrada {

		Evento evento = repositorioEventos.getById(id);

		evento.setCancelado(true);
		evento.setOcupacion(null);
		
		repositorioEventos.update(evento);		

	}

	@Override
	public LinkedList<EventoResumen> getListadoResumen(int mes, int ano)
			throws RepositorioException, EntidadNoEncontrada {

		LinkedList<EventoResumen> resultado = new LinkedList<>();

		for (Evento evento : repositorioEventos.getEventosMes(mes, ano)) {

			EventoResumen resumen = new EventoResumen();
			
			resumen.setId(evento.getId());
			resumen.setNombre(evento.getNombre());
			resumen.setDescripcion(evento.getDescripcion());
			
			Ocupacion oc = evento.getOcupacion();
			resumen.setFechaIncio(oc.getFechaInicio());

			resumen.setCategoria(evento.getCategoria());
			resumen.setNombre_espacioFisico(oc.getEspacioFisico().getNombre());
			resumen.setDireccion_espacioFisico(oc.getEspacioFisico().getDireccion());
			
			List<PuntoDeInteres> puntos = oc.getEspacioFisico().getPuntosDeInteres();
			puntos.sort(Comparator.comparingDouble(p -> p.getDistancia()));
			
			List<String> puntosNombres = new LinkedList<>();
			
			for(PuntoDeInteres punto : puntos) {
				puntosNombres.add(punto.getNombre());
			}
			resumen.setNombre_puntosDeInteres_espacioFisico(puntosNombres);
			
			resultado.add(resumen);
			
		}
		
		return resultado;
	}

	@Override
	public EventosDTO getEvento(String id) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		Evento evento = repositorioEventos.getById(id);
		return transformToDTO(evento);
	}
	
	private EventosDTO transformToDTO(Evento evento) {        
        return new EventosDTO(evento.getId(), evento.getNombre(), evento.getDescripcion(), evento.getOrganizador(),
        			evento.getPlazas(), evento.isCancelado(), evento.getCategoria(), evento.getOcupacion());
    }
	
}
