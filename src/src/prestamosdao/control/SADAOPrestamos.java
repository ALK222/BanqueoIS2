package prestamosdao.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cuenta;
import dominio.EstadoPrestamo;
import dominio.Prestamo;

/**
 * Gestiona la informacion saliente sobre los prestamos
 */
public class SADAOPrestamos implements ISADAOPrestamos {

    // ATRIBUTOS

    public static final int MAX_PRESTAMOS = 9; // MAXIMO TEMPORAL

    // METODOS

    @Override
    public boolean solicitaPrestamo(Cuenta c, Prestamo p) throws IOException {
        List<Prestamo> listaPrestamos = PrestamoJSON.leerListaPrestamos();
        int prestamosActuales = consultarListaPrestamos(c, listaPrestamos).size();

        if (prestamosActuales < MAX_PRESTAMOS) {
            listaPrestamos.add(p);
            PrestamoJSON.guardarListaPrestamos(listaPrestamos);
            return true;
        }
        // Si no modificamos, no volvemos a guardar
        return false;
    }

    @Override
    public boolean cancelarSolicitud(int numReferencia) throws IOException {
        List<Prestamo> listaPrestamos = PrestamoJSON.leerListaPrestamos();
        Prestamo aux = null;
        for (Prestamo p : listaPrestamos) {
            if (p.getNumReferencia() == numReferencia) {
                if (p.getEstadoPrestamo() == EstadoPrestamo.SOLICITADO) {
                    aux = p;
                }
            }
        }

        if (aux != null) {
            listaPrestamos.remove(aux);
            PrestamoJSON.guardarListaPrestamos(listaPrestamos);
            return true;
        }
        // Si no modificamos, no volvemos a guardar
        return false;
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) throws IOException {
        List<Prestamo> listaPrestamos = PrestamoJSON.leerListaPrestamos();
        Prestamo aux = null;
        for (Prestamo pr : listaPrestamos) {
            if (p.getNumReferencia() == pr.getNumReferencia()) {
                aux = pr;
            }
        }

        if (aux != null) {
            listaPrestamos.remove(aux);
            listaPrestamos.add(p);
            PrestamoJSON.guardarListaPrestamos(listaPrestamos);
            return true;
        }
        // Si no modificamos, no volvemos a guardar
        return false;
    }

    @Override
    public boolean existePrestamo(int numReferencia) throws FileNotFoundException {
        List<Prestamo> listaPrestamos = PrestamoJSON.leerListaPrestamos();
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.getNumReferencia() == numReferencia) {
                return true;
            }
        }
        // No guardamos en ningun momento porque no modificamos
        return false;
    }

    @Override
    public List<Prestamo> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos) {
        List<Prestamo> aux = new ArrayList<Prestamo>();
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.esCuentaAsociada(c)) {
                aux.add(prestamo);
            }
        }
        return aux;
    }

    @Override
    public Prestamo buscaPrestamo(int numReferencia) throws FileNotFoundException {
        List<Prestamo> listaPrestamos = PrestamoJSON.leerListaPrestamos();
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.getNumReferencia() == numReferencia) {
                return prestamo;
            }
        }
        return null;
    }
}
