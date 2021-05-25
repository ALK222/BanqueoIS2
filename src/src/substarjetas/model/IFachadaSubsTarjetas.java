package substarjetas.model;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import common.misc.Pair;
import dominio.Tarjeta;

/**
 * Interfaz de la fachada de SAPrestamos
 */
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
   *                   baja
   * 
   */
  int bajaTarjeta(int numTarjeta);

  /**
   * Consulta la lista de tarjetas
   * 
   * 
   * @param dni clave primaria, se utiliza para consultar las tarjetas de un
   *            titular
   * @throws IOException
   * @throws UserException
   * 
   */
  Pair<List<Tarjeta>, Integer> consultarListaTarjetas(String dni);

  /**
   * Busca una tarjeta dentro de la lista
   * 
   * @param numTarjeta numero de la tarjeta, que se utiliza como identificador en
   *                   la busqueda
   * 
   */
  Pair<Integer, Tarjeta> buscaTarjeta(int numTarjeta);

  /**
   * Modifica los datos de una tarjeta
   * 
   * @param t la tarjeta
   * 
   */
  int modificarTarjeta(Tarjeta t);

}
