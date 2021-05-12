package prestamosdao.control;

import java.util.List;

import cuentadao.control.Cuenta;

/**
 * Intermediario entre el SADAO de prestamos y la GUI
 */
public class FachadaDAOPrestamos implements IFachadaDAOPrestamos {

    // ATRIBUTOS
    private ISADAOPrestamos daoPrest;

    @Override
    public boolean solicitarPrestamo(Cuenta c, Prestamo p) {
        return daoPrest.solicitaPrestamo(c, p);
    }

    @Override
    public boolean cancelarSolicitud(int numRef) {
        return daoPrest.cancelarSolicitud(numRef);
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) {
        return daoPrest.modificarPrestamo(p);
    }

    @Override
    public boolean existePrestamo(int numRef) {
        return daoPrest.existePrestamo(numRef);
    }

    @Override
    public List<Prestamo> consultarListaPrestamos(Cuenta c) {
        return daoPrest.consultarListaPrestamos(c);
    }

    @Override
    public Prestamo buscarPrestamo(int numRef) {
        return daoPrest.buscaPrestamo(numRef);
    }

}
