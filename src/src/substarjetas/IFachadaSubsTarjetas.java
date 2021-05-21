package substarjetas;

import java.util.List;

import common.misc.Pair;
import dominio.Tarjeta;

public interface IFachadaSubsTarjetas {
    /**
     * Da de alta una tarjeta, creando una y añadiendola a la lista
     * 
     * @param t tarjeta que se va a agregar
     * 
     */
    int altaTarjeta(Tarjeta t);

      /**
     * Da de baja una tarjeta
     * 
     * @param numTarjeta se utiliza para buscar la tarjeta que se tiene que dar de
     *                    baja
     * 
     */
    int bajaTarjeta(int numTarjeta);

    
    /**
     * Consulta la lista de tarjetas
     * 
     * 
     * @param dni            clave primaria, se utiliza para consultar las tarjetas
     *                       de un titular
     * 
     */
    Pair<List<Tarjeta>,Integer> consultarListaTarjetas(String dni) throws Exception;

    /**
     * Busca una tarjeta dentro de la lista
     * 
     * @param numTarjeta numero de la tarjeta, que se utiliza como identificador en
     *                    la busqueda
     * 
     */
    int buscaTarjeta(int numTarjeta);

    
    /**
     * Modifica los datos de una tarjeta
     * 
     * @param t la tarjeta
     * @throws exception lanza una excepcion si la tarjeta es null
     * 
     */
    int modificarTarjeta(Tarjeta t);

}
