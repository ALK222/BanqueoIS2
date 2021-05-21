package subsprestamos;

import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;
import dominio.Prestamo;

public interface ISASubsPrestamos {

    int solicitarPrestamo(Cuenta c, Prestamo p);

    int cancelarSolicitud(int numRef);

    Pair<List<Prestamo>, Integer> consultarListaPrestamos(Cuenta c, List<Prestamo> listaPrestamos);

    Pair<Prestamo, Integer> buscarPrestamo(int numRef);

    int modificarPrestamo(Prestamo p);

}
