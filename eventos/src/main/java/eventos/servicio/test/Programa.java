package eventos.servicio.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import eventos.dto.EventoDTO;
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
		
		IServicioEspaciosFisicos servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
		IServicioEventos servicioEventos = FactoriaServicios.getServicio(IServicioEventos.class);
		IPuntosDeInteres servicioPuntosDeInteres = FactoriaServicios.getServicio(IPuntosDeInteres.class);
		
		// Configuración del espacio físico 1
		String nombre_ef1 = "100 montad";
		String propietario_ef1 = "Jose María Capitán";
		int capacidad_ef1 = 100;
		String direccion_ef1 = "Zig Zag, Avenida Juan Carlos I";
		double latitud_ef1 = 37.99869;
		double longitud_ef1 = -1.13784;	
		String descripcion_ef1 = "Cadena de restaurantes.";
		
		// Configuración del espacio físico 2
		String nombre_ef2 = "Auditorio Víctor Villegas";
		String propietario_ef2 = "Comunidad Autónoma de la Región de Murcia";
		int capacidad_ef2 = 1000;
		String direccion_ef2 = "Av. Primero de Mayo, s/n, 30006, Murcia";
		double latitud_ef2 = 37.98350;
		double longitud_ef2 = -1.11407;	
		String descripcion_ef2 = "El Auditorio y Centro de Congresos “Víctor Villegas” está formado por dos edificios; el edificio principal contiene el auditorio y un segundo edificio concebido como anexo para congresos.";
		
		// Configuración del espacio físico 3
		String nombre_ef3 = "Teatro Romea";
		String propietario_ef3 = "Ayuntamiento de Murcia";
		int capacidad_ef3 = 700;
		String direccion_ef3 = "Calle de la Gloria, 3, 30004, Murcia";
		double latitud_ef3 = 37.98656;
		double longitud_ef3 = -1.13123;	
		String descripcion_ef3 = "El Teatro Romea es uno de los principales teatros de Murcia, conocido por su arquitectura clásica y su variada programación cultural, que incluye teatro, danza y conciertos.";

		// Alta del espacio físico 1
		String id_ef1 = servicioEspacios.alta(nombre_ef1, propietario_ef1, capacidad_ef1, direccion_ef1, latitud_ef1, longitud_ef1, descripcion_ef1);
		System.out.println('\n' + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] ALTA DE ESPACIO FISICO");
		System.out.println("Espacio fisico creado: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		// Alta del espacio físico 2
		String id_ef2 = servicioEspacios.alta(nombre_ef2, propietario_ef2, capacidad_ef2, direccion_ef2, latitud_ef2, longitud_ef2, descripcion_ef2);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] ALTA DE ESPACIO FISICO");
		System.out.println("Espacio fisico creado: " + id_ef2);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef2).toString());
		
		// Alta del espacio físico 3
		String id_ef3 = servicioEspacios.alta(nombre_ef3, propietario_ef3, capacidad_ef3, direccion_ef3, latitud_ef3, longitud_ef3, descripcion_ef3);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] ALTA DE ESPACIO FISICO");
		System.out.println("Espacio fisico creado: " + id_ef3);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef3).toString());

		// Modificación del espacio físico 1
		String nombre_ef1_modif = "100 montaditos";
		int capacidad_ef1_modif = 150;
		String descripcion_ef1_modif = "Cadena de restaurantes decorada como una taberna que sirve cerveza y vino, con muchas variedades de montaditos españoles.";
		servicioEspacios.modificar(id_ef1, nombre_ef1_modif, capacidad_ef1_modif, descripcion_ef1_modif);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] MODIFICACIÓN DE ESPACIO FISICO");
		System.out.println("Espacio fisico modificado: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());

		// Obtención de los puntos de interés
		List<PuntoDeInteres> puntosDeInteres = servicioPuntosDeInteres.obtenerPuntosDeInteres(37.984123153048316, -1.1291804565199284);
		List<PuntoDeInteres> puntos = new ArrayList<>();
		
		for(PuntoDeInteres punto : puntosDeInteres) {
			if (punto.getDistancia() <= 0.245) {
				puntos.add(punto);
			}
		}
		
		servicioEspacios.asignarPuntosDeInteres(id_ef1, puntos);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] ASIGNACIÓN DE PUNTOS DE INTERÉS A UN ESPACIO FISICO");
		System.out.println("Puntos asignados al espacio fisico: " + id_ef1);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		// Configuración del evento 1
		String nombre_e1 = "Encuentro Universitario Matemático";
		String descripcion_e1 = "Evento social organizado con el objetivo de que la comunidad universitaria matemática de la Universidad"
				+ "de Murcia disfrute de una cena en la que se pueda charlar con amigos y conocer a gente.";
		String organizador_e1 = "Comisión de fiestas";
		int plazas_e1 = 80;
		Categoria categoria_e1 = Categoria.ENTRETENIMIENTO;
		LocalDateTime fechaInicio_e1 = LocalDateTime.of(2025, 01, 16, 18, 00);
		LocalDateTime fechaFin_e1 = LocalDateTime.of(2025, 01, 17, 00, 00);
		
		// Configuración del evento 1
		String nombre_e2 = "Concierto de cuerda y percusión";
		String descripcion_e2 = "Concierto de violines, violas y violonchelos, acompañados por percusionistas de 4º de profesional.";
		String organizador_e2 = "Conservatorio de Música de Murcia";
		int plazas_e2 = 100;
		Categoria categoria_e2 = Categoria.CULTURAL;
		LocalDateTime fechaInicio_e2 = LocalDateTime.of(2024, 11, 16, 18, 00);
		LocalDateTime fechaFin_e2 = LocalDateTime.of(2024, 11, 17, 00, 00);

		// Alta del evento 1
		String id_e1 = servicioEventos.alta(nombre_e1, descripcion_e1, organizador_e1, plazas_e1, categoria_e1, fechaInicio_e1, fechaFin_e1, id_ef1);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEventos] ALTA DE UN EVENTO");
		System.out.println("Evento creado: " + id_e1);	
		System.out.println(servicioEventos.getEvento(id_e1).toString());
		
		// Alta del evento 2
		String id_e2 = servicioEventos.alta(nombre_e2, descripcion_e2, organizador_e2, plazas_e2, categoria_e2, fechaInicio_e2, fechaFin_e2, id_ef2);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEventos] ALTA DE UN EVENTO");
		System.out.println("Evento creado: " + id_e2);	
		System.out.println(servicioEventos.getEvento(id_e2).toString());
		
		// Modificacion del evento 1
		servicioEventos.modificar(id_e1, "Descripcion del primer evento modificada", fechaInicio_e1, fechaFin_e1, 20, id_ef1);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEventos] MODIFICACIÓN DE UN EVENTO");
		System.out.println("Evento modificado: " + id_e1);
		System.out.println(servicioEventos.getEvento(id_e1).toString());
		
		
		// Dar de baja espacio físico con ocupaciones ACTIVAS
		servicioEspacios.baja(id_ef1);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] BAJA DE UN ESPACIO FÍSICO CON OCUPACIONES ACTIVAS");
		System.out.println("Espacio fisico dado de baja: " + id_ef1);	
		System.out.println("El espacio fisico está ACTIVO puesto que tiene ocupaciones activas");
		System.out.println(servicioEspacios.getEspacioFisico(id_ef1).toString());
		
		// Dar de baja espacio físico con ocupaciones NO ACTIVAS
		servicioEspacios.baja(id_ef2);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] BAJA DE UN ESPACIO FÍSICO CON OCUPACIONES NO ACTIVAS");
		System.out.println("Espacio fisico dado de baja: " + id_ef2);	
		System.out.println("El espacio fisico está CERRADO_TEMPORALMENTE puesto que tiene ocupaciones no activas");
		System.out.println(servicioEspacios.getEspacioFisico(id_ef2).toString());
		
		// Dar de baja espacio fisico sin ocupaciones		
		servicioEspacios.baja(id_ef3);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] BAJA DE UN ESPACIO FÍSICO SIN OCUPACIONES");
		System.out.println("Espacio fisico dado de baja: " + id_ef3);	
		System.out.println("El espacio fisico está CERRADO_TEMPORALMENTE ya que no tiene ocupaciones activas");
		System.out.println(servicioEspacios.getEspacioFisico(id_ef3).toString());

		// Activar espacio físico 2
		servicioEspacios.activar(id_ef2);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] ACTIVACIÓN DE UN ESPACIO FÍSICO");
		System.out.println("Espacio fisico activado: " + id_ef2);	
		System.out.println(servicioEspacios.getEspacioFisico(id_ef2).toString());
		
		
		// Buscar espacios fisicos por fechas
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEspaciosFisicos] BÚSQUEDA DE ESPACIOS FÍSICOS POR FECHA");
		System.out.println("Búsqueda de espacios físicos entre hoy y mañana con capacidad para 400 personas");
		LocalDateTime hoy = LocalDateTime.now();
		LocalDateTime manana = hoy.plusDays(1);
		
		List<EspacioFisico> espacios = servicioEspacios.buscarEspaciosFisicosByFecha(hoy, manana, 400);

		for (EspacioFisico espacio : espacios) {
			System.out.println(espacio.toString());
		}

		// Listar eventos del mes
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEventos] LISTAR EVENTOS DEL MES");
		System.out.println("Listado de eventos del mes 11/2024");
		LinkedList<EventoResumen> resumen = servicioEventos.getListadoResumen(11, 2024);
		System.out.println(resumen.toString());
		
		// Cancelar evento del mes
		/*
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ServicioEventos] CANCELAR EVENTO");
		servicioEventos.cancelar(id_e1);
		System.out.println("Cancelar el evento con id: "+ id_e1);
		EventosDTO ev = servicioEventos.getEvento(id_e1);
		System.out.println("¿Está cancelado el evento? " +  ev.isCancelado());
		*/
		
	}

}
