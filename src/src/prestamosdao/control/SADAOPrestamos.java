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
        int i = 0;
        boolean encontrado = false;
        while (i != 0 && !encontrado) {
            if (listaPrestamos.get(i).getNumReferencia() == numReferencia) {
                listaPrestamos.get(i).setEstadoPrestamo(EstadoPrestamo.DENEGADO);
                encontrado = true;
            }
        }
        if (encontrado) {
            PrestamoJSON.guardarListaPrestamos(listaPrestamos);
        }
        // Si no modificamos, no volvemos a guardar
        return false;
    }

    public boolean aceptarSolicitud(int numReferencia) throws IOException {
        List<Prestamo> listaPrestamos = PrestamoJSON.leerListaPrestamos();
        int i = 0;
        boolean encontrado = false;
        while (i != 0 && !encontrado) {
            if (listaPrestamos.get(i).getNumReferencia() == numReferencia) {
                listaPrestamos.get(i).setEstadoPrestamo(EstadoPrestamo.ACEPTADO);
                encontrado = true;
            }
        }
        if (encontrado) {
            PrestamoJSON.guardarListaPrestamos(listaPrestamos);
        }
        // Si no modificamos, no volvemos a guardar
        return false;
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) throws IOException {
        List<Prestamo> listaPrestamos = PrestamoJSON.leerListaPrestamos();

        int i = 0;
        boolean encontrado = false;
        while (i < listaPrestamos.size() && !encontrado) {
            if (p.getNumReferencia() == listaPrestamos.get(i).getNumReferencia()) {
                encontrado = true;
            }
            if (!encontrado) {
                i++;
            }
        }

        if (encontrado) {
            listaPrestamos.remove(i);
            listaPrestamos.add(p);
        }
        return encontrado;
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
