package common;

import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import dominio.Cuenta;
import dominio.Persona;
import dominio.Prestamo;
import dominio.Tarjeta;
import dominio.TipoTarjeta;

public class Controller {

    List<Persona> _listaUsuarios;
    List<Tarjeta> _listaTarjetas;
    List<Prestamo> _listaPrestamos;
    List<Cuenta> _listaCuentas;

    public void loadTarjetas(InputStream in) throws JSONException {
        try {
            JSONArray ja = new JSONArray(in);
            for (int i = 0; i < ja.length(); i++) {
                String titular = ja.getJSONObject(i).getString("Titular");
                int pin = ja.getJSONObject(i).getInt("PIN");
                String fechaCad = ja.getJSONObject(i).getString("Fecha_CAD");
                TipoTarjeta tipo = TipoTarjeta.parse(ja.getJSONObject(i).getString("Tipo"));
                int numTarjeta = ja.getJSONObject(i).getInt("Num_Tarjeta");
                boolean estado = ja.getJSONObject(i).getBoolean("Estado");

                _listaTarjetas.add(new Tarjeta(titular, pin, estado, numTarjeta, fechaCad, tipo));
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
