package subscuentas;

import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;

/**
 * Interfaz de la fachada del sistema de aplicaciones
 */
public interface IFachadaSubsCuentas {
    /**
     * Da de alta una cuenta
     * 
     * @param c cuenta que se da de alta
     * @return 0 si todo va bien, 1 si no se pudo dar de alta, 2 si hubo una
     *         UserException y 10 si hubo una IOException
     */
    int altaCuenta(Cuenta c);

    /**
     * Da de baja una cuenta
     * 
     * @param c cuenta a dar de baja
     * 
     * @return 0 si todo va bien, 1 si no se pudo dar de alta, 2 si hubo una
     *         UserException y 10 si hubo una IOException
     */
    int bajaCuenta(Cuenta c);

    /**
     * Consulta la lista de cuentas
     * 
     * 
     * @param titularCuenta se utiliza para consultar las cuentas de un titular
     * @param dni           clave primaria, se utiliza para consultar las cuentas de
     *                      un titular
     * @return 0 si todo va bien, 1 si no se pudo dar de alta, 2 si hubo una
     *         UserException y 10 si hubo una IOException, junto a una lista de
     *         cuentas si todo salio bien
     */
    Pair<List<Cuenta>, Integer> consultarListaCuentas(String titularCuenta, String dni);

    /**
     * Busca una cuenta dentro de la lista
     * 
     * @param numeroCuenta numero de la cuenta, que se utiliza como identificador en
     *                     la busqueda
     * @return 0 si todo va bien, 1 si no se pudo dar de alta, 2 si hubo una
     *         UserException y 10 si hubo una IOException, junto a una cuenta si
     *         todo salio bien
     */
    Pair<Cuenta, Integer> buscaCuenta(int numeroCuenta);

    /**
     * Modifica los datos de una cuenta
     * 
     * @param c la cuenta
     * @throws exception lanza una excepcion si la cuenta es null
     * @return 0 si todo va bien, 1 si no se pudo dar de alta, 2 si hubo una
     *         UserException y 10 si hubo una IOException
     */
    int modificarCuenta(Cuenta c);

}
