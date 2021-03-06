package substarjetas.model;

import java.util.List;

import common.misc.Pair;
import dominio.Tarjeta;

/**
 * Interfaz del sistema de aplicaciones
 */
public interface ISASubsTarjetas {
    /**
     * Alta de una tarjeta en la base de datos
     * 
     * @param t Tarjeta a la que dar de alta
     * @return Si la tarjeta se ha podido registrar
     */
    int altaTarjeta(Tarjeta t);

    /**
     * Baja de una tarjeta de la base de datos
     * 
     * @param numTarjeta Numero de la tarjeta a la que dar de baja
     * @return Si la tarjeta ha podido ser dada de baja
     */
    int bajaTarjeta(int numTarjeta);

    /**
     * Consulta la lista de tarjetas para obtener solo las que sean de un
     * determinado usuario
     * 
     * 
     * @param dni DNI del usuario
     * @return Una lista de tarjetas asociadas a un usuario
     */
    Pair<List<Tarjeta>, Integer> consultarListaTarjetas(String dni);

    /**
     * Busca una tarjeta en la base de datos
     * 
     * @param numTarjeta Numero de la tarjeta que se busca
     * @return La tarjeta si se ha encontrado, null si no
     */
    Pair<Integer, Tarjeta> buscaTarjeta(int numTarjeta);

    /**
     * Modifica una tarjeta en la base de datos
     * 
     * @param t Tarjeta ya modificada
     * @return Si se ha podido modificar o no
     */
    int modificarTarjeta(Tarjeta t);

}
