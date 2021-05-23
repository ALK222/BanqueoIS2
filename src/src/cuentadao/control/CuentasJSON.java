package cuentadao.control;

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

import dominio.Cuenta;

/**
 * Intermediario entre el DAOCuentas y la "base de datos"
 */
public class CuentasJSON {

    /**
     * Lectura del fichero que hace de base de datos
     * 
     * @return Una lista con todas las cuentas guardadas
     * @throws FileNotFoundException Si no consigue encontrar el fichero
     */
    public static List<Cuenta> leerListaCuentas() throws FileNotFoundException {
        File testFile = null;
        InputStream in = null;

        try {
            testFile = new File(System.getProperty("user.dir") + "/src/resources/cuentas.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("./resources/cuentas.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }

        }

        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        try {

            JSONObject jsonFile = new JSONObject(new JSONTokener(in));
            JSONArray ja = jsonFile.getJSONArray("cuentas");

            for (int i = 0; i < ja.length(); i++) {
                JSONArray movimientos = new JSONArray();

                String titularCuenta = ja.getJSONObject(i).getString("Titular");
                String dni = ja.getJSONObject(i).getString("DNI");
                int numeroCuenta = ja.getJSONObject(i).getInt("Numero_Cuenta");
                double saldo = ja.getJSONObject(i).getDouble("Saldo");
                String firmaDigital = ja.getJSONObject(i).getString("Firma_Digital");
                if (ja.getJSONObject(i).has("Movimientos")) {
                    movimientos = ja.getJSONObject(i).getJSONArray("Movimientos");
                }

                listaCuentas.add(new Cuenta(titularCuenta, dni, numeroCuenta, saldo, firmaDigital, movimientos));
            }
        } catch (Exception e) {
            throw e;
        }
        return listaCuentas;
    }

    /**
     * Guardado de la lista actualizada en el fichero
     * 
     * @param listaCuentas Listado de cuentas con las ultimas modificaciones
     * @throws IOException Si no encuentra el archivo o no puede escribir en el
     */
    public static void guardarListaCuentas(List<Cuenta> listaCuentas) throws IOException {
        File testFile = null;
        FileWriter in = null;
        try {
            testFile = new File(System.getProperty("user.dir") + "/src/resources/cuentas.json");
            in = new FileWriter(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("./resources/cuentas.json");
                in = new FileWriter(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }
        }
        JSONArray ja = new JSONArray();
        for (Cuenta c : listaCuentas) {
            JSONObject cJson = new JSONObject();
            cJson.put("Titular", c.getTitularCuenta().getFirst());
            cJson.put("DNI", c.getTitularCuenta().getSecond());
            cJson.put("Numero_Cuenta", c.getNumeroCuenta());
            cJson.put("Saldo", c.getSaldo());
            cJson.put("Firma_Digital", c.getFirmaDigital());
            cJson.put("Movimientos", c.getMovimientos());

            ja.put(cJson);

        }

        in.write("{ \"cuentas\":\n");
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
