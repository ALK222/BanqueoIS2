package substarjetas;

import java.util.List;

import tarjetasdao.control.Tarjeta;

public interface ISASubsTarjetas {
    boolean altaTarjeta(Tarjeta t); 

    boolean bajaTarjeta(int num_tarjeta);

    List<Tarjeta> consultarListaTarjetas(String dni) throws Exception; 

    Tarjeta buscaTarjeta(int num_tarjeta); 

    boolean modificarTarjeta(Tarjeta t); 

}
