package subsprestamos;

import java.util.List;

import dominio.Cuenta;
import dominio.Prestamo;

public interface IFachadaSubsPrestamos {

    boolean solicitarPrestamo(Cuenta c, Prestamo p) throws IOException;

    boolean cancelarSolicitud(int numRef) throws IOException;

    List<Prestamo> consultarListaPrestamos(Cuenta c) throws Exception;

    Prestamo buscarPrestamo(int numRef) throws Exception;

    boolean modificarPrestamo(Prestamo p) throws IOException;

}
