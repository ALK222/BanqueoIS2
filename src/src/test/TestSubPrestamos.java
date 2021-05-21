package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import dominio.Cuenta;
import dominio.EstadoPrestamo;
import dominio.Prestamo;
import dominio.Tarjeta;
import prestamosdao.control.PrestamoJSON;
import prestamosdao.control.SADAOPrestamos;
import subsprestamos.FachadaSubsPrestamos;
import subsprestamos.IFachadaSubsPrestamos;

@RunWith(JUnitPlatform.class)
public class TestSubPrestamos {
    private IFachadaSubsPrestamos _testPrestamos;

    private List<Prestamo> _listaOriginal;

    @BeforeEach
    public void setup() throws FileNotFoundException {
        System.out.println("Creando lista y subsistema");
        _testPrestamos = new FachadaSubsPrestamos();

        _listaOriginal = Collections.unmodifiableList(PrestamoJSON.leerListaPrestamos());
    }

    @AfterEach
    public void clear() throws IOException {
        System.out.println("Guardando lista original");

        PrestamoJSON.guardarListaPrestamos(_listaOriginal);
    }

    @Test
    void testSolicitudPrestamo() throws IOException {
        Cuenta cuentaAux = new Cuenta("Alejandro Barrachina", 111111111f, 20000f, "AAAA", new JSONArray());

        for (int i = 0; i < SADAOPrestamos.MAX_PRESTAMOS; ++i) {
            int numRef = 2000000;
            Prestamo p = new Prestamo(numRef, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                    cuentaAux);
            numRef++;
            boolean pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);

            assertEquals(true, pruebaSolicitud, "Solicitud que debería ser valida acabada con error");
        }
        Prestamo p = new Prestamo(100000, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                cuentaAux);
        boolean pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);

        assertEquals(false, pruebaSolicitud, "Solicitud que debería no ser valida acabada bien");

    }

    @Test
    void testCancelacion() throws IOException {
        // Cancelar prestamo solicidato
        Cuenta cuentaAux = new Cuenta("Alejandro Barrachina", 111111111f, 20000f, "AAAA", new ArrayList<Tarjeta>(),
                new ArrayList<Prestamo>(), new JSONArray());

        Prestamo p = new Prestamo(111111, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                cuentaAux);

        boolean pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);
        assertEquals(true, pruebaSolicitud, "Solicitud que debería ser valida acabada con error");

        boolean pruebaCancelacion = _testPrestamos.cancelarSolicitud(p.getNumReferencia());
        assertEquals(true, pruebaCancelacion, "Cancelacion que debería ser valida acabada con error");

        p.setEstadoPrestamo(EstadoPrestamo.ACEPTADO);
        boolean pruebaCancelacion2 = _testPrestamos.cancelarSolicitud(p.getNumReferencia());
        assertEquals(false, pruebaCancelacion2, "Cancelacion que debería no ser valida acabada bien");

        p = null;
        boolean pruebaCancelacion3 = _testPrestamos.cancelarSolicitud(p.getNumReferencia());
        assertEquals(false, pruebaCancelacion3, "Cancelacion que debería no ser valida acabada bien");
    }

}
