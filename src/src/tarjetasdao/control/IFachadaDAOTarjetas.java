package tarjetasdao.control;

import java.util.List;

public interface IFachadaDAOTarjetas {
	
	boolean altaTarjeta(Tarjeta t);
	
	boolean bajaTarejeta(int num_tarjeta);
	
	List<Tarjeta> consultarListaTarjetas(String titular_cuenta, String dni);
	
	Tarjeta buscarTarjeta(int num_tarjeta);
	
	Tarjeta modificarTarjeta(Tarjeta t);
	
	boolean existeTarjeta(int num_tarjeta);
}
