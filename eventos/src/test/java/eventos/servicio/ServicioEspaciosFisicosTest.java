package eventos.servicio;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import eventos.dto.EspacioFisicoDTO;
import eventos.modelo.EstadoEspacio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServicioEspaciosFisicosTest {
	
	private IServicioEspaciosFisicos servicioEspacios;
	
	private String nombre;
	private String descripcion;
	private String propietario;
	private int capacidad;
	private String direccion;
	private double latitud;
	private double longitud;
	

	@BeforeAll
    void setupBeforeAll() throws Exception {
		
		this.servicioEspacios = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
		
		this.nombre = "La Fica";
		this.descripcion = "El recinto ferial de la fica es un recinto de uso general localizado en frente del audirotio y centro de congresos Victor Vallegas";
		this.propietario = "Ayuntamiento de Murcia";
		this.capacidad = 5000;
		this.direccion = "Diseminado Primero Mayo, 19, 30003 Murcia";
		this.latitud = 37.983939685301834;
		this.longitud = -1.1123021608350483;
		
	}
	
	// -------------------------- Test servicio espacios fisicos --------------------------
	
	// ---------------------------- Alta de un espacio fisico -----------------------------
	
	@Test
	public void test_altaEspacioFisico_nombreNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un espacio físico donde se produce una excepcion por el nombre

    		String id = this.servicioEspacios.alta(null, this.propietario, this.capacidad, this.direccion, 
    					this.latitud, this.longitud, this.descripcion);
        });
        
        assertEquals("nombre: no debe ser nulo ni vacio", exception.getMessage());
		
	}

	@Test
	public void test_altaEspacioFisico_nombreVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un espacio físico donde se produce una excepcion por el nombre

    		String id = this.servicioEspacios.alta("", this.propietario, this.capacidad, this.direccion, 
    					this.latitud, this.longitud, this.descripcion);
        });
        
        assertEquals("nombre: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_descripcionNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un espacio físico donde se produce una excepcion por la descripcion

    		String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, this.direccion, 
    					this.latitud, this.longitud, null);
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}

	@Test
	public void test_altaEspacioFisico_descripcionVacia() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un espacio físico donde se produce una excepcion por la descripcion

    		String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, this.direccion, 
    					this.latitud, this.longitud, "");
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_propietarioNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por el propietario

        	String id = this.servicioEspacios.alta(this.nombre, null, this.capacidad, this.direccion, 
					this.latitud, this.longitud, this.descripcion);
        });
        
        assertEquals("propietario: no debe ser nulo ni vacio", exception.getMessage());
		
	}

	@Test
	public void test_altaEspacioFisico_propietarioVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por el propietario

        	String id = this.servicioEspacios.alta(this.nombre, "", this.capacidad, this.direccion, 
					this.latitud, this.longitud, this.descripcion);
        });
        
        assertEquals("propietario: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_capacidadCero() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la capacidad

        	String id = this.servicioEspacios.alta(this.nombre, this.propietario, 0, this.direccion, 
					this.latitud, this.longitud, this.descripcion);
        });
        
        assertEquals("capacidad: no debe ser igual a 0", exception.getMessage());
		
	}
	
	
	@Test
	public void test_altaEspacioFisico_direccionNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la direccion

        	String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, null, 
					this.latitud, this.longitud, this.descripcion);
        });
        
        assertEquals("direccion: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_direccionVacia() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la direccion

        	String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, "", 
					this.latitud, this.longitud, this.descripcion);
        });
        
        assertEquals("direccion: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_latitudMenor90() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la latitud

        	String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, this.direccion, 
					-100, this.longitud, this.descripcion);
        });
        
        assertEquals("latitud: no debe ser menor a -90º ni mayor a 90º", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_latitudMayor90() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la latitud

        	String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, this.direccion, 
					100, this.longitud, this.descripcion);
        });
        
        assertEquals("latitud: no debe ser menor a -90º ni mayor a 90º", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_longitudMenor180() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la longitud

        	String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, this.direccion, 
					this.latitud, -200, this.descripcion);
        });
        
        assertEquals("longitud: no debe ser menor a -180º ni mayor a 180º", exception.getMessage());
		
	}
	
	@Test
	public void test_altaEspacioFisico_longitudMayor180() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Creación de un evento donde se produce una excepcion por la longitud

        	String id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, this.direccion, 
					this.latitud, 200, this.descripcion);
        });
        
        assertEquals("longitud: no debe ser menor a -180º ni mayor a 180º", exception.getMessage());
		
	}

	
	@Test
	public void test_altaEspacioFisico() {
		
    	String id = null;
		try {
        	id = this.servicioEspacios.alta(this.nombre, this.propietario, this.capacidad, this.direccion, 
					this.latitud, this.longitud, this.descripcion);
		} catch (RepositorioException e) {
			e.printStackTrace();
		}

        assertNotNull(id);
		
	}
	
	// ----------------------------------- Modificar un espacio fisico -----------------------------------

	@Test
	public void test_modificarEspacioFisico_idNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Modificación de un espacio fisico donde se produce una excepcion por el id
    		this.servicioEspacios.modificar(null, this.nombre, this.capacidad, this.descripcion);
        });
        
        assertEquals("id: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEspacioFisico_idVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Modificación de un espacio fisico donde se produce una excepcion por el id
    		this.servicioEspacios.modificar("", this.nombre, this.capacidad, this.descripcion);
        });
        
        assertEquals("id: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEspacioFisico_nombreNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Modificación de un espacio fisico donde se produce una excepcion por el nombre
    		this.servicioEspacios.modificar("1", null, this.capacidad, this.descripcion);
        });
        
        assertEquals("nombre: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEspacioFisico_nombreVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificación de un espacio fisico donde se produce una excepcion por el nombre
    		this.servicioEspacios.modificar("1", "", this.capacidad, this.descripcion);
        });
        
        assertEquals("nombre: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEspacioFisico_capacidadCero() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificación de un espacio fisico donde se produce una excepcion por la capacidad
    		this.servicioEspacios.modificar("1", this.nombre, 0, this.descripcion);
        });
        
        assertEquals("capacidad: no debe ser igual a 0", exception.getMessage());
		
	}
	
	
	@Test
	public void test_modificarEspacioFisico_descripcionNula() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificacion de un espacio físico donde se produce una excepcion por la descripcion
    		this.servicioEspacios.modificar("1", this.nombre, this.capacidad, null);
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}

	@Test
	public void test_modificarEspacioFisico_descripcionVacia() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            
        	// Modificacion de un espacio físico donde se produce una excepcion por la descripcion
    		this.servicioEspacios.modificar("1", this.nombre, this.capacidad, "");
        });
        
        assertEquals("descripcion: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_modificarEspacioFisico() {
		
		try {
			this.servicioEspacios.modificar("1", this.nombre, this.capacidad, "Descripcion del espacio fisico");
		} catch (RepositorioException e) {
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
	}
	
	// ---------------------------- Dar de baja un espacio fisico ---------------------------
	
	@Test
	public void test_bajaEspacioFisico_idNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Baja de un espacio fisico donde se produce una excepcion por el id
    		this.servicioEspacios.baja(null);
        });
        
        assertEquals("id: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_bajaEspacioFisico_idVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Baja de un espacio fisico donde se produce una excepcion por el id
    		this.servicioEspacios.baja("");
        });
        
        assertEquals("id: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_bajaEspacioFisico() {

		try {
			this.servicioEspacios.baja("1");
			EspacioFisicoDTO espacio = this.servicioEspacios.getEspacioFisico("1");

	        assertEquals(EstadoEspacio.CERRADO_TEMPORALMENTE, espacio.getEstado());
		} catch (RepositorioException e) {
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
	}
	
	// ---------------------------- Activar un espacio fisico ---------------------------
	
	@Test
	public void test_activarEspacioFisico_idNulo() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Activacion de un espacio fisico donde se produce una excepcion por el id
    		this.servicioEspacios.activar(null);
        });
        
        assertEquals("id: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_activarEspacioFisico_idVacio() {
		
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
          
        	// Activacion de un espacio fisico donde se produce una excepcion por el id
    		this.servicioEspacios.activar("");
        });
        
        assertEquals("id: no debe ser nulo ni vacio", exception.getMessage());
		
	}
	
	@Test
	public void test_activarEspacioFisico() {

		try {
			this.servicioEspacios.activar("1");
			EspacioFisicoDTO espacio = this.servicioEspacios.getEspacioFisico("1");

	        assertEquals(EstadoEspacio.ACTIVO, espacio.getEstado());
		} catch (RepositorioException e) {
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
	}
	
}