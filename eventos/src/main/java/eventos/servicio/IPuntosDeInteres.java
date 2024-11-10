package eventos.servicio;

import java.util.List;

import eventos.modelo.PuntoDeInteres;

public interface IPuntosDeInteres {

	List<PuntoDeInteres> obtenerPuntosDeInteres(double latitud, double longitud) throws Exception;

}
