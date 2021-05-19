package tarjetasdao.control;

import java.io.IOException;
import java.util.List;

import dominio.Tarjeta;

public class FachadaDAOTarjetas implements IFachadaDAOTarjetas {

	private ISADAOTarjetas daoTarj;


	public FachadaDAOTarjetas(){
		daoTarj = new SADAOTarjetas();
	}

	@Override
	public boolean altaTarjeta(Tarjeta t ) throws IOException{
		return daoTarj.altaTarjeta(t);
	}

	@Override
	public boolean bajaTarjeta(int num_tarjeta) throws IOException{
		return daoTarj.bajaTarjeta(num_tarjeta);
	}

	@Override
	public List<Tarjeta> consultarListaTarjetas(String dni) throws Exception {
		return daoTarj.consultarListaTarjetas(dni);
	}

	@Override
	public Tarjeta buscarTarjeta(int num_tarjeta) throws IOException{
		return daoTarj.buscarTarjeta(num_tarjeta);
	}

	@Override
	public boolean modificarTarjeta(Tarjeta t) throws IOException{
		return daoTarj.modificarTarjeta(t);
	}

	@Override
	public boolean existeTarjeta(int num_tarjeta)throws IOException {
		return daoTarj.existeTarjeta(num_tarjeta);
	}

}
