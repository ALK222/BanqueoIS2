package substarjetas;

import java.util.List;

import dominio.Tarjeta;

public interface ISASubsTarjetas {
    boolean altaTarjeta(Tarjeta t);

    boolean bajaTarjeta(int numTarjeta);

    List<Tarjeta> consultarListaTarjetas(String dni) throws Exception;

    Tarjeta buscaTarjeta(int numTarjeta);

    boolean modificarTarjeta(Tarjeta t);

}
