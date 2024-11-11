package eventos.servicio.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import eventos.modelo.Categoria;
import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoDeInteres;
import eventos.servicio.EventoResumen;
import eventos.servicio.IPuntosDeInteres;
import eventos.servicio.IServicioEspaciosFisicos;
import eventos.servicio.IServicioEventos;
import servicio.FactoriaServicios;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		/*
		 * Para validar las clases servicioEventos y servicioEspaciosFisicos se han creado dos clases en
		 * el paquete eventos.servicios ubicado en la carpeta src/test/java. En estas clases se comprueban
		 * todas las excepciones que se producen por argumentos no válidos y los métodos implementados. 
		 * 
		 * En la clase servicioEventos, los dos últimos test no se pasan debido a que al hacer las consultas 
		 * a la base de datos no se devuelve lo esperado. En la clase servicioEspaciosFisciosTest fallan algunos test.
		 * 
		 * Además, en este programa se prueba la funcionalidad ofrecida por puntosDeInteres, servicioEventos
		 * y servicioEspacios.
		 * 
		 */
		IServicioEspaciosFisicos servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
		IServicioEventos servicioEventos = FactoriaServicios.getServicio(IServicioEventos.class);
		IPuntosDeInteres servicioPuntosDeInteres = FactoriaServicios.getServicio(IPuntosDeInteres.class);
		
		// Configuración del espacio fisico 1
		String nombre_ef1 = "100 montad";
		String propietario_ef1 = "Jose María Capitán";
		int capacidad_ef1 = 100;
		String direccion_ef1 = "Zig Zag, Avenida Juan Carlos I";
		double latitud_ef1 = 37.99869;
		double longitud_ef1 = -1.13784;	
		String descripcion_ef1 = "Cadena de restaurantes.";
		
		// Alta del espacio físico 1
		String id_ef1 = servicioEspacios.alta(nombre_ef1, propietario_ef1, capacidad_ef1, direccion_ef1, latitud_ef1, longitud_ef1, descripcion_ef1);
		System.out.println('\n' +"Espacio fisico creado: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		// Modificación del espacio físico 1
		String nombre_ef1_modif = "100 montaditos";
		int capacidad_ef1_modif = 150;
		String descripcion_ef1_modif = "Cadena de restaurantes decorada como una taberna que sirve cerveza y vino, con muchas variedades de montaditos españoles.";
		servicioEspacios.modificar(id_ef1, nombre_ef1_modif, capacidad_ef1_modif, descripcion_ef1_modif);
		System.out.println('\n' +"Espacio fisico modificado: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		
		// En estos métodos tengo problemas, pero en la base de datos si que se reflejan los cambios
		
		List<PuntoDeInteres> puntosDeInteres = servicioPuntosDeInteres.obtenerPuntosDeInteres(37.984123153048316, -1.1291804565199284);
		List<PuntoDeInteres> puntos = new ArrayList<>();
		
		for(PuntoDeInteres punto : puntosDeInteres) {
			if (punto.getDistancia() <= 0.245) {
				puntos.add(punto);
			}
		}
		
		servicioEspacios.asignarPuntosDeInteres(id_ef1, puntos);
		System.out.println('\n' +"Puntos asignados al espacio fisico: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		// Dar de baja
		servicioEspacios.baja(id_ef1);
		System.out.println('\n' +"Espacio fisico dado de baja: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		// Activar 
		servicioEspacios.activar(id_ef1);
		System.out.println('\n' +"Espacio fisico activado: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		// Buscar espacio fisico por fechas
		LocalDateTime hoy = LocalDateTime.now();
		LocalDateTime manana = hoy.plusDays(1);
		
		List<EspacioFisico> espacios = servicioEspacios.buscarEspaciosFisicosByFecha(hoy, manana, 400);

		for (EspacioFisico espacio : espacios) {
			System.out.println(espacio.toString());
		}
		
		// Configuración del evento 1
		String nombre_e1 = "Encuentro Universitario Matemático";
		String descripcion_e1 = "Evento social organizado con el objetivo de que la comunidad universitaria matemática de la Universidad"
				+ "de Murcia disfrute de una cena en la que se pueda charlar con amigos y conocer a gente.";
		String organizador_e1 = "Comisión de fiestas";
		int plazas_e1 = 80;
		Categoria categoria_e1 = Categoria.ENTRETENIMIENTO;
		LocalDateTime fechaInicio_e1 = LocalDateTime.of(2024,11, 16, 18, 00);
		LocalDateTime fechaFin_e1 = LocalDateTime.of(2024,11, 17, 00, 00);

		// Alta del evento 1
		String id_e1 = servicioEventos.alta(nombre_e1, descripcion_e1, organizador_e1, plazas_e1, categoria_e1, fechaInicio_e1, fechaFin_e1, id_ef1);
		System.out.println('\n' +"Evento creado: " + id_e1);	
		System.out.println(servicioEventos.getEvento(id_e1).toString());
		
		// Modificacion del evento 1
		servicioEventos.modificar(id_e1, "Descripcion del primer evento modificada", fechaInicio_e1, fechaFin_e1, 20, id_ef1);
		System.out.println('\n' +"Evento modificado: " + id_e1);	
		System.out.println(servicioEventos.getEvento(id_e1).toString());
		
		// Listar eventos del mes
		System.out.println("Listado de eventos del mes 11/2024");
		LinkedList<EventoResumen> resumen = servicioEventos.getListadoResumen(11, 2024);
		System.out.println(resumen.toString());
		
		// Cancelar evento del mes
		servicioEventos.cancelar(id_e1);
		System.out.println("Cancelar el evento con id: "+ id_e1);
		resumen = servicioEventos.getListadoResumen(11, 2024);
		System.out.println(resumen.toString());
		
	}

}
