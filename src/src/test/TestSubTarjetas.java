package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import dominio.Tarjeta;
import dominio.TipoTarjeta;
import netscape.javascript.JSException;
import substarjetas.FachadaSubsTarjetas;
import substarjetas.IFachadaSubsTarjetas;
import tarjetasdao.control.TarjetasJSON;

@RunWith(JUnitPlatform.class)
class TestSubTarjetas {
    private List<Tarjeta> _listaOriginal;
    private IFachadaSubsTarjetas _testTarjeta;

    @BeforeEach
    public void setup() throws FileNotFoundException {

        _testTarjeta = new FachadaSubsTarjetas();
        _listaOriginal = Collections.unmodifiableList(TarjetasJSON.leerListaTarjetas());

    }

    @AfterEach
    public void clear() throws IOException {
        System.out.println("Limpiando Lista");
        TarjetasJSON.guardarListaUsuarios(_listaOriginal);
    }

    @Test
    void testAlta() {
        System.out.println("Test de funciones de alta");
        // ALTA DE TARJETA FUNCIONAL
        Tarjeta t = new Tarjeta("Luisa Carnes Caballero", 1234, true, 0000000000000001, "12/25", TipoTarjeta.DEBITO,
                "12345677A");

        int pruebaAlta1 = _testTarjeta.altaTarjeta(t);
        assertEquals(0, pruebaAlta1, "Alta que deberia ser valida acabada con error");

        // ALTA DE UN USUARIO EXISTENTE
        int pruebaAlta2 = _testTarjeta.altaTarjeta(t);
        assertEquals(1, pruebaAlta2, "Alta que debería no ser valida igualmente mete acla tarjeta");
    }

    @Test
    void testModificacion() {
        System.out.println("Test de las funciones de modificacion");
        Tarjeta t = new Tarjeta("Luisa Carnes Caballero", 1234, true, 0000000000000001, "12/25", TipoTarjeta.DEBITO,
                "12345677A");

        int pruebaAlta1 = _testTarjeta.altaTarjeta(t);
        assertEquals(0, pruebaAlta1, "Alta que deberia ser valida acabada con error");

        t.setPin(0000);

        // MODIFICAR USUARIO FUNCIONAL
        int pruebaMod1 = _testTarjeta.modificarTarjeta(t);
        assertEquals(0, pruebaMod1, "Modificacion que deberia ser valida provoca error");

        Tarjeta t2 = new Tarjeta("Tomas Hernandez Hernandez", 1235, true, 0000000000000003, "12/25", TipoTarjeta.DEBITO,
                "12345677A");

        // MODIFICAR TARJETA CON ERROR
        int pruebaMod2 = _testTarjeta.modificarTarjeta(t2);
        assertEquals(1, pruebaMod2, "Modificacion que no deberia ser valida no acaba con error");

    }

    @Test

    void testBaja() {
        System.out.println("Test de las funciones de baja");
        Tarjeta t = new Tarjeta("Luisa Carnes Caballero", 1234, true, 0000000000000001, "12/25", TipoTarjeta.DEBITO,
                "1234567A");

        int pruebaAlta1 = _testTarjeta.altaTarjeta(t);
        assertEquals(0, pruebaAlta1, "Ata que deberia ser valida acaba con error");

        // BAJA DE USUARIO FUNCIONAL
        int pruebaBaja1 = _testTarjeta.bajaTarjeta(t.getNum_tarjeta());
        assertEquals(0, pruebaBaja1, "Baja que debaria ser valida acabada con error");

        // BAJA DE USUARIO CON ERROR
        int pruebaBaja2 = _testTarjeta.bajaTarjeta(t.getNum_tarjeta());
        assertEquals(1, pruebaBaja2, "Baja que no deberia ser valida no provoca error");
    }

    @Test
    void testFiltrado() {
        System.out.println("Test de las funciones de filtrado");
        // PRUEBA DE FILTRADO DE LISTA
        int filtradoNombre = _testTarjeta.consultarListaTarjetas("01234567A").getSecond();
        assertEquals(0, filtradoNombre, "Filtrado por nombre no funciona correctamente");

        int filtradoNumero = _testTarjeta.consultarListaTarjetas("0").getSecond();
        assertEquals(2, filtradoNumero, "Filtrado por numero no funciona correctamente");

    }

    @Test
    void testBuscar() {
        System.out.println("Test de las funciones de busqueda");

        // BUSCAR TARJETA BIEN
        int t1 = _testTarjeta.buscaTarjeta(1235123).getFirst();

        assertEquals(0, t1, "No se realizo bien la busqueda");

        // BUSCAR TARJETA MAL
        int t2 = _testTarjeta.buscaTarjeta(0).getFirst();
        assertEquals(1, t2, "No se hizo bien el buscarTarjetas fallido");

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
