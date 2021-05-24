package prestamosdao.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;

/**
 * Interfaz de la fachada de prestamos
 */
public interface IFachadaDAOPrestamos {

    /**
     * Solicitud de un prestamo donde se decide si es aceptado o no.
     * 
     * @param c cuenta ligada al prestamo
     * @param p prestamo solicitado
     * @return Si el cliente ha podido conseguir el prestamo o si no
     * @throws IOException
     * @throws FileNotFoundException
     */
    boolean solicitarPrestamo(Cuenta c, Prestamo p) throws FileNotFoundException, IOException;

    /**
     * Cancela una solicitud de un prestamo en proceso.
     * 
     * @param numRef numero de referencia del prestamo a cancelar
     * @return Si el prestamo se ha podido cancelar
     * @throws IOException
     * @throws FileNotFoundException
     */
    boolean cancelarSolicitud(int numRef) throws FileNotFoundException, IOException;

    /**
     * Acepta una solicitud de un prestamo en proceso.
     * 
     * @param numRef numero de referencia del prestamo a cancelar
     * @return Si el prestamo se ha podido aceptar
     * @throws FileNotFoundException
     * @throws IOException
     */
    boolean aceptarSolicitud(int numRef) throws FileNotFoundException, IOException;

    /**
     * Modifica un prestamo existente.
     * 
     * @param p prestamo ya modificado
     * @return Si el prestamo se ha modificado con exito o no
     * @throws IOException
     */
    boolean modificarPrestamo(Prestamo p) throws IOException;

    /**
     * Comprueba si un prestamo existe
     * 
     * @param numRef numero de referencia del prestamo a comprobar
     * @return si el prestamo existe o no
     * @throws FileNotFoundException
     */
    boolean existePrestamo(int numRef) throws FileNotFoundException;

    /**
     * Consulta todos los prestamos asociados a una cuenta
     * 
     * @param c cuenta asociada a los prestamos
     * @return una lista con todos los prestamos asociados a la cuenta
     * @throws FileNotFoundException
     */
    List<Prestamo> consultarListaPrestamos(Cuenta c) throws FileNotFoundException;

    /**
     * Busca un prestamo concreto en la base de datos
     * 
     * @param numRef numero de referencia del prestamo a buscar
     * @return el prestamo en caso de que exista
     * @throws FileNotFoundException
     */
    Prestamo buscarPrestamo(int numRef) throws FileNotFoundException;

}
