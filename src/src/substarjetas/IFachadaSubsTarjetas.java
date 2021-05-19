package substarjetas;

import java.util.List;

import dominio.Tarjeta;

public interface IFachadaSubsTarjetas {
    /**
     * Da de alta una tarjeta, creando una y añadiendola a la lista
     * 
     * @param t tarjeta que se va a agregar
     * 
     */
    boolean altaTarjeta(Tarjeta t);

    boolean bajaTarjeta(int numTarjeta);

    List<Tarjeta> consultarListaTarjetas(String dni) throws Exception;

    Tarjeta buscaTarjeta(int numTarjeta);

    boolean modificarTarjeta(Tarjeta t);

}
