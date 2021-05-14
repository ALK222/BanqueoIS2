package prestamosdao.control;

import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;

/**
 * Interfaz del SADAO de prestamos
 */
public interface ISADAOPrestamos {

    /**
     * Solicitud de un prestamo donde se decide si es aceptado o no.
     * 
     * @param c cuenta ligada al prestamo
     * @param p prestamo solicitado
     * @return Si el cliente ha podido conseguir el prestamo o si no
     */
    boolean solicitaPrestamo(Cuenta c, Prestamo p);

    /**
     * Cancela una solicitud de un prestamo en proceso.
     * 
     * @param numRef numero de referencia del prestamo a cancelar
     * @return Si el prestamo se ha podido cancelar
     */
    boolean cancelarSolicitud(int numRef);

    /**
     * Modifica un prestamo existente.
     * 
     * @param p prestamo ya modificado
     * @return Si el prestamo se ha modificado con exito o no
     */
    boolean modificarPrestamo(Prestamo p);

    /**
     * Comprueba si un prestamo existe
     * 
     * @param numRef numero de referencia del prestamo a comprobar
     * @return si el prestamo existe o no
     */
    boolean existePrestamo(int numRef);

    /**
     * Consulta todos los prestamos asociados a una cuenta
     * 
     * @param c cuenta asociada a los prestamos
     * @return una lista con todos los prestamos asociados a la cuenta
     */
    List<Prestamo> consultarListaPrestamos(Cuenta c);

    /**
     * Busca un prestamo concreto en la base de datos
     * 
     * @param numRef numero de referencia del prestamo a buscar
     * @return el prestamo en caso de que exista
     */
    Prestamo buscaPrestamo(int numRef);

}
