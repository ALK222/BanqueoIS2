package tarjetasdao.control;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import dominio.Tarjeta;

public interface ISADAOTarjetas {
     /**
      * Alta de una tarjeta en la base de datos
      * 
      * @param t Tarjeta a la que dar de alta
      * @return Si la tarjeta se ha podido registrar
      * @throws IOException
      */
     boolean altaTarjeta(Tarjeta t) throws IOException;

     /**
      * Baja de una tarjeta de la base de datos
      * 
      * @param num_tarjeta Numero de la tarjeta a la que dar de baja
      * @return Si la tarjeta ha podido ser dada de baja
      * @throws IOException
      */
     boolean bajaTarjeta(int num_tarjeta) throws IOException;

     /**
      * Consulta la lista de tarjetas de un usuario dado su DNI
      * 
      * @param dni DNI del usuario
      * @return Una lista de tarjetas discriminada por localización
      * @throws Exception
      */
     List<Tarjeta> consultarListaTarjetas(String dni) throws IOException, UserException;

     /**
      * Busca una tarjeta en la base de datos
      * 
      * @param num_tarjeta Numero de tarjeta del usuario
      * @return La tarjeta si se ha encontrado, null si no
      * @throws IOException
      */
     Tarjeta buscarTarjeta(int num_tarjeta) throws IOException;

     /**
      * Modifica una tarjeta en la base de datos
      * 
      * @param t Tarjeta a modificar
      * @return Si se ha podido modificar con exito
      * @throws IOException
      */
     boolean modificarTarjeta(Tarjeta t) throws IOException;

     /**
      * Determina si una tarjeta existe o no
      * 
      * @param num_tarjeta Numero de la tarjeta
      * @return Si existe o no
      * @throws IOException
      */
     boolean existeTarjeta(int num_tarjeta) throws IOException;
}
