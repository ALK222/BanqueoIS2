package tarjetasdao.control;

import java.util.List;

public interface IFachadaDAOTarjetas {
	
	boolean altaTarjeta(Tarjeta t);
	
	boolean bajaTarejeta(int num_tarjeta);
	
	List<Tarjeta> consultarListaTarjetas(String dni) throws Exception;
	
	Tarjeta buscarTarjeta(int num_tarjeta);
	
	boolean modificarTarjeta(Tarjeta t);
	
	boolean existeTarjeta(int num_tarjeta);
}
