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
import dominio.EstadoPrestamo;
import dominio.Prestamo;
import dominio.Tarjeta;
import dominio.TipoTarjeta;

public class CuentasJSON {

    public static List<Cuenta> leerListaCuentas() throws FileNotFoundException {
        File testFile = null;
        InputStream in = null;

        try {
            testFile = new File("../resources/cuentas.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("src/resources/cuentas.json");
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
                List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
                List<Tarjeta> listaTarjetas = new ArrayList<Tarjeta>();

                String titularCuenta = ja.getJSONObject(i).getString("Titular");
                int numeroCuenta = ja.getJSONObject(i).getInt("Numero");
                double saldo = ja.getJSONObject(i).getDouble("Saldo");
                String firmaDigital = ja.getJSONObject(i).getString("Firma");
                if (ja.getJSONObject(i).has("Movimientos")) {
                    movimientos = ja.getJSONObject(i).getJSONArray("Movimientos");
                }
                if (ja.getJSONObject(i).has("Prestamos")) {
                    for (Object o : ja.getJSONObject(i).getJSONArray("Prestamos")) {
                        JSONObject jo = (JSONObject) o;
                        listaPrestamos.add(new Prestamo(jo.getInt("Num_Referencia"),
                                jo.getDouble("Cantidad_Solicitada"), jo.getString("Plazo_Devolucion"),
                                jo.getInt("Aval"), jo.getString("Profesion"), jo.getBoolean("Firma_Seguro_Defuncion"),
                                EstadoPrestamo.parse(jo.getString("Estado_Prestamo"))));
                    }
                }
                if (ja.getJSONObject(i).has("Tarjetas")) {
                    for (Object o : ja.getJSONObject(i).getJSONArray("Tarjetas")) {
                        JSONObject jo = (JSONObject) o;
                        listaTarjetas.add(new Tarjeta(jo.getString("Titular"), jo.getInt("PIN"),
                                jo.getBoolean("Estado"), jo.getInt("Num_Tarjeta"), jo.getString("Fecha_CAD"),
                                TipoTarjeta.parse(jo.getString("Tipo"))));
                    }
                }

                listaCuentas.add(new Cuenta(titularCuenta, numeroCuenta, saldo, firmaDigital, listaTarjetas,
                        listaPrestamos, movimientos));
            }
        } catch (Exception e) {
            throw e;
        }
        return listaCuentas;
    }

    public static void guardarListaCuentas(List<Cuenta> listaCuentas) throws IOException {
        File testFile = null;
        FileWriter in = null;
        try {
            testFile = new File("../resources/cuentas.json");
            in = new FileWriter(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("src/resources/cuentas.json");
                in = new FileWriter(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }
        }
        JSONArray ja = new JSONArray();
        for (Cuenta c : listaCuentas) {
            JSONObject cJson = new JSONObject();
            cJson.put("Titular", c.getTitularCuenta());
            cJson.put("Numero", c.getNumeroCuenta());
            cJson.put("Saldo", c.getSaldo());
            cJson.put("Firma", c.getFirmaDigital());
            cJson.put("Movimientos", c.getMovimientos());
            cJson.put("Tarjetas", c.getListaTarjetas()); // la clave puede ser modificada, TODO
            cJson.put("Prestamos", c.getListaPrestamos());

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
