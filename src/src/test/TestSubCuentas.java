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
import subscuentas.model.FachadaSubsCuentas;
import subscuentas.model.IFachadaSubsCuentas;

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

	@Test
	void testBuscar() {
		System.out.println("Test de las funciones de busqueda");

		// BUSQUEDA BIEN
		int pruebaBusqueda = _testCuenta.buscaCuenta(221111444).getSecond();
		assertEquals(0, pruebaBusqueda, "Busqueda que debería funcionar no funciona");

		// BUSQUEDA MAL
		int pruebaBusqueda1 = _testCuenta.buscaCuenta(321111444).getSecond();
		assertEquals(1, pruebaBusqueda1, "Busqueda que debería funcionar no funciona");

	}

	@Test
	void testConsultarCuentas() {
		System.out.println("Test de las funciones de consulta");

		// BUSQUEDA BIEN
		int pruebaConsulta = _testCuenta.consultarListaCuentas("Federico Garcia Lorca", "01234567A").getSecond();
		assertEquals(0, pruebaConsulta, "Busqueda que debería funcionar no funciona");

		// BUSQUEDA MAL
		int pruebaConsulta1 = _testCuenta.consultarListaCuentas("Federico Garcia Lorca", "555555A").getSecond();
		assertEquals(2, pruebaConsulta1, "Busqueda que debería funcionar no funciona");

	}

}
