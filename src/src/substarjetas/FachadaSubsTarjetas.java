package substarjetas;

import java.util.List;

import common.misc.Pair;
import dominio.Tarjeta;

public class FachadaSubsTarjetas implements IFachadaSubsTarjetas {
    ISASubsTarjetas subsTarjetas;

    /**
     * Da de alta una tarjeta, creando una y añadiendola a la lista
     * 
     * @param t tarjeta que se va a agregar
     * 
     */
    @Override
    public int altaTarjeta(Tarjeta t) {
        return subsTarjetas.altaTarjeta(t);
    }

    /**
     * Da de baja una tarjeta
     * 
     * @param numTarjeta se utiliza para buscar la tarjeta que se tiene que dar de
     *                    baja
     * 
     */
    @Override
    public int bajaTarjeta(int numTarjeta) {
        return subsTarjetas.bajaTarjeta(numTarjeta);
    }

    /**
     * Consulta la lista de tarjetas
     * 
     * 
     * @param titularCuenta se utiliza para consultar las tarjetas de un titular
     * @param dni            clave primaria, se utiliza para consultar las tarjetas
     *                       de un titular
     * 
     */
    @Override
    public Pair<List<Tarjeta>,Integer> consultarListaTarjetas(String dni) throws Exception {
        return subsTarjetas.consultarListaTarjetas(dni);
    }

    /**
     * Busca una tarjeta dentro de la lista
     * 
     * @param numTarjeta numero de la tarjeta, que se utiliza como identificador en
     *                    la busqueda
     * 
     */
    @Override
    public int buscaTarjeta(int numTarjeta) {
        return subsTarjetas.buscaTarjeta(numTarjeta);
    }

    /**
     * Modifica los datos de una tarjeta
     * 
     * @param t la tarjeta
     * @throws exception lanza una excepcion si la tarjeta es null
     * 
     */
    @Override
    public int modificarTarjeta(Tarjeta t) {
        return subsTarjetas.modificarTarjeta(t);
    }

}
