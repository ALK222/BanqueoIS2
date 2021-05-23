package test;

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
import common.misc.Pair;
import dominio.Cliente;
import dominio.Persona;
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;
import usuariosdao.control.UsuariosJSON;

@RunWith(JUnitPlatform.class)
class TestSubUsuarios {
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

		int pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(0, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// ALTA DE UN USUARIO EXISTENTE
		int pruebaAlta2 = _testUsuario.altaUsuario(p);

		assertEquals(1, pruebaAlta2, "Alta que debería no ser valida igualmente mete al usuario");

	}

	@Test
	void testModificacion() throws IOException, UserException {
		System.out.println("Test de las funciones de modificación");
		Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		int pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(0, pruebaAlta1, "Alta que debería ser valida acabada con error");

		p.setTlf(666666666);

		// MODIFICAR USUARIO FUNCIONAL
		int pruebaMod1 = _testUsuario.modificarUsuario(p);
		assertEquals(0, pruebaMod1, "Modificacion que debería ser valida provoca error");

		Persona p2 = new Cliente("04976834A", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		// MODIFICAR USUARIO CON ERROR
		int pruebaMod2 = _testUsuario.modificarUsuario(p2);
		assertEquals(1, pruebaMod2, "Modificacion que no debería ser valida no acaba con error");

	}

	@Test
	void testBaja() throws IOException, UserException {
		System.out.println("Test de las funciones de baja");
		Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		int pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(0, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// BAJA USUARIO FUNCIONAL
		int pruebaBaja1 = _testUsuario.bajaUsuario(p);
		assertEquals(0, pruebaBaja1, "Baja que debería ser valida acabada con error");

		// BAJA USUARIO CON ERROR
		int pruebaBaja2 = _testUsuario.bajaUsuario(p);
		assertEquals(1, pruebaBaja2, "Baja que no debería ser valida no provoca error");

	}

	@Test
	void testFiltrado() {
		System.out.println("Test de las funciones de filtrado");
		// PRUEBA FILTRADO DE LISTA
		Pair<List<Persona>, Integer> filtradoCiudad = _testUsuario.consultarListaUsuarios("Madrid", "c");
		assertEquals(0, filtradoCiudad.getSecond(), "Filtrado por ciudad no funciona correctamente");

		Pair<List<Persona>, Integer> filtradoCodigop = _testUsuario.consultarListaUsuarios("35006", "cp");
		assertEquals(0, filtradoCodigop.getSecond(), "Filtrado por codigo postal no funciona correctamente");

		Pair<List<Persona>, Integer> filtradoCalle = _testUsuario.consultarListaUsuarios("Calle Inventada", "st");
		assertEquals(0, filtradoCalle.getSecond(), "Filtrado por calle no funciona correctamente");

		// PRUEBA DE FILTRADO DE LISTA MAL

		Pair<List<Persona>, Integer> filtradoSaleMal = _testUsuario.consultarListaUsuarios("35006", "aaaa");
		assertEquals(2, filtradoSaleMal.getSecond(), "Filtrado por codigo postal no funciona correctamente");
	}

	@Test
	void testBuscar() {
		System.out.println("Test de las funciones de busqueda");
		// BUSCAR USUARIO BIEN
		int p1 = _testUsuario.buscarUsuario("01234567A").getSecond();
		assertEquals(0, p1, "No se realizó bien la busqueda");

		// BUSCAR USUARIO MAL
		int p2 = _testUsuario.buscarUsuario(null).getSecond();
		assertEquals(2, p2, "No se hizo bien el buscarUsuarios fallido");

	}

	@Test
	void testInicioSesion() throws UserException, IOException {
		System.out.println("Test de las funciones de inicio de sesión");
		// INICIAR SESION BIEN
		Pair<Integer, Boolean> pruebaInicio1 = _testUsuario.iniciarSesion("01234567A", "abc123");
		assertEquals(0, pruebaInicio1.getFirst(), "Un inicio de sesión válido da error");

		// INICIAR SESION MAL

		Pair<Integer, Boolean> pruebaInicio2 = _testUsuario.iniciarSesion("01234567A", "AAAAAAAA");

		assertEquals(1, pruebaInicio2.getFirst(), "Un inicio de sesión no válido no da error");
	}
}
