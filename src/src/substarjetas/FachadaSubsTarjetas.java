package substarjetas;

import java.util.List;

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
    public boolean altaTarjeta(Tarjeta t) {
        return subsTarjetas.altaTarjeta(t);
    }

    /**
     * Da de baja una tarjeta
     * 
     * @param num_tarjeta se utiliza para buscar la tarjeta que se tiene que dar de
     *                    baja
     * 
     */
    @Override
    public boolean bajaTarjeta(int num_tarjeta) {
        return subsTarjetas.bajaTarjeta(num_tarjeta);
    }

    /**
     * Consulta la lista de tarjetas
     * 
     * 
     * @param titular_cuenta se utiliza para consultar las tarjetas de un titular
     * @param dni            clave primaria, se utiliza para consultar las tarjetas
     *                       de un titular
     * 
     */
    @Override
    public List<Tarjeta> consultarListaTarjetas(String dni) throws Exception {
        return subsTarjetas.consultarListaTarjetas(dni);
    }

    /**
     * Busca una tarjeta dentro de la lista
     * 
     * @param num_tarjeta numero de la tarjeta, que se utiliza como identificador en
     *                    la busqueda
     * 
     */
    @Override
    public Tarjeta buscaTarjeta(int num_tarjeta) {
        return subsTarjetas.buscaTarjeta(num_tarjeta);
    }

    /**
     * Modifica los datos de una tarjeta
     * 
     * @param t la tarjeta
     * @throws exception lanza una excepcion si la tarjeta es null
     * 
     */
    @Override
    public boolean modificarTarjeta(Tarjeta t) {
        return subsTarjetas.modificarTarjeta(t);
    }

}
