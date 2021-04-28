package common;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import usuariosdao.control.Cliente;
import usuariosdao.control.Gestor;
import usuariosdao.control.Persona;

public class Controller {

    List<Persona> _listaUsuarios;

    // CARGAR USUARIOS
    /**
     * Carga de usuarios desde archivo
     * 
     * @param in JSON que contiene todos los usuarios
     */
    public void loadUsuarios(InputStream in) throws JSONException {
        try {
            JSONArray ja = new JSONArray(in);

            for (JSONObject jo : ja) {
                String nombre = jo.getString("Nombre");
                String dni = jo.getString("DNI");
                String domicilio = jo.getString("Domicilio");
                String email = jo.getString("Email");
                String contrasena = jo.getString("Contrasena");
                String telefono = jo.getString("Telefono");

                if (jo.has("DNI_Gestor")) { // Si tiene un dni de un gestor asociado, es un cliente
                    _listaUsuarios.add(new Cliente(dni, nombre, domicilio, telefono, jo.getString("DNI_Gestor"),
                            contrasena, email));
                } else { // Si no, es un gestor
                    _listaUsuarios.add(new Gestor(dni, nombre, domicilio, telefono, contrasena, email));
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void loadTarjetas(InputStream in) throws JSONException {
        try {
            JSONArray ja = new JSONArray(in);

            for (JSONObject jo : ja) {
                String titular = jo.getString("Titular");
                int pin = jo.getInt("PIN");
                Date fechaCad = Date.parse(jo.getString("Fecha_CAD"));
                String tipo = jo.getString("Tipo");
                String titular = jo.getString("Num_Tarjeta");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
