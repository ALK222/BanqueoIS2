package subsprestamos;

import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;
import prestamosdao.control.FachadaDAOPrestamos;
import prestamosdao.control.IFachadaDAOPrestamos;

public class SASubsPrestamos implements ISASubsPrestamos {
    IFachadaDAOPrestamos prestamo;

    public SASubsPrestamos() {
        prestamo = new FachadaDAOPrestamos();
    }

    /*
     * public SASubsPrestamos(List<Prestamo> listaPrestamos) { prestamo = new
     * FachadaDAOPrestamos(listaPrestamos); }
     */

    @Override
    public boolean solicitarPrestamo(Cuenta c, Prestamo p) {
        return prestamo.solicitarPrestamo(c, p);

    }

    @Override
    public boolean cancelarSolicitud(int num_ref_pres) {
        return prestamo.cancelarSolicitud(num_ref_pres);
    }

    @Override
    public List<Prestamo> consultarListaPrestamos(Cuenta c) throws Exception {
        return prestamo.consultarListaPrestamos(c);
    }

    @Override
    public Prestamo buscarPrestamo(int num_ref_pres) throws Exception {
        return prestamo.buscarPrestamo(num_ref_pres);
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) { // posible bool
        return prestamo.modificarPrestamo(p);
    }

}
