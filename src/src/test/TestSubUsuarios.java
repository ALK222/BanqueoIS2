package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import common.exception.UserException;
import dominio.Cliente;
import dominio.Persona;
import subsusuarios.FachadaSubsUsuarios;
import subsusuarios.IFachadaSubsUsuarios;
import usuariosdao.control.UsuariosJSON;

@RunWith(JUnitPlatform.class)
class TestSubUsuarios {
	private List<Persona> _listaUsuarios;
	private IFachadaSubsUsuarios _testUsuario;

	private List<Persona> _listaOriginal;

	@BeforeEach
	public void setup() throws FileNotFoundException {
		System.out.println("Creando lista y subsistema");
		_testUsuario = new FachadaSubsUsuarios();

		_listaOriginal = Collections.unmodifiableList(UsuariosJSON.leerListaUsuarios());
	}

	@AfterEach
	public void clear() throws IOException {
		System.out.println("Guardando lista original");

		UsuariosJSON.guardarListaUsuarios(_listaOriginal);
	}

	@Test
	void testAlta() throws FileNotFoundException, IOException, UserException {
		System.out.println("Test de las funciones de alta");
		// ALTA DE USUARIO FUNCIONAL
		Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		boolean pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(true, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// ALTA DE UN USUARIO EXISTENTE
		boolean pruebaAlta2 = false;
		try {
			pruebaAlta2 = _testUsuario.altaUsuario(p);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(false, pruebaAlta2, "Alta que debería no ser valida igualmente mete al usuario");

	}

	@Test
	void testModificacion() throws IOException, UserException {
		System.out.println("Test de las funciones de modificación");
		Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		boolean pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(true, pruebaAlta1, "Alta que debería ser valida acabada con error");

		p.setTlf(666666666);

		// MODIFICAR USUARIO FUNCIONAL
		boolean pruebaMod1 = _testUsuario.modificarUsuario(p);
		assertEquals(true, pruebaMod1, "Modificacion que debería ser valida provoca error");

		Persona p2 = new Cliente("04976834A", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		// MODIFICAR USUARIO CON ERROR
		boolean pruebaMod2 = false;
		try {
			_testUsuario.modificarUsuario(p2);
		} catch (UserException e) {
			System.out.print(e.getMessage());
		}
		assertEquals(false, pruebaMod2, "Modificacion que no debería ser valida no acaba con error");

	}

	@Test
	void testBaja() throws IOException, UserException {
		System.out.println("Test de las funciones de baja");
		Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		boolean pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(true, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// BAJA USUARIO FUNCIONAL
		boolean pruebaBaja1 = _testUsuario.bajaUsuario(p);
		assertEquals(true, pruebaBaja1, "Baja que debería ser valida acabada con error");

		// BAJA USUARIO CON ERROR
		boolean pruebaBaja2 = false;
		try {
			_testUsuario.bajaUsuario(p);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(false, pruebaBaja2, "Baja que no debería ser valida no provoca error");

	}

	@Test
	void testFiltrado() {
		System.out.println("Test de las funciones de filtrado");
		// PRUEBA FILTRADO DE LISTA
		try {
			List<Persona> filtradoCiudad = _testUsuario.consultarListaUsuarios("Madrid", "c");
			assertEquals(UsuariosJSON.leerListaUsuarios(), filtradoCiudad,
					"Filtrado por ciudad no funciona correctamente");
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			List<Persona> filtradoCodigop = _testUsuario.consultarListaUsuarios("35006", "cp");
			assertEquals(UsuariosJSON.leerListaUsuarios(), filtradoCodigop,
					"Filtrado por codigo postal no funciona correctamente");
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			List<Persona> filtradoCalle = _testUsuario.consultarListaUsuarios("Calle Inventada", "st");
			assertEquals(UsuariosJSON.leerListaUsuarios(), filtradoCalle,
					"Filtrado por calle no funciona correctamente");
		} catch (Exception e) {
			fail(e.toString());
		}

		// PRUEBA DE FILTRADO DE LISTA MAL

		try {
			List<Persona> filtradoCodigop = null;
			try {
				filtradoCodigop = _testUsuario.consultarListaUsuarios("35006", "aaaa");
			} catch (UserException e) {
				System.out.println(e.getMessage());
			}
			assertEquals(_listaUsuarios, filtradoCodigop, "Filtrado por codigo postal no funciona correctamente");
		} catch (Exception e) {
			assertEquals("Modo no soportado", e.getMessage(), "No se lanza la excepcion correcta");
		}
	}

	@Test
	void testBuscar() {
		System.out.println("Test de las funciones de busqueda");
		// BUSCAR USUARIO BIEN
		Persona p1 = null;
		Persona expectedPersona = new Cliente("01234567A", "Federico Garcia Lorca", "Calle inventada, 2 2;35006;Madrid",
				555555555, "01234568A", "abc123", "correofalso@gmail.com");
		try {
			p1 = _testUsuario.buscarUsuario("01234567A");
		} catch (Exception e) {
			fail("La funcion buscarUsuario falla");
		}
		assertEquals(expectedPersona, p1, "No se realizó bien la busqueda");

		// BUSCAR USUARIO MAL
		Persona p2 = null;
		try {
			p2 = _testUsuario.buscarUsuario(null);
			fail("BuscarUsuario no lanza excepciones bien");
		} catch (Exception e) {
			assertEquals(null, p2, "No se hizo bien el buscarUsuarios fallido");
		}
	}

	@Test
	void testInicioSesion() throws UserException, IOException {
		System.out.println("Test de las funciones de inicio de sesión");
		// INICIAR SESION BIEN
		boolean pruebaInicio1 = _testUsuario.iniciarSesion("01234567A", "abc123");
		assertEquals(true, pruebaInicio1, "Un inicio de sesión válido da error");

		// INICIAR SESION MAL

		boolean pruebaInicio2 = false;
		try {
			_testUsuario.iniciarSesion("01234567A", "AAAAAAAA");
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(false, pruebaInicio2, "Un inicio de sesión no válido no da error");
	}
}
