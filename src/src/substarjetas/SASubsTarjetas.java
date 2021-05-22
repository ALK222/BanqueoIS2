package substarjetas;

import java.io.IOException;
import java.util.List;

import common.misc.Pair;
import dominio.Tarjeta;
import tarjetasdao.control.FachadaDAOTarjetas;
import tarjetasdao.control.IFachadaDAOTarjetas;

public class SASubsTarjetas implements ISASubsTarjetas {
    IFachadaDAOTarjetas tarjeta;

    public SASubsTarjetas() {
        tarjeta = new FachadaDAOTarjetas();
    }

    /**
     * Da de alta una tarjeta, creando una y añadiendola a la lista
     * 
     * @param t tarjeta que se va a agregar
     * 
     */
    @Override
    public int altaTarjeta(Tarjeta t) {

        try {
            if (tarjeta.existeTarjeta(t.getNum_tarjeta())) {
                return 1;
            } else {
                tarjeta.altaTarjeta(t);
                return 0;
            }

        } catch (IOException e) {
            return 10;
        }

    }

    /**
     * Da de baja una tarjeta
     * 
     * @param numTarjeta se utiliza para buscar la tarjeta que se tiene que dar de
     *                   baja
     * 
     */
    @Override
    public int bajaTarjeta(int numTarjeta) {

        try {
            if (!tarjeta.existeTarjeta(numTarjeta)) {
                return 1;
            } else {
                tarjeta.bajaTarjeta(numTarjeta);
                return 0;
            }

        } catch (IOException e) {
            return 10;

        }

    }

    /**
     * Consulta la lista de tarjetas
     * 
     * 
     * @param titularCuenta se utiliza para consultar las tarjetas de un titular
     * @param dni           clave primaria, se utiliza para consultar las tarjetas
     *                      de un titular
     * 
     */
    @Override
    public Pair<List<Tarjeta>, Integer> consultarListaTarjetas(String dni) throws Exception {
        try {
            List<Tarjeta> listaAux = tarjeta.consultarListaTarjetas(dni);
            if (listaAux == null) {
                return new Pair<List<Tarjeta>, Integer>(null, 1);
            } else {
                return new Pair<List<Tarjeta>, Integer>(listaAux, 0);
            }
        } catch (IOException e) {
            return new Pair<List<Tarjeta>, Integer>(null, 10);
        }

    }

    /**
     * Busca una tarjeta dentro de la lista
     * 
     * @param numTarjeta numero de la tarjeta, que se utiliza como identificador en
     *                   la busqueda
     * 
     */
    @Override
    public int buscaTarjeta(int numTarjeta) {
        /**
         * for(Tarjeta t : _listaTarjetas){ if(t.getNum_tarjeta() == numTarjeta){ return
         * t; } } return null;
         */
        try {
            if (tarjeta.buscarTarjeta(numTarjeta) == null) {
                return 1;
            } else {
                tarjeta.buscarTarjeta(numTarjeta);
                return 0;
            }
        } catch (IOException e) {
            return 10;
        }

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
        /**
         * Tarjeta aux = null; try { if(t == null){ throw new Exception(); } else {
         * for(Tarjeta tAux : _listaTarjetas){ if(tAux.getNum_tarjeta() ==
         * t.getNum_tarjeta()){ aux = t; _listaTarjetas.add(t); } } if(aux != null) {
         * _listaTarjetas.remove(aux); return true; }
         * 
         * }
         * 
         * } catch (Exception e) { System.err.println("[ERROR] :" + e.getMessage() +
         * e.getStackTrace()); } return false;
         */

        try {
            if (!tarjeta.modificarTarjeta(t)) {
                return 1;
            } else {
                return 0;
            }
        } catch (IOException e) {
            return 10;
        }

    }

}
