package eventos.servicio;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import eventos.modelo.Categoria;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.LinkedList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServicioEventosTest {
	
	private IServicioEspaciosFisicos servicioEspacios;
	private String id_espacio_fisico;
	
	private IServicioEventos servicioEventos;
	
	private String nombre;
	private String descripcion;
	private String organizador;
	private int plazas;
	private Categoria categoria;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	
	private String nombre1;
	private String descripcion1;
	private String organizador1;
	private int plazas1;
	private Categoria categoria1;
	private LocalDateTime fechaInicio1;
	private LocalDateTime fechaFin1;
	private String id_evento1;

	@BeforeAll
    void setupBeforeAll() throws Exception {
		
		// Alta de un espacio físico necesario para dar de alta un evento
		
		this.servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
		
		String nombre_ef1 = "100 montaditos";
		String propietario_ef1 = "Jose María Capitán";
		int capacidad_ef1 = 100;
		String direccion_ef1 = "Zig Zag, Avenida Juan Carlos I";
		double latitud_ef1 = 37.99869;
		double longitud_ef1 = -1.13784;	
		String descripcion_ef1 = "Cadena de restaurantes.";
		this.id_espacio_fisico = servicioEspacios.alta(nombre_ef1, propietario_ef1, capacidad_ef1, direccion_ef1, latitud_ef1, longitud_ef1, descripcion_ef1);
		
		this.servicioEventos = FactoriaServicios.getServicio(IServicioEventos.class);
		
		// Configuración base de un evento (se utilizará para el alta de un evento)
		
		this.nombre = "Encuentro Universitario Matemático";
		this.descripcion = "Evento social organizado con el objetivo de que la comunidad universitaria matemática de la Universidad"
				+ "de Murcia disfrute de una cena en la que se pueda charlar con amigos y conocer a gente.";
		this.organizador = "Comisión de fiestas";
		this.plazas = 80;
		this.categoria = Categoria.ENTRETENIMIENTO;
		this.fechaInicio = LocalDateTime.of(2024,11, 16, 18, 00);
		this.fechaFin = LocalDateTime.of(2024,11, 17, 00, 00);
		
		// Configuración y creación de un evento para la modificación de eventos
		
		this.nombre1 = "Segundo evento";
		this.descripcion1 = "Evento en la ciudad de Murcia para la familia.";
		this.organizador1 = "Ayuntamiento de Murcia";
		this.plazas1 = 50;
		this.categoria1 = Categoria.OTRO;
		this.fechaInicio1 = LocalDateTime.of(2024,11, 20, 9, 00);
		this.fechaFin1 = LocalDateTime.of(2024,11, 22, 00, 00);
		
		this.id_evento1 = this.servicioEventos.alta(this.nombre1, this.descripcion1, this.organizador1, this.plazas1, 
				this.categoria1, this.fechaInicio1, this.fechaFin1, id_espacio_fisico);
	}
	
	// --------------------------------- Test servicio eventos ---------------------------------
	
	// ----------------------------------- Alta de un evento -----------------------------------
	
	@Test
	public void test_altaEvento_nombreNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por el nombre

    		String id = this.servicioEventos.alta(null, this.descripcion, this.organizador, this.plazas, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("nombre: no debe ser nulo ni vacio", exception.getMessage());
		
	}

	@Test
	public void test_altaEvento_nombreVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por el nombre

    		String id = this.servicioEventos.alta("", this.descripcion, this.organizador, this.plazas, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("nombre: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_descripcionNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la descripcion

    		String id = this.servicioEventos.alta(this.nombre, null, this.organizador, this.plazas, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}

	@Test
	public void test_altaEvento_descripcionVacia() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la descripcion

    		String id = this.servicioEventos.alta(this.nombre, "", this.organizador, this.plazas, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_organizadorNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por el organizador

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, null, this.plazas, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("organizador: no debe ser nulo ni vacio", exception.getMessage());
		
	}

	@Test
	public void test_altaEvento_organizadorVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por el organizador

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, "", this.plazas, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("organizador: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_ceroPlazas() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por las plazas

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, this.organizador, 0, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("plazas: no debe ser igual a 0 ni el número de plazas puede ser mayor a la capacidad del espacio físico", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_masPlazasQueCapacidad() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por las plazas

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, this.organizador, 120, 
    					this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("plazas: no debe ser igual a 0 ni el número de plazas puede ser mayor a la capacidad del espacio físico", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_categoriaNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la categoria

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, this.organizador, this.plazas, 
    					null, this.fechaInicio, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("categoria: no debe ser nulo", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_fechaInicioNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la fecha de inicio

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, this.organizador, this.plazas, 
    					this.categoria, null, this.fechaFin, id_espacio_fisico);
        });
        
        assertEquals("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_fechaFinNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la fecha de fin

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, this.organizador, this.plazas, 
    					this.categoria, this.fechaInicio, null, id_espacio_fisico);
        });
        
        assertEquals("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento_fechaInicioPosteriorFin() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por las fechas

    		String id = this.servicioEventos.alta(this.nombre, this.descripcion, this.organizador, this.plazas, 
    					this.categoria, this.fechaFin, this.fechaInicio, id_espacio_fisico);
        });
        
        assertEquals("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEvento() {
		
    	String id = null;
		try {
			id = this.servicioEventos.alta(this.nombre, this.descripcion, this.organizador, this.plazas, 
						this.categoria, this.fechaInicio, this.fechaFin, id_espacio_fisico);
		} catch (RepositorioException e) {
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}

        assertNotNull(id);
		
	}
	
	// ----------------------------------- Modificar un evento -----------------------------------

	@Test
	public void test_modificarEvento_descripcionNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Modificación de un evento donde se produce una excepcion por la descripcion
    		this.servicioEventos.modificar(this.id_evento1, null, this.fechaInicio1, this.fechaFin1,
    				this.plazas1, this.id_espacio_fisico);
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEvento_descripcionVacia() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificación de un evento donde se produce una excepcion por la descripcion
    		this.servicioEventos.modificar(this.id_evento1, "", this.fechaInicio1, this.fechaFin1,
    				this.plazas1, this.id_espacio_fisico);
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	

	@Test
	public void test_modificarEvento_ceroPlazas() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificación de un evento donde se produce una excepcion por las plazas
    		this.servicioEventos.modificar(this.id_evento1, this.descripcion1, this.fechaInicio1, this.fechaFin1,
    				0, this.id_espacio_fisico);
        });
        
        assertEquals("plazas: no debe ser igual a 0 ni el número de plazas puede ser mayor a la capacidad del espacio físico", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEvento_masPlazasQueCapacidad() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificación de un evento donde se produce una excepcion por las plazas
    		this.servicioEventos.modificar(this.id_evento1, this.descripcion1, this.fechaInicio1, this.fechaFin1,
    				120, this.id_espacio_fisico);
        });
        
        assertEquals("plazas: no debe ser igual a 0 ni el número de plazas puede ser mayor a la capacidad del espacio físico", exception.getMessage());
		
	}
	

	@Test
	public void test_modificarEvento_fechaInicioNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificación de un evento donde se produce una excepcion por la fecha de inicio
    		this.servicioEventos.modificar(this.id_evento1, this.descripcion1, null, this.fechaFin1,
    				this.plazas1, this.id_espacio_fisico);
        	
        });
        
        assertEquals("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEvento_fechaFinNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificación de un evento donde se produce una excepcion por la fecha fin
    		this.servicioEventos.modificar(this.id_evento1, this.descripcion1, this.fechaInicio1, null,
    				this.plazas1, this.id_espacio_fisico);
        });
        
        assertEquals("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEvento_fechaInicioPosteriorFin() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        	
        	// Modificación de un evento donde se produce una excepcion por las fechas
    		this.servicioEventos.modificar(this.id_evento1, this.descripcion1, this.fechaFin1, this.fechaInicio1,
    				this.plazas1, this.id_espacio_fisico);
        });
        
        assertEquals("fecha inicio y fecha fin: no deben ser nulas ni la fecha inicio posterior a la fecha fin", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEvento() {
		
		try {
			this.servicioEventos.modificar(this.id_evento1, "Descripcion del segundo evento", 
					this.fechaInicio1, this.fechaFin1, this.plazas1, id_espacio_fisico);
		} catch (RepositorioException e) {
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
	}

	// ----------------------------------- Resumen de eventos -----------------------------------
	
	@Test
	public void test_listarEventos() {
	
		LinkedList<EventoResumen> resumen = null;
		
		try {
			System.out.println("TEST LISTAR EVENTOS");
			System.out.println("Resumen de eventos en el mes " + 11 + " de " + 2024);
			resumen = this.servicioEventos.getListadoResumen(11, 2024);
			System.out.println(resumen.toString());
		} catch (RepositorioException e) {
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
		assertNotNull(resumen);
		assertEquals(2, resumen.size());
		
	}
		
	// ----------------------------------- Cancelar un evento -----------------------------------
	
	@Test
	public void test_cancelarEvento() {

		LinkedList<EventoResumen> resumen = null;
		
		try {
			System.out.println("TEST CANCELAR EVENTO");
			System.out.println("Resumen de eventos en el mes " + 11 + " de " + 2024);
			this.servicioEventos.cancelar(this.id_evento1);
			resumen = this.servicioEventos.getListadoResumen(11, 2024);
			System.out.println(resumen);
		} catch (RepositorioException e) {
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
		assertNotNull(resumen);
		assertEquals(1, resumen.size());
		
	}
	
	
	
}