package eventos.repositorio;

import eventos.modelo.EspacioFisico;
import repositorio.RepositorioJPA;

public class RepositorioEspacioFisicoJPA extends RepositorioJPA<EspacioFisico>{

	@Override
	public Class<EspacioFisico> getClase() {
		return EspacioFisico.class;
	}

}
