package subsprestamos;

import java.io.IOException;
import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;

public class FachadaSubsPrestamos implements IFachadaSubsPrestamos {
    ISASubsPrestamos subsPrestamo;

    public FachadaSubsPrestamos() {
        subsPrestamo = new SASubsPrestamos();
    }

    /*
     * public FachadaSubsPrestamos(List<Prestamo> listaPrestamos) { subsPrestamo =
     * new SASubsPrestamos(listaPrestamos); }
     */

    @Override
    public boolean solicitarPrestamo(Cuenta c, Prestamo p) throws IOException {
        return subsPrestamo.solicitarPrestamo(c, p);

    }

    @Override
    public boolean cancelarSolicitud(int numRef) throws IOException {
        return subsPrestamo.cancelarSolicitud(numRef);
    }

    @Override
    public List<Prestamo> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos) throws Exception {
        return subsPrestamo.consultarListaPrestamos(c,listaPrestamos);
    }

    @Override
    public Prestamo buscarPrestamo(int numRef) throws Exception {
        return subsPrestamo.buscarPrestamo(numRef);
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) throws IOException { // posible bool
        return subsPrestamo.modificarPrestamo(p);
    }

}
