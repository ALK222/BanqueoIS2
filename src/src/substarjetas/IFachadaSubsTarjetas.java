package substarjetas;

import java.util.List;

import tarjetasdao.control.Tarjeta;

public interface IFachadaSubsTarjetas {
     /**
     * Da de alta una tarjeta, creando una y añadiendola a la lista
     * 
     * @param t tarjeta que se va a agregar
     * 
     */
    boolean altaTarjeta(Tarjeta t); 

    boolean bajaTarjeta(int num_tarjeta);

    List<Tarjeta> consultarListaTarjetas(String titular_cuenta, String dni); 

    Tarjeta buscaTarjeta(int num_tarjeta); 
    
    boolean modificarTarjeta(Tarjeta t); 

}
