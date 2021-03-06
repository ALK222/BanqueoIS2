package tarjetasdao.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import dominio.Tarjeta;
import dominio.TipoTarjeta;

/**
 * Intermediario entre el DAOTarjetas y la "base de datos"
 */
public class TarjetasJSON {

    /**
     * Lectura del fichero que hace de base de datos
     * 
     * @return Una lista con todas las cuentas guardadas
     * @throws FileNotFoundException Si no consigue encontrar el fichero
     */
    public static List<Tarjeta> leerListaTarjetas() throws FileNotFoundException {
        File testFile = null;
        InputStream in = null;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/Tarjetas.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/Tarjetas.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                try {
                    testFile = new File("./resources/Tarjetas.json");
                    in = new FileInputStream(testFile);
                } catch (FileNotFoundException ex1) {
                    try {
                        testFile = new File(System.getProperty("user.dir") + "/src/resources/Tarjetas.json");
                        in = new FileInputStream(testFile);
                    } catch (FileNotFoundException ex2) {
                        throw ex2;
                    }
                }
            }
        }
        List<Tarjeta> listaTarjetas = new ArrayList<Tarjeta>();
        try {

            JSONObject jsonFile = new JSONObject(new JSONTokener(in));
            JSONArray ja = jsonFile.getJSONArray("tarjetas");

            for (int i = 0; i < ja.length(); i++) {

                String titular = ja.getJSONObject(i).getString("Titular");
                int pin = ja.getJSONObject(i).getInt("PIN");
                boolean estado = ja.getJSONObject(i).getBoolean("Estado");
                int numTarjeta = ja.getJSONObject(i).getInt("Num_Tarjeta");
                String fechaCad = ja.getJSONObject(i).getString("Fecha_CAD");
                String tipo = ja.getJSONObject(i).getString("Tipo");
                String dni = ja.getJSONObject(i).getString("DNI");

                TipoTarjeta tipoTarjeta = TipoTarjeta.parse(tipo);

                listaTarjetas.add(new Tarjeta(titular, pin, estado, numTarjeta, fechaCad, tipoTarjeta, dni));

            }
        } catch (Exception e) {
            throw e;
        }
        return listaTarjetas;
    }

    /**
     * Guardado de la lista actualizada en el fichero
     * 
     * @param listaCuentas Listado de cuentas con las ultimas modificaciones
     * @throws IOException Si no encuentra el archivo o no puede escribir en el
     */
    public static void guardarListaTarjetas(List<Tarjeta> listaTarjetas) throws IOException {
        File testFile = null;
        FileWriter in = null;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/Tarjetas.json");
            in = new FileWriter(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/Tarjetas.json");
                in = new FileWriter(testFile);
            } catch (FileNotFoundException ex) {
                try {
                    testFile = new File("./resources/Tarjetas.json");
                    in = new FileWriter(testFile);
                } catch (FileNotFoundException ex1) {
                    try {
                        testFile = new File(System.getProperty("user.dir") + "/src/resources/Tarjetas.json");
                        in = new FileWriter(testFile);
                    } catch (FileNotFoundException e2) {
                        throw e2;
                    }
                }
            }
        }
        JSONArray ja = new JSONArray();
        for (Tarjeta t : listaTarjetas) {
            JSONObject tJson = new JSONObject();
            tJson.put("Titular", t.getTitular());
            tJson.put("PIN", t.getPin());
            tJson.put("Estado", t.isEstado());
            tJson.put("Num_Tarjeta", t.getNum_tarjeta());
            tJson.put("Fecha_CAD", t.getFechaCad());
            tJson.put("Tipo", t.getTipo_tarjeta());
            tJson.put("DNI", t.get_dni());

            ja.put(tJson);

        }

        in.write("{ \"tarjetas\":\n");
        in.write(ja.toString());
        in.write("\n}");
        in.close();

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
