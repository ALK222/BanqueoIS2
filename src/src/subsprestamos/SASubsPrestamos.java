package subsprestamos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;
import dominio.Prestamo;
import prestamosdao.control.FachadaDAOPrestamos;
import prestamosdao.control.IFachadaDAOPrestamos;

public class SASubsPrestamos implements ISASubsPrestamos {
    IFachadaDAOPrestamos prestamo;

    public SASubsPrestamos() {
        prestamo = new FachadaDAOPrestamos();
    }

    @Override
    public int solicitarPrestamo(Cuenta c, Prestamo p) {
        try {
            if (prestamo.solicitarPrestamo(c, p)) {
                return 0;
            } else {
                return 1;
            }
        } catch (IOException e) {
            return 10;
        }

    }

    @Override
    public int cancelarSolicitud(int numRef) {
        try {
            if (prestamo.cancelarSolicitud(numRef)) {
                return 0;
            } else {
                return 1;
            }
        } catch (IOException e) {
            return 10;
        }
    }

    @Override
    public Pair<List<Prestamo>, Integer> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos) {
        List<Prestamo> listaPrestamosBuscados = prestamo.consultarListaPrestamos(c, listaPrestamos);
        if (listaPrestamosBuscados != null) {
            return new Pair<List<Prestamo>, Integer>(listaPrestamosBuscados, 0);
        }
        return new Pair<List<Prestamo>, Integer>(null, 1);
    }

    @Override
    public Pair<Prestamo, Integer> buscarPrestamo(int numRef) {
        try {
            Prestamo prestamoBuscado = prestamo.buscarPrestamo(numRef);
            if (prestamoBuscado == null) {
                return new Pair<>(null, 1);
            }
            return new Pair<>(prestamoBuscado, 0);
        } catch (FileNotFoundException e) {
            return new Pair<>(null, 10);
        }
    }

    @Override
    public int modificarPrestamo(Prestamo p) { // posible bool
        try {
            return prestamo.modificarPrestamo(p) ? 0 : 1;
        } catch (IOException e) {
            return 10;
        }
    }

}
