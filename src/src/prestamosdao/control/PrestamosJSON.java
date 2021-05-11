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

public class PrestamosJSON {

    public static List<Prestamo> leerListaPrestamos() throws FileNotFoundException {
        File testFile = null;
        InputStream in = null;
        try {
            testFile = new File("../resources/prestamos.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("src/resources/prestamos.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }

        }
        List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
        try {

            JSONObject jsonFile = new JSONObject(new JSONTokener(in));
            JSONArray ja = jsonFile.getJSONArray("cuentas");

            for (int i = 0; i < ja.length(); i++) {

                double cantidad = ja.getJSONObject(i).getDouble("Cantidad");
                int numReferencia = ja.getJSONObject(i).getInt("Numero referencia");
                String plazo = ja.getJSONObject(i).getString("Plazo");
                int aval = ja.getJSONObject(i).getInt("Aval");
                String profesion = ja.getJSONObject(i).getString("Profesion");
                boolean firma = ja.getJSONObject(i).getBoolean("Firma");
                String estAux = ja.getJSONObject(i).getString("Estado");
                EstadoPrestamo estado = EstadoPrestamo.parse(estAux); 
                  
                    /**TODO faltaria la cuenta asociada  */
                listaPrestamos.add(new Prestamo(numReferencia,cantidad,plazo,aval,profesion,firma,estado)); 
               
            }
        } catch (Exception e) {
            throw e;
        }
        return listaPrestamos;
    }
    


    public static void guardarListaUsuarios(List<Prestamo> listaPrestamos) throws IOException {
        File testFile = null;
        FileWriter in = null;
        try {
            testFile = new File("../resources/prestamos.json");
            in = new FileWriter(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("src/resources/prestamos.json");
                in = new FileWriter(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }
        }
        JSONArray ja = new JSONArray();
        for (Prestamo p : listaPrestamos) {
            JSONObject pJson = new JSONObject();
            pJson.put("Cantidad", p.getCantidadSolicitada());
            pJson.put("Numero referencia", p.getNumReferencia());
            pJson.put("Plazo", p.getPlazoDevolucion());
            pJson.put("Aval", p.getAval());
            pJson.put("Profesion", p.getProfesion());
            pJson.put("Firma", p.isFirma_seguro_defuncion());
            pJson.put("Estado", p.getEstado_prestamo());
           // la cuenta asociada no se pone ? TODO 

            ja.put(pJson);

        }

        in.write("{ \"prestamos\":\n");
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
