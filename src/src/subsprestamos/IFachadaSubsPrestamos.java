package subsprestamos;

import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;
import dominio.Prestamo;

/**
 * Interfaz de la fachada de SAPrestamos
 */
public interface IFachadaSubsPrestamos {

    /**
     * Solicitud de un prestamo
     * 
     * @param c Cuenta asociada al prestamo
     * @param p Prestamo a solicitar
     * @return 0 si todo fue bien, 1 si no se pudo solicitar, 2 si hubo una
     *         UserException y 10 si hubo una IOException
     */
    int solicitarPrestamo(Cuenta c, Prestamo p);

    /**
     * Cancelacion de una solicitud de prestamo
     * 
     * @param numRef Numero de referencia del Prestamo
     * @return 0 si todo fue bien, 1 si no se pudo solicitar, 2 si hubo una
     *         UserException y 10 si hubo una IOException
     */
    int cancelarSolicitud(int numRef);

    /**
     * Aceptar una solicitud de prestamo
     * 
     * @param numRef Numero de referencia del Prestamo
     * @return 0 si todo fue bien, 1 si no se pudo solicitar, 2 si hubo una
     *         UserException y 10 si hubo una IOException
     */
    int aceptarSolicitud(int numRef);

    /**
     * Consulta una lista de prestamos
     * 
     * @param c Cuenta asociada al prestamo
     * @return 0 si todo fue bien, 1 si no se pudo solicitar, 2 si hubo una
     *         UserException y 10 si hubo una IOException y un listado de prestamos
     *         si todo sale bien
     */
    Pair<List<Prestamo>, Integer> consultarListaPrestamos(Cuenta c);

    /**
     * Busca un prestamo dado un numero de referencia
     * 
     * @param numRef Numero de referencia del prestamo
     * @return 0 si todo fue bien, 1 si no se pudo solicitar, 2 si hubo una
     *         UserException y 10 si hubo una IOException y un prestamo si todo ha
     *         salido bien
     */
    Pair<Prestamo, Integer> buscarPrestamo(int numRef);

    /**
     * Modificacion de un Prestamo
     * 
     * @param p Prestamo modificado
     * @return 0 si todo fue bien, 1 si no se pudo solicitar, 2 si hubo una
     *         UserException y 10 si hubo una IOException
     */
    int modificarPrestamo(Prestamo p);

}
