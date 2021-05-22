package prestamosdao.control;

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

import dominio.EstadoPrestamo;
import dominio.Prestamo;

public class PrestamoJSON {
    public static List<Prestamo> leerListaPrestamos() throws FileNotFoundException {
        File testFile = null;
        InputStream in = null;
        try {
            testFile = new File(System.getProperty("user.dir") + "/src/resources/prestamos.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("./resources/prestamos.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }

        }
        List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
        try {

            JSONObject jsonFile = new JSONObject(new JSONTokener(in));
            JSONArray ja = jsonFile.getJSONArray("Prestamos");

            for (int i = 0; i < ja.length(); i++) {
                JSONObject currentJSONObject = ja.getJSONObject(i);
                double cantidad = currentJSONObject.getDouble("Cantidad_Solicitada");
                int numReferencia = currentJSONObject.getInt("Num_Referencia");
                String plazoDevolucion = currentJSONObject.getString("Plazo_Devolucion");
                int aval = currentJSONObject.getInt("Aval");
                String profesion = currentJSONObject.getString("Profesion");
                boolean firmaSeguroDefuncion = currentJSONObject.getBoolean("Firma_Seguro_Defuncion");
                String estado = currentJSONObject.getString("Estado_Prestamo");
                int c = currentJSONObject.getInt("Cuenta_Asociada");
                listaPrestamos.add(new Prestamo(numReferencia, cantidad, plazoDevolucion, aval, profesion,
                        firmaSeguroDefuncion, EstadoPrestamo.parse(estado), c));
            }
        } catch (Exception e) {
            throw e;
        }
        return listaPrestamos;
    }

    public static void guardarListaPrestamos(List<Prestamo> listaPrestamos) throws IOException {
        File testFile = null;
        FileWriter in = null;
        try {
            testFile = new File("src/resources/prestamos.json");
            in = new FileWriter(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("resources/prestamos.json");
                in = new FileWriter(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }
        }
        JSONArray ja = new JSONArray();
        for (Prestamo p : listaPrestamos) {
            JSONObject pJson = new JSONObject();
            pJson.put("Cantidad_Solicitada", p.getCantidadSolicitada());
            pJson.put("Num_Referencia", p.getNumReferencia());
            pJson.put("Plazo_Devolucion", p.getPlazoDevolucion());
            pJson.put("Aval", p.getAval());
            pJson.put("Profesion", p.getProfesion());
            pJson.put("Firma_Seguro_Defuncion", p.isFirmaSeguroDefuncion());
            pJson.put("Estado_Prestamo", p.getEstadoPrestamo().toString());
            pJson.put("Cuenta_Asociada", p.getCuentaAsociada());

            ja.put(pJson);

        }

        in.write("{ \"Prestamos\":\n");
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
