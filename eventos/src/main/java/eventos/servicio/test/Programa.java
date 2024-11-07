package eventos.servicio.test;

import java.time.LocalDateTime;

import eventos.modelo.Categoria;
import eventos.servicio.IServicioEspaciosFisicos;
import eventos.servicio.IServicioEventos;
import servicio.FactoriaServicios;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		IServicioEspaciosFisicos servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
		IServicioEventos servicioEventos = FactoriaServicios.getServicio(IServicioEventos.class);
		
		// Configuración del espacio fisico 1
		String nombre_ef1 = "100 montaditos";
		String propietario_ef1 = "Jose María Capitán";
		int capacidad_ef1 = 150;
		String direccion_ef1 = "Zig Zag, Avenida Juan Carlos I";
		double latitud_ef1 = 37.99869;
		double longitud_ef1 = -1.13784;
		String descripcion_ef1 = "Cadena de restaurantes decorada como una taberna que sirve cerveza y vino, con muchas variedades de montaditos españoles.";
		
		// Alta del espacio físico 1
		String id_ef1 = servicioEspacios.alta(nombre_ef1, propietario_ef1, capacidad_ef1, direccion_ef1, latitud_ef1, longitud_ef1, descripcion_ef1);
		System.out.println("ID del espacio fisico creado: " + id_ef1);		
		
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
		
		System.out.println("ID del evento: " + id_e1);		
		
	}

}
