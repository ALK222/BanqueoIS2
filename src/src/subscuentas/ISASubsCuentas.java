package subscuentas;

import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;

public interface ISASubsCuentas {

    /**
     * Da de alta una cuenta
     * 
     * @param c cuenta que se da de alta
     * 
     */
    int altaCuenta(Cuenta c);

    /**
     * Da de baja una cuenta
     * 
     * @param c cuenta a dar de baja
     * 
     * 
     */
    int bajaCuenta(Cuenta c);

    /**
     * Consulta la lista de cuentas
     * 
     * 
     * @param titularCuenta se utiliza para consultar las cuentas de un titular
     * @param dni           clave primaria, se utiliza para consultar las cuentas de
     *                      un titular
     * 
     */
    Pair<List<Cuenta>, Integer> consultarListaCuentas(String titularCuenta, String dni);

    /**
     * Busca una cuenta dentro de la lista
     * 
     * @param numeroCuenta numero de la cuenta, que se utiliza como identificador en
     *                     la busqueda
     * 
     */
    Pair<Cuenta, Integer> buscaCuenta(int numeroCuenta);

    /**
     * Modifica los datos de una cuenta
     * 
     * @param c la cuenta
     * @throws exception lanza una excepcion si la cuenta es null
     * 
     */
    int modificarCuenta(Cuenta c);

}
