package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import prestamosdao.control.PrestamoJSON;
import prestamosdao.control.SADAOPrestamos;
import subsprestamos.model.FachadaSubsPrestamos;
import subsprestamos.model.IFachadaSubsPrestamos;

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
    void testSolicitudPrestamo() {

        int numRef = 2000000;
        for (int i = 0; i < SADAOPrestamos.MAX_PRESTAMOS; ++i) {
            Cuenta cuentaAux = new Cuenta("Alejandro Barrachina", "111111A", 1111111, 2000, "AAAA", new JSONArray());
            Prestamo p = new Prestamo(numRef, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                    cuentaAux.getNumeroCuenta());
            numRef++;
            int pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);

            assertEquals(0, pruebaSolicitud, "Solicitud que debería ser valida acabada con error");
        }
        Cuenta cuentaAux = new Cuenta("Alejandro Barrachina", "111111A", 1111111, 2000, "AAAA", new JSONArray());
        Prestamo p = new Prestamo(100000, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                cuentaAux.getNumeroCuenta());
        int pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);

        assertEquals(1, pruebaSolicitud, "Solicitud que debería no ser valida acabada bien");

    }

    @Test
    void testCancelacion() {
        // Cancelar prestamo solicidato
        Cuenta cuentaAux = new Cuenta("Alejandro Barrachina", "111111A", 1111111, 2000, "AAAA", new JSONArray());
        Prestamo p = new Prestamo(111111, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                cuentaAux.getNumeroCuenta());

        int pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);
        assertEquals(0, pruebaSolicitud, "Solicitud que debería ser valida acabada con error");

        int pruebaCancelacion = _testPrestamos.cancelarSolicitud(p.getNumReferencia());
        assertEquals(0, pruebaCancelacion, "Cancelacion que debería ser valida acabada con error");

        p.setEstadoPrestamo(EstadoPrestamo.ACEPTADO);
        int pruebaCancelacion2 = _testPrestamos.cancelarSolicitud(p.getNumReferencia());
        assertEquals(1, pruebaCancelacion2, "Cancelacion que debería no ser valida acabada bien");

        int pruebaCancelacion3 = _testPrestamos.cancelarSolicitud(0);
        assertEquals(1, pruebaCancelacion3, "Cancelacion que debería no ser valida acabada bien");
    }

    @Test
    void testAceptar() {
        // Cancelar prestamo solicidato
        Cuenta cuentaAux = new Cuenta("Alejandro Barrachina", "111111A", 1111111, 2000, "AAAA", new JSONArray());
        Prestamo p = new Prestamo(111111, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                cuentaAux.getNumeroCuenta());

        int pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);
        assertEquals(0, pruebaSolicitud, "Solicitud que debería ser valida acabada con error");

        int pruebaCancelacion = _testPrestamos.aceptarSolicitud(p.getNumReferencia());
        assertEquals(0, pruebaCancelacion, "Cancelacion que debería ser valida acabada con error");

        p.setEstadoPrestamo(EstadoPrestamo.DENEGADO);
        int pruebaCancelacion2 = _testPrestamos.aceptarSolicitud(p.getNumReferencia());
        assertEquals(1, pruebaCancelacion2, "Cancelacion que debería no ser valida acabada bien");

        int pruebaCancelacion3 = _testPrestamos.aceptarSolicitud(0);
        assertEquals(1, pruebaCancelacion3, "Cancelacion que debería no ser valida acabada bien");
    }

    @Test
    void testModificacion() {
        Cuenta cuentaAux = new Cuenta("Alejandro Barrachina", "111111A", 1111111, 2000, "AAAA", new JSONArray());
        Prestamo p = new Prestamo(100000, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.SOLICITADO,
                cuentaAux.getNumeroCuenta());
        int pruebaSolicitud = _testPrestamos.solicitarPrestamo(cuentaAux, p);

        assertEquals(0, pruebaSolicitud, "Solicitud que debería no ser valida acabada bien");
        // Prueba modificacion bien
        Prestamo p1 = new Prestamo(100000, 200f, "9/2020", 100, "Informatico", true, EstadoPrestamo.ACEPTADO,
                cuentaAux.getNumeroCuenta());
        int pruebaModificacion = _testPrestamos.modificarPrestamo(p1);

        assertEquals(0, pruebaModificacion, "Solicitud que debería no ser valida acabada bien");

        Prestamo p2 = new Prestamo(200000, 300f, "9/2020", 200, "Informatico", true, EstadoPrestamo.SOLICITADO,
                cuentaAux.getNumeroCuenta());

        int pruebaModificacion1 = _testPrestamos.modificarPrestamo(p2);

        assertEquals(1, pruebaModificacion1, "Solicitud que debería no ser valida acabada bien");
    }

    @Test
    void testBusquedaPrestamo() {
        int numRef = 13245768;

        int pruebaBusqueda = _testPrestamos.buscarPrestamo(numRef).getSecond();
        assertEquals(0, pruebaBusqueda, "Busqueda que debería devolver un prestamo no lo devuelve");

        numRef = 0000000;
        int pruebaBusqueda1 = _testPrestamos.buscarPrestamo(numRef).getSecond();
        assertEquals(1, pruebaBusqueda1, "Busqueda que no debería devolver un prestamo lo intenta devolver");

    }

}
