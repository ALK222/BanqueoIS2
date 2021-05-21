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

import cuentadao.control.CuentasJSON;
import dominio.Cuenta;
import subscuentas.FachadaSubsCuentas;
import subscuentas.IFachadaSubsCuentas;

@RunWith(JUnitPlatform.class)
class TestSubCuentas {
	private IFachadaSubsCuentas _testCuenta;
	private List<Cuenta> _listaOriginal;

	@BeforeEach
	public void setup() throws FileNotFoundException {
		System.out.println("Creando lista y subsistema");
		_testCuenta = new FachadaSubsCuentas();
		_listaOriginal = Collections.unmodifiableList(CuentasJSON.leerListaCuentas());
	}

	@AfterEach
	public void clear() throws IOException {
		System.out.println("Limpiando lista");
		CuentasJSON.guardarListaCuentas(_listaOriginal);
	}

	@Test
	void testAlta() {
		System.out.println("Test de las funciones de alta");
		int numeroCuenta = 111222333;
		// ALTA DE CUENTA FUNCIONAL
		Cuenta c = new Cuenta("Jesus Abajo Magro", "12345678B", numeroCuenta, 2000, "AA", new JSONArray());

		int pruebaAlta1 = _testCuenta.altaCuenta(c);
		assertEquals(0, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// ALTA DE UN CUENTA EXISTENTE
		int pruebaAlta2 = _testCuenta.altaCuenta(c);
		assertEquals(1, pruebaAlta2, "Alta que debería no ser valida igualmente mete al usuario");
	}

	@Test
	void testModificacion() {
		System.out.println("Test de las funciones de modificación");
		Cuenta c = new Cuenta("Jesus Abajo Magro", "12345678B", 111222333, 2000, "AA", new JSONArray());

		int pruebaAlta1 = _testCuenta.altaCuenta(c);
		assertEquals(0, pruebaAlta1, "Alta que debería ser valida acabada con error");

		c.setSaldo(120);

		// MODIFICAR USUARIO FUNCIONAL
		int pruebaMod1 = _testCuenta.modificarCuenta(c);
		assertEquals(0, pruebaMod1, "Modificacion que debería ser valida provoca error");

		Cuenta c2 = new Cuenta("Alejandro Diaz Blazquez", "12345678C", 111224444, 4000, "AA", new JSONArray());

		// MODIFICAR USUARIO CON ERROR
		int pruebaMod2 = _testCuenta.modificarCuenta(c2);
		assertEquals(1, pruebaMod2, "Modificacion que no debería ser valida no acaba con error");

	}

	@Test
	void testBaja() {
		System.out.println("Test de las funciones de baja");
		Cuenta c2 = new Cuenta("Jesus Abajo Magro", "12345678B", 111222333, 2000, "AA", new JSONArray());

		int pruebaAlta1 = _testCuenta.altaCuenta(c2);
		assertEquals(0, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// BAJA USUARIO FUNCIONAL
		int pruebaBaja1 = _testCuenta.bajaCuenta(c2);
		assertEquals(0, pruebaBaja1, "Baja que debería ser valida acabada con error");

		// BAJA USUARIO CON ERROR
		int pruebaBaja2 = _testCuenta.bajaCuenta(c2);
		assertEquals(1, pruebaBaja2, "Baja que no debería ser valida no provoca error");

	}

	// @Test
	// void testBuscar() {
	// System.out.println("Test de las funciones de busqueda");
	// // BUSCAR USUARIO BIEN
	// Persona p1 = null;
	// Persona expectedPersona = new Cliente("01234567A", "Federico Garcia Lorca",
	// "Calle inventada, 2 2;35006;Madrid",
	// 555555555, "01234568A", "abc123", "correofalso@gmail.com");
	// try {
	// p1 = _testUsuario.buscarUsuario("01234567A");
	// } catch (Exception e) {
	// fail("La funcion buscarUsuario falla");
	// }
	// assertEquals(expectedPersona, p1, "No se realizó bien la busqueda");

	// // BUSCAR USUARIO MAL
	// Persona p2 = null;
	// try {
	// p2 = _testUsuario.buscarUsuario(null);
	// fail("BuscarUsuario no lanza excepciones bien");
	// } catch (Exception e) {
	// assertEquals(null, p2, "No se hizo bien el buscarUsuarios fallido");
	// }
	// }

	// @Test
	// void testInicioSesion() {
	// System.out.println("Test de las funciones de inicio de sesión");
	// // INICIAR SESION BIEN
	// boolean pruebaInicio1 = _testUsuario.iniciarSesion("01234567A", "abc123");
	// assertEquals(true, pruebaInicio1, "Un inicio de sesión válido da error");

	// // INICIAR SESION MAL

	// boolean pruebaInicio2 = _testUsuario.iniciarSesion("01234567A", "AAAAAAAA");
	// assertEquals(false, pruebaInicio2, "Un inicio de sesión no válido no da
	// error");
	// }

}
