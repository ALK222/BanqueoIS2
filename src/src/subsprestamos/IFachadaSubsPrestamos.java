package subsprestamos;

import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;

public interface IFachadaSubsPrestamos {

    boolean solicitarPrestamo(Cuenta c, Prestamo p);

    boolean cancelarSolicitud(int num_ref_pres);

    List<Prestamo> consultarListaPrestamos(Cuenta c) throws Exception;

    Prestamo buscarPrestamo(int num_ref_pres) throws Exception;

    boolean modificarPrestamo(Prestamo p);

}
