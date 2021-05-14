package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import dominio.Cliente;
import dominio.Gestor;
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

		_listaOriginal = UsuariosJSON.leerListaUsuarios();

	}

	@AfterEach
	public void clear() throws IOException {
		System.out.println("Guardando lista original");

		UsuariosJSON.guardarListaUsuarios(_listaOriginal);
	}

	@Test
	void testAlta() throws FileNotFoundException, IOException {
		System.out.println("Test de las funciones de alta");
		// ALTA DE USUARIO FUNCIONAL
		Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		boolean pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(true, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// ALTA DE UN USUARIO EXISTENTE
		boolean pruebaAlta2 = _testUsuario.altaUsuario(p);
		assertEquals(false, pruebaAlta2, "Alta que debería no ser valida igualmente mete al usuario");

	}

	@Test
	void testModificacion() throws IOException {
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
		boolean pruebaMod2 = _testUsuario.modificarUsuario(p2);
		assertEquals(false, pruebaMod2, "Modificacion que no debería ser valida no acaba con error");

	}

	@Test
	void testBaja() throws IOException {
		System.out.println("Test de las funciones de baja");
		Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
				"abcdabcd", "tremendoemail@gmail.com", "04966834S");

		boolean pruebaAlta1 = _testUsuario.altaUsuario(p);
		assertEquals(true, pruebaAlta1, "Alta que debería ser valida acabada con error");

		// BAJA USUARIO FUNCIONAL
		boolean pruebaBaja1 = _testUsuario.bajaUsuario(p);
		assertEquals(true, pruebaBaja1, "Baja que debería ser valida acabada con error");

		// BAJA USUARIO CON ERROR
		boolean pruebaBaja2 = _testUsuario.bajaUsuario(p);
		assertEquals(false, pruebaBaja2, "Baja que no debería ser valida no provoca error");

	}

	@Test
	void testFiltrado() {
		System.out.println("Test de las funciones de filtrado");
		// PRUEBA FILTRADO DE LISTA
		try {
			List<Persona> filtradoCiudad = _testUsuario.consultarListaUsuarios("Madrid", "c");
			assertEquals(_listaUsuarios, filtradoCiudad, "Filtrado por ciudad no funciona correctamente");
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			List<Persona> filtradoCodigop = _testUsuario.consultarListaUsuarios("35006", "cp");
			assertEquals(_listaUsuarios, filtradoCodigop, "Filtrado por codigo postal no funciona correctamente");
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			List<Persona> filtradoCalle = _testUsuario.consultarListaUsuarios("Calle Inventada", "st");
			assertEquals(_listaUsuarios, filtradoCalle, "Filtrado por calle no funciona correctamente");
		} catch (Exception e) {
			fail(e.toString());
		}

		// PRUEBA DE FILTRADO DE LISTA MAL

		try {
			List<Persona> filtradoCodigop = _testUsuario.consultarListaUsuarios("35006", "aaaa");
			assertEquals(_listaUsuarios, filtradoCodigop, "Filtrado por codigo postal no funciona correctamente");
			fail("No debería dejar hacer la busqueda");
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
	void testInicioSesion() {
		System.out.println("Test de las funciones de inicio de sesión");
		// INICIAR SESION BIEN
		boolean pruebaInicio1 = _testUsuario.iniciarSesion("01234567A", "abc123");
		assertEquals(true, pruebaInicio1, "Un inicio de sesión válido da error");

		// INICIAR SESION MAL

		boolean pruebaInicio2 = _testUsuario.iniciarSesion("01234567A", "AAAAAAAA");
		assertEquals(false, pruebaInicio2, "Un inicio de sesión no válido no da error");
	}

	public static void loadUsuarios(InputStream in, List<Persona> listaUsuarios) throws JSONException {
		try {

			JSONObject jsonFile = new JSONObject(new JSONTokener(in));
			JSONArray ja = jsonFile.getJSONArray("usuarios");

			for (int i = 0; i < ja.length(); i++) {

				String nombre = ja.getJSONObject(i).getString("Nombre");
				String dni = ja.getJSONObject(i).getString("DNI");
				String domicilio = ja.getJSONObject(i).getString("Domicilio");
				String email = ja.getJSONObject(i).getString("Email");
				String contrasena = ja.getJSONObject(i).getString("Contrasena");
				int telefono = ja.getJSONObject(i).getInt("Telefono");

				if (ja.getJSONObject(i).has("DNI_Gestor")) { // Si tiene un dni de un gestor asociado, es un cliente
					listaUsuarios.add(new Cliente(dni, nombre, domicilio, telefono,
							ja.getJSONObject(i).getString("DNI_Gestor"), contrasena, email));
				} else { // Si no, es un gestor
					listaUsuarios.add(new Gestor(dni, nombre, domicilio, telefono, contrasena, email));
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static String readJsonFile(String filePath) {
		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return jsonData;
	}
}
