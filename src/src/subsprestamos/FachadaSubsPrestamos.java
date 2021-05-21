package subsprestamos;

import java.io.IOException;
import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;
import dominio.Prestamo;

public class FachadaSubsPrestamos implements IFachadaSubsPrestamos {
    ISASubsPrestamos subsPrestamo;

    public FachadaSubsPrestamos() {
        subsPrestamo = new SASubsPrestamos();
    }

    @Override
    public int solicitarPrestamo(Cuenta c, Prestamo p) throws IOException {
        return subsPrestamo.solicitarPrestamo(c, p);

    }

    @Override
    public int cancelarSolicitud(int numRef) throws IOException {
        return subsPrestamo.cancelarSolicitud(numRef);
    }

    @Override
    public Pair<List<Prestamo>, Integer> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos)
            throws Exception {
        return subsPrestamo.consultarListaPrestamos(c, listaPrestamos);
    }

    @Override
    public Pair<Prestamo, Integer> buscarPrestamo(int numRef) throws Exception {
        return subsPrestamo.buscarPrestamo(numRef);
    }

    @Override
    public int modificarPrestamo(Prestamo p) throws IOException {
        return subsPrestamo.modificarPrestamo(p);
    }

}
