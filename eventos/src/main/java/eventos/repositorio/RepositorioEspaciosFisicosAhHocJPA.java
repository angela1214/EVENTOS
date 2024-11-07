package eventos.repositorio;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import eventos.modelo.EspacioFisico;
import repositorio.RepositorioJPA;


public class RepositorioEspaciosFisicosAhHocJPA extends RepositorioJPA<EspacioFisico> implements RepositorioEspaciosFisicosAdHoc {

	@Override
	public Class<EspacioFisico> getClase() {
		// TODO Auto-generated method stub
		return null;
	}

}
