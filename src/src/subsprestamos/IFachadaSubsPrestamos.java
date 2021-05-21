package subsprestamos;

import java.io.IOException;
import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;
import dominio.Prestamo;

public interface IFachadaSubsPrestamos {

    int solicitarPrestamo(Cuenta c, Prestamo p) throws IOException;

    int cancelarSolicitud(int numRef) throws IOException;

    Pair<List<Prestamo>, Integer> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos) throws Exception;

    Pair<Prestamo, Integer> buscarPrestamo(int numRef) throws Exception;

    int modificarPrestamo(Prestamo p) throws IOException;

}
