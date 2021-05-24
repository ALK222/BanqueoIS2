package subsprestamos;

import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;
import dominio.Prestamo;

/**
 * Intermediario entre el SAPrestamos y la gui
 */
public class FachadaSubsPrestamos implements IFachadaSubsPrestamos {
    ISASubsPrestamos subsPrestamo;

    public FachadaSubsPrestamos() {
        subsPrestamo = new SASubsPrestamos();
    }

    @Override
    public int solicitarPrestamo(Cuenta c, Prestamo p) {
        return subsPrestamo.solicitarPrestamo(c, p);

    }

    @Override
    public int cancelarSolicitud(int numRef) {
        return subsPrestamo.cancelarSolicitud(numRef);
    }

    @Override
    public int aceptarSolicitud(int numRef) {
        return subsPrestamo.aceptarSolicitud(numRef);
    }

    @Override
    public Pair<List<Prestamo>, Integer> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos) {
        return subsPrestamo.consultarListaPrestamos(c, listaPrestamos);
    }

    @Override
    public Pair<Prestamo, Integer> buscarPrestamo(int numRef) {
        return subsPrestamo.buscarPrestamo(numRef);
    }

    @Override
    public int modificarPrestamo(Prestamo p) {
        return subsPrestamo.modificarPrestamo(p);
    }

}
