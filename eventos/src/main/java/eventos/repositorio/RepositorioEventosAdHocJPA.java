package eventos.repositorio;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import eventos.modelo.Evento;
import repositorio.RepositorioJPA;


public class RepositorioEventosAdHocJPA extends RepositorioJPA<Evento> implements RepositorioEventosAdHoc {

	@Override
	public Class<Evento> getClase() {
		return Evento.class;
	}

}
