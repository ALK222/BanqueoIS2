package subsprestamos;

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
    public boolean solicitarPrestamo(Cuenta c, Prestamo p) {
        return subsPrestamo.solicitarPrestamo(c, p);

    }

    @Override
    public boolean cancelarSolicitud(int num_ref_pres) {
        return subsPrestamo.cancelarSolicitud(num_ref_pres);
    }

    @Override
    public List<Prestamo> consultarListaPrestamos(Cuenta c) throws Exception {
        return subsPrestamo.consultarListaPrestamos(c);
    }

    @Override
    public Prestamo buscarPrestamo(int num_ref_pres) throws Exception {
        return subsPrestamo.buscarPrestamo(num_ref_pres);
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) { // posible bool
        return subsPrestamo.modificarPrestamo(p);
    }

}
