
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import subsusuarios.FachadaSubsUsuarios;
import subsusuarios.IFachadaSubsUsuarios;
import usuariosdao.control.Cliente;
import usuariosdao.control.Gestor;
import usuariosdao.control.Persona;

/**
 * Test para el subsistema de usuarios
 */
public class Main {

    public static void main(String[] args) throws IOException {
        List<Persona> listaUsuarios = new ArrayList<Persona>();
        String testString = "";
        try {
            File testFile = new File("../resources/usuarios.json");
            InputStream in = new FileInputStream(testFile);
            loadUsuarios(in, listaUsuarios);
        } catch (FileNotFoundException e) {
            testString += e.toString() + '\n';
        }

        IFachadaSubsUsuarios testUsuario = new FachadaSubsUsuarios();

        // ALTA DE USUARIO FUNCIONAL
        Persona p = new Cliente("04976834S", "Luisa Carnes Caballero", "Calle inventada, 2 4;35006;Madrid", 555555555,
                "abcdabcd", "tremendoemail@gmail.com", "04966834S");
        if (testUsuario.altaUsuario(p)) {
            testString += "Alta acabada con exito\n";
        } else {
            testString += "Alta acabada con error\n";
        }

        // MODIFICAR USUARIO FUNCIONAL
        if (testUsuario.modificarUsuario(p)) {
            testString += "Modificacion exitosa\n";
        } else {
            testString += "Modificacion no finalizada\n";
        }

        // ALTA DE UN USUARIO EXISTENTE
        if (testUsuario.altaUsuario(p)) {
            testString += "Alta acabada con exito\n";
        } else {
            testString += "Alta acabada con error\n";
        }

        // BAJA USUARIO FUNCIONAL
        if (testUsuario.bajaUsuario(p)) {
            testString += "Baja acabada con exito\n";
        } else {
            testString += "Baja acabada con error\n";
        }

        // MODIFICAR USUARIO CON ERROR
        if (testUsuario.modificarUsuario(p)) {
            testString += "Modificacion exitosa\n";
        } else {
            testString += "Modificacion no finalizada\n";
        }

        // BAJA USUARIO CON ERROR
        if (testUsuario.bajaUsuario(p)) {
            testString += "Baja acabada con exito\n";
        } else {
            testString += "Baja acabada con error\n";
        }

        // PRUEBA FILTRADO DE LISTA

        try {
            List<Persona> filtradoCiudad = testUsuario.consultarListaUsuarios("Madrid", "c");
            for (Persona persona : filtradoCiudad) {
                testString += persona.toString();
            }
        } catch (Exception e) {
            testString += e.toString();
        }

        try {
            List<Persona> filtradoCodigop = testUsuario.consultarListaUsuarios("35006", "cp");
            for (Persona persona : filtradoCodigop) {
                testString += persona.toString();
            }
        } catch (Exception e) {
            testString += e.toString();
        }

        try {
            List<Persona> filtradoCalle = testUsuario.consultarListaUsuarios("Calle Inventada", "st");
            for (Persona persona : filtradoCalle) {
                testString += persona.toString() + '\n';
            }
        } catch (Exception e) {
            testString += e.toString();
        }

        // PRUEBA DE FILTRADO DE LISTA MAL

        try {
            List<Persona> filtradoCodigop = testUsuario.consultarListaUsuarios("35006", "aaaa");
            for (Persona persona : filtradoCodigop) {
                testString += persona.toString() + '\n';
                ;
            }
        } catch (Exception e) {
            testString += e.toString();
        }

        // BUSCAR USUARIO BIEN
        Persona p1 = null;
        try {
            p1 = testUsuario.buscarUsuario("01234567A");
        } catch (Exception e) {
            testString += e.toString() + '\n';
        }
        if (p1 != null) {
            testString += p1.toString() + '\n';
        } else {
            testString += "Fallo al buscar usuario";
        }

        // BUSCAR USUARIO MAL
        Persona p2 = null;
        try {
            p2 = testUsuario.buscarUsuario(null);
        } catch (Exception e) {
            testString += e.toString() + '\n';
        }
        if (p2 != null) {
            testString += p2.toString() + '\n';
        } else {
            testString += "Fallo al buscar usuario";
        }

        // INICIAR SESION BIEN

        if (testUsuario.iniciarSesion("01234567A", "abc123")) {
            testString += "Sesion iniciada" + '\n';
        } else {
            testString += "Error al iniciar sesion\n";
        }

        // INICIAR SESION MAL

        if (testUsuario.iniciarSesion("01234567A", "AAAAAAAAAAAA")) {
            testString += "Sesion iniciada" + '\n';
        } else {
            testString += "Error al iniciar sesion\n";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("./final_output.txt"));
        writer.write(testString);

        writer.close();

    }

    public static void loadUsuarios(InputStream in, List<Persona> listaUsuarios) throws JSONException {
        try {
            JSONArray ja = new JSONArray(in);

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

}
