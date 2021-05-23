package cuentadao.control;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;

import common.exception.UserException;
import dominio.Cuenta;

/**
 * Interfaz del DAO de usuarios
 */
public interface ISADAOCuenta {
    /**
     * Alta de una cuenta en la base de datos
     * 
     * @param c Cuenta a la que dar de alta
     * @return Si la cuenta se ha podido registrar
     * @throws IOException Si el archivo no se pudo encontrar o reescribir
     */
    boolean altaCuenta(Cuenta c) throws IOException;

    /**
     * Baja de una cuenta de la base de datos
     * 
     * @param c Cuenta a la que dar de baja
     * @return Si la cuenta ha podido ser dada de baja o no
     * @throws IOException Si el archivo no se pudo encontrar o sobreescribir.
     */
    boolean bajaCuenta(Cuenta c) throws IOException;

    /**
     * Busca las cuentas pertenecientes a un usuario
     * 
     * @param titular_cuenta Nombre del titular de la cuenta
     * @param dni            DNI del titular de la cuenta
     * @return La lista de cuentas asociadas a ese usuario
     * @throws IOException   Si no se pudo encontrar el archivo o sobreescribirse
     * @throws UserException Si el DNI o el Nombre no son validos
     */
    List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni) throws IOException, UserException;

    /**
     * Devuelve la cuenta buscada
     * 
     * @param num_cuenta numero de referencia de la cuenta
     * @return La cuenta asociada al numero de referencia
     * @throws IOException Si el archivo no pudo ser encontrado o reescrito.
     */
    Cuenta consultarCuenta(int num_cuenta) throws IOException;

    /**
     * Modificacion de una cuenta
     * 
     * @param c Cuenta modificada
     * @return Si los cambios se pudieron guardar o no en la base de datos
     * @throws IOException Si el archivo no pudo ser encontrado o reescrito.
     */
    boolean modificarCuentas(Cuenta c) throws IOException;

    /**
     * Devuelve los movimientos de una cuenta a lo largo de un numero de meses
     * 
     * @param maxMeses Numero de meses maximos para los movimientos
     * @param c        Cuenta asociada a los movimientos
     * @return Un array con trodos los movimientos y sus datos
     * @throws IOException Si el archivo no pudo ser encotrado o reescrito.
     */
    JSONArray getMovimiento(int maxMeses, Cuenta c) throws IOException;
}
