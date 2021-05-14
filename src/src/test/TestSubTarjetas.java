package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
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

import netscape.javascript.JSException;
import substarjetas.FachadaSubsTarjetas;
import substarjetas.IFachadaSubsTarjetas;
import tarjetasdao.control.Tarjeta;
import tarjetasdao.control.TipoTarjeta;
import tarjetasdao.control.Tipo;

@RunWith(JUnitPlatform.class)
class TestSubTarjetas{
    private List<Tarjeta> _listaTarjetas;
    private IFachadaSubsTarjetas _testTarjeta;

    @BeforeEach
    public void setup() throws FileNotFoundException{
        File testFile = null;
        InputStream in = null;
        try {
            testFile = new File ("../resources/tarjetas.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e){
            try {
                testFile = new File("resources\\tarjetas.json");
                in = new FileInputStream(testFile);
            } catch (FileSystemNotFoundException ex){
                throw ex;
            }
        }
        _listaTarjetas = new ArrayList<Tarjeta>();
        System.out.println("Creando lista y subsistema");
        
        loadTarjetas(in, _listaTarjetas);
        _testTarjeta = new FachadaSubsTarjetas(_listaTarjetas);

    }

    @AfterEach
    public void clear() {
        System.out.println("Limpiando Lista");
        _listaTarjetas.clear();
    }

    @Test
    void testAlta() {
        System.out.println("Test de funciones de alta");
        // ALTA DE TARJETA FUNCIONAL
        Tarjeta t = new Tarjeta("Luisa Carnes Caballero", 1234, true, 0000000000000001, "12/25", TipoTarjeta.DEBITO);

        boolean pruebaAlta1 = _testTarjeta.altaTarjeta(t);
        assertEquals(true, pruebaAlta1, "Alta que deberia ser valida acabada con error");
        
        //ALTA DE UN USUARIO EXISTENTE
        boolean pruebaAlta2 = _testTarjeta.altaTarjeta(t);
        assertEquals(false, pruebaAlta2, "Alta que debería no ser valida igualmente mete acla tarjeta");
    }

    @Test
    void testModificacion() {
        System.out.println("Test de las funciones de modificacion");
        Tarjeta t = new Tarjeta("Luisa Carnes Caballero", 1234, true, 0000000000000001, "12/25", TipoTarjeta.DEBITO);

        boolean pruebaAlta1 = _testTarjeta.altaTarjeta(t);
        assertEquals(true,pruebaAlta1, "Alta que deberia ser valida acabada con error");

        t.setPin(0000);

        //MODIFICAR USUARIO FUNCIONAL
        boolean pruebaMod1 = _testTarjeta.modificarTarjeta(t);
        assertEquals(true, pruebaMod1, "Modificacion que deberia ser valida provoca error");

        Tarjeta t2 = new Tarjeta("Tomas Turbao Hernandez", 1234, true, 0000000000000001, "12/25", TipoTarjeta.DEBITO);

        //MODIFICAR TARJETA CON ERROR
        boolean pruebaMod2 = _testTarjeta.modificarTarjeta(t2);
        assertEquals(false, pruebaMod2, "Modificacion que no deberia ser valida no acaba con error");

    }

    @Test

    void testBaja() {
        System.out.println("Test de las funciones de baja");
        Tarjeta t = new Tarjeta("Luisa Carnes Caballero", 1234, true, 0000000000000001, "12/25", TipoTarjeta.DEBITO);

        boolean pruebaAlta1 = _testTarjeta.altaTarjeta(t);
        assertEquals(true, pruebaAlta1, "Ata que deberia ser valida acaba con error");
        
        //BAJA DE USUARIO FUNCIONAL
        boolean pruebaBaja1 = _testTarjeta.bajaTarjeta(t.getNum_tarjeta());
        assertEquals(true, pruebaBaja1, "Baja que debaria ser valida acabada con error");

        //BAJA DE USUARIO CON ERROR
        boolean pruebaBaja2 = _testTarjeta.bajaTarjeta(t.getNum_tarjeta());
        assertEquals(false, pruebaBaja2, "Baja que no deberia ser valida no provoca error");
    }

    @Test
    void testFiltrado() {
        System.out.println("Test de las funciones de filtrado");
        //PRUEBA DE FILTRADO DE LISTA
        try {
            List<Tarjeta> filtradoNombre = _testTarjeta.consultarListaTarjetas("Luisa Carnes Caballero", "nombre" );
            assertEquals(_listaTarjetas, filtradoNombre, "Filtrado por nombre no funciona correctamente");
        } catch (Exception e) {
            fail(e.toString());
        }
        try {
            List<Tarjeta> filtradoNumero = _testTarjeta.consultarListaTarjetas("0000000000000001", "num");
            assertEquals(_listaTarjetas, filtradoNumero, "Filtrado por numero no funciona correctamente");
        } catch (Exception e) {
            fail(e.toString());
        }

        //PRUEBA DE FILTRADO DE LISTA MAL
        try {
            List<Tarjeta> filtradoCaducidad = _testTarjeta.consultarListaTarjetas("12/25", "brrrrr");
            assertEquals(_listaTarjetas, filtradoCaducidad, "Filtrado por fecha de caducidad no funciona correctamente");
            fail("No deberia dejar hacer la busqueda");
        } catch (Exception e){
            assertEquals("Modo no soportado", e.getMessage(), "No se lanza la excepcion correcta");
        }
    }

    @Test
    void testBuscar() {
        System.out.println("Test de las funciones de busqueda");
        
        //BUSCAR TARJETA BIEN
        Tarjeta t1 = null;
        Tarjeta expectedTarjeta = new Tarjeta("Federico Garcia Lorca", 0000, false, 0000000000001234, "11/21", TipoTarjeta.CREDITO);
        try {
            t1 = _testTarjeta.buscaTarjeta(0000000000001234);
        } catch (Exception e) {
            fail("La funcion buscar falla");
        }
        assertEquals(expectedTarjeta, t1, "No se realizo bien la busqueda");

        //BUSCAR TARJETA MAL
        Tarjeta t2 = null;
        try {
            t2 = _testTarjeta.buscaTarjeta(null);
            fail("BuscarTarjeta no lanza excepciones bien");
        } catch (Exception e) {
            assertEquals(null, p2, "No se hizo bien el buscarTarjetas fallido");
        }
    }

    public static void loadTarjetas(InputStream in, List<Tarjeta> listaTarjetas) throws JSException {
        try {

			JSONObject jsonFile = new JSONObject(new JSONTokener(in));
			JSONArray ja = jsonFile.getJSONArray("usuarios");

			for (int i = 0; i < ja.length(); i++) {

				String titular = ja.getJSONObject(i).getString("Titular");
                int pin = ja.getJSONObject(i).getInt("PIN");
                boolean estado = ja.getJSONObject(i).getboolean("Estado");
                String fechaCad = ja.getJSONObject(i).getString("Fecha_CAD");
                TipoTarjeta tipoTarjeta = ja.getJSONObject(i).getTipoTarjeta("Tipo");
                int numTarjeta = ja.getJSONObject(i).getInt("Num_Tarjeta");
			
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