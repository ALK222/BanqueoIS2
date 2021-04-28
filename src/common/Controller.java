package common;

import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import cuentadao.control.Cuenta;
import prestamosdao.control.Prestamo;
import tarjetasdao.control.Tarjeta;
import tarjetasdao.control.Tipo;
import usuariosdao.control.Cliente;
import usuariosdao.control.Gestor;
import usuariosdao.control.Persona;

public class Controller {

    List<Persona> _listaUsuarios;
    List<Tarjeta> _listaTarjetas;
    List<Prestamo> _listaPrestamos;
    List<Cuenta> _listaCuentas;

    // CARGAR USUARIOS
    /**
     * Carga de usuarios desde archivo
     * 
     * @param in JSON que contiene todos los usuarios
     */
    public void loadUsuarios(InputStream in) throws JSONException {
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
                    _listaUsuarios.add(new Cliente(dni, nombre, domicilio, telefono,
                            ja.getJSONObject(i).getString("DNI_Gestor"), contrasena, email));
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
            for (int i = 0; i < ja.length(); i++) {
                String titular = ja.getJSONObject(i).getString("Titular");
                int pin = ja.getJSONObject(i).getInt("PIN");
                String fechaCad = ja.getJSONObject(i).getString("Fecha_CAD");
                Tipo tipo = Tipo.parse(ja.getJSONObject(i).getString("Tipo"));
                int numTarjeta = ja.getJSONObject(i).getInt("Num_Tarjeta");
                boolean estado = ja.getJSONObject(i).getBoolean("Estado");

                _listaTarjetas.add(new Tarjeta(titular, pin, estado, numTarjeta, fechaCad, tipo));
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
