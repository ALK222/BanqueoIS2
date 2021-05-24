package tarjetasdao.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.exception.UserException;
import dominio.Tarjeta;

/**
 * Gestiona la informacion saliente sobre las Tarjetas
 */
public class SADAOTarjetas implements ISADAOTarjetas {

	@Override
	public boolean altaTarjeta(Tarjeta t) throws IOException { // Se ha cambiado de Tarjeta a boolean
		List<Tarjeta> listaTarjetas = TarjetasJSON.leerListaTarjetas();
		boolean ok = listaTarjetas.contains(t) ? false : listaTarjetas.add(t);
		TarjetasJSON.guardarListaTarjetas(listaTarjetas);
		return ok;
	}

	@Override
	public boolean bajaTarjeta(int num_tarjeta) throws IOException {
		Tarjeta t = null;
		List<Tarjeta> listaTarjetas = TarjetasJSON.leerListaTarjetas();
		int i = 0;
		while (t == null && i < listaTarjetas.size()) {
			if (num_tarjeta == listaTarjetas.get(i).getNum_tarjeta())
				t = listaTarjetas.get(i);
			++i;
		}
		boolean ok = t != null ? listaTarjetas.remove(t) : false;
		TarjetasJSON.guardarListaTarjetas(listaTarjetas);
		return ok;
	}

	@Override
	public List<Tarjeta> consultarListaTarjetas(String dni) throws UserException, FileNotFoundException {
		List<Tarjeta> listaTarjetas = TarjetasJSON.leerListaTarjetas();
		List<Tarjeta> listaAux = new ArrayList<Tarjeta>();
		for (Tarjeta t : listaTarjetas) {
			if (t.get_dni().equals(dni)) {
				listaAux.add(t);
			}
		}
		if (listaAux.isEmpty()) {
			throw new UserException("No hay tarjetas asociadas a este usuario");
		}
		return listaAux;
	}

	@Override
	public Tarjeta buscarTarjeta(int num_tarjeta) throws IOException {
		List<Tarjeta> listaTarjetas = TarjetasJSON.leerListaTarjetas();
		try {
			for (Tarjeta t : listaTarjetas) {
				if (t.getNum_tarjeta() == num_tarjeta)
					return t;
			}
		} catch (Exception e) {
			throw e;
		}
		TarjetasJSON.guardarListaTarjetas(listaTarjetas);
		return null;
	}

	@Override
	public boolean modificarTarjeta(Tarjeta t) throws IOException {
		List<Tarjeta> listaTarjetas = TarjetasJSON.leerListaTarjetas();
		boolean conseguido = false;
		int i = 0;
		while (i < listaTarjetas.size() && !conseguido) {
			if (t.compareTo(listaTarjetas.get(i)) == 0) {
				conseguido = true;
			}
			if (!conseguido) {
				i++;
			}
		}
		if (conseguido) {
			listaTarjetas.remove(i);
			listaTarjetas.add(t);
		}
		return conseguido;
	}

	@Override
	public boolean existeTarjeta(int num_tarjeta) throws IOException {
		List<Tarjeta> listaTarjetas = TarjetasJSON.leerListaTarjetas();
		for (Tarjeta t : listaTarjetas) {
			if (t.getNum_tarjeta() == num_tarjeta) {
				return true;
			}
		}
		return false;
	}

}
