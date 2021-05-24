package prestamosdao.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;

/**
 * Intermediario entre el SADAO de prestamos y el SA
 */
public class FachadaDAOPrestamos implements IFachadaDAOPrestamos {

    // ATRIBUTOS
    private ISADAOPrestamos daoPrest;

    public FachadaDAOPrestamos() {
        daoPrest = new SADAOPrestamos();
    }

    @Override
    public boolean solicitarPrestamo(Cuenta c, Prestamo p) throws FileNotFoundException, IOException {
        return daoPrest.solicitaPrestamo(c, p);
    }

    @Override
    public boolean cancelarSolicitud(int numRef) throws FileNotFoundException, IOException {
        return daoPrest.cancelarSolicitud(numRef);
    }

    @Override
    public boolean modificarPrestamo(Prestamo p) throws IOException {
        return daoPrest.modificarPrestamo(p);
    }

    @Override
    public boolean existePrestamo(int numRef) throws FileNotFoundException {
        return daoPrest.existePrestamo(numRef);
    }

    @Override
    public List<Prestamo> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos) {
        return daoPrest.consultarListaPrestamos(c, listaPrestamos);
    }

    @Override
    public Prestamo buscarPrestamo(int numRef) throws FileNotFoundException {
        return daoPrest.buscaPrestamo(numRef);
    }

}
