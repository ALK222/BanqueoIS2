package subsprestamos;

import java.util.List;

import prestamosdao.control.Prestamo;
import cuentadao.control.Cuenta;

public interface ISASubsPrestamos {
    
    boolean solicitarPrestamo(Cuenta c, Prestamo p);

    boolean cancelarSolicitud(int num_ref_pres);

    List<Prestamo> consultarListaPrestamos(Cuenta c) throws Exception;

    Prestamo buscarPrestamo(int num_ref_pres) throws Exception;

    boolean modificarPrestamo(Prestamo p);

}
