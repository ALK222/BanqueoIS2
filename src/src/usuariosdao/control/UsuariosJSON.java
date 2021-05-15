package usuariosdao.control;

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

import dominio.Cliente;
import dominio.Gestor;
import dominio.Persona;

public class UsuariosJSON {

    public static List<Persona> leerListaUsuarios() throws FileNotFoundException {
        File testFile = null;
        InputStream in = null;
        try {
            testFile = new File("src/resources/usuarios.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("resources/usuarios.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }

        }
        List<Persona> listaUsuarios = new ArrayList<Persona>();
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
        return listaUsuarios;
    }

    public static void guardarListaUsuarios(List<Persona> listaUsuarios) throws IOException {
        File testFile = null;
        FileWriter in = null;
        try {
            testFile = new File("src/resources/usuarios.json");
            in = new FileWriter(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File("resources/usuarios.json");
                in = new FileWriter(testFile);
            } catch (FileNotFoundException ex) {
                throw ex;
            }
        }
        JSONArray ja = new JSONArray();
        for (Persona p : listaUsuarios) {
            JSONObject pJson = new JSONObject();
            pJson.put("Nombre", p.getNombre());
            pJson.put("DNI", p.getDni());
            pJson.put("Domicilio", p.getDomicilio());
            pJson.put("Email", p.getEmail());
            pJson.put("Contrasena", p.getContrasena());
            pJson.put("Telefono", p.getTlf());

            if (p.getClass() == Cliente.class) {
                pJson.put("DNI_Gestor", ((Cliente) p).getGestorDni());

            }
            ja.put(pJson);

        }

        in.write("{ \"usuarios\":\n");
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
