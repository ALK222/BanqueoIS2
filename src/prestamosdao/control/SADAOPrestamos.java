package prestamosdao.control;

import java.util.ArrayList;
import java.util.List;

import cuentadao.control.Cuenta;

/**
 * Gestiona la informacion saliente sobre los prestamos
 */
public class SADAOPrestamos implements ISADAOPrestamos {

    // ATRIBUTOS

    private List<Prestamo> _listaPrestamos;

    private static final int MAX_PRESTAMOS = 9; // MAXIMO TEMPORAL

    // METODOS

    @Override
    public boolean solicitaPrestamo(Cuenta c, Prestamo p) {
        int prestamosActuales = consultarListaPrestamos(c).size();

        if (prestamosActuales <= MAX_PRESTAMOS) {
            return true;
        }

        return false;
    }

    @Override
    public boolean cancelarSolicitud(int numReferencia) {
        Prestamo aux = null;
        for (Prestamo p : _listaPrestamos) {
            if (p.getNumReferencia() == numReferencia) {
                if (p.getEstado_prestamo() == EstadoPrestamo.SOLICITADO) {
                    aux = p;
                }
            }
        }

        if (aux != null) {
            _listaPrestamos.remove(aux);
            return true;
        }
        return false;
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) {
        Prestamo aux = null;
        for (Prestamo pr : _listaPrestamos) {
            if (pr.getNumReferencia() == pr.getNumReferencia()) {
                aux = pr;
            }
        }

        if (aux != null) {
            _listaPrestamos.remove(aux);
            _listaPrestamos.add(p);
            return true;
        }
        return false;
    }

    @Override
    public boolean existePrestamo(int numReferencia) {
        for (Prestamo prestamo : _listaPrestamos) {
            if (prestamo.getNumReferencia() == numReferencia) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Prestamo> consultarListaPrestamos(Cuenta c) {
        List<Prestamo> aux = new ArrayList<Prestamo>();
        for (Prestamo prestamo : aux) {
            if (prestamo.esCuentaAsociada(c)) {
                aux.add(prestamo);
            }
        }
        return aux;
    }

    @Override
    public Prestamo buscaPrestamo(int numReferencia) {
        for (Prestamo prestamo : _listaPrestamos) {
            if (prestamo.getNumReferencia() == numReferencia) {
                return prestamo;
            }
        }
        return null;
    }
}
