package tarjetasdao.control;

import java.util.List;

import usuariosdao.control.ISADAOUsuarios;

public class FachadaDAOTarjetas implements IFachadaDAOTarjetas {

	private ISADAOTarjetas daoTarj;
	
	// TODO: falta constructor
	
	@Override
	public boolean altaTarjeta(Tarjeta t) {
		return daoTarj.altaTarjeta(t);
	}

	@Override
	public boolean bajaTarejeta(int num_tarjeta) {
		return daoTarj.bajaTarejeta(num_tarjeta);
	}

	@Override
	public List<Tarjeta> consultarListaTarjetas(String titular_cuenta, String dni) {
		return daoTarj.consultarListaTarjetas(titular_cuenta, dni);
	}

	@Override
	public Tarjeta buscarTarjeta(int num_tarjeta) {
		return daoTarj.buscarTarjeta(num_tarjeta);
	}

	@Override
	public Tarjeta modificarTarjeta(Tarjeta t) {
		return daoTarj.modificarTarjeta(t);
	}

	@Override
	public boolean existeTarjeta(int num_tarjeta) {
		return daoTarj.existeTarjeta(num_tarjeta);
	}

}