package cuentadao.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import common.exception.UserException;
import dominio.Cuenta;
import dominio.Persona;
import usuariosdao.control.UsuariosJSON;

/**
 * Gestiona la informacion saliente sobre las cuentas
 */
public class SADAOCuentas implements ISADAOCuenta {

    @Override
    public boolean altaCuenta(Cuenta c) throws IOException {
        List<Cuenta> listaCuentas = CuentasJSON.leerListaCuentas();
        boolean ok = listaCuentas.contains(c) ? false : listaCuentas.add(c);
        CuentasJSON.guardarListaCuentas(listaCuentas);
        return ok;
    }

    @Override
    public boolean bajaCuenta(Cuenta c) throws IOException {
        List<Cuenta> listaCuentas = CuentasJSON.leerListaCuentas();
        boolean ok = !listaCuentas.contains(c) ? false : listaCuentas.remove(c);
        CuentasJSON.guardarListaCuentas(listaCuentas);
        return ok;
    }

    @Override
    public List<Cuenta> buscarListaCuentas(String titularCuenta, String dni) throws IOException, UserException {
        List<Cuenta> listaCuentas = CuentasJSON.leerListaCuentas();
        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();
        List<Cuenta> listaAux = new ArrayList<Cuenta>();
        Persona usuario = null;
        if (dni == null || titularCuenta == null) {
            throw new UserException("No hay cuentas asociadas a este usuario");
        }

        for (Persona p : listaPersonas) {
            if (p.getDni().equals(dni) && p.getNombre().equals(titularCuenta)) {
                usuario = p;
            }

        }
        if (usuario == null) {
            throw new UserException("Usuario no encontrado");
        }
        for (Cuenta c : listaCuentas) {
            if (c.getTitularCuenta().getFirst().equals(usuario.getNombre())
                    && c.getTitularCuenta().getSecond().equals(usuario.getDni())) {
                listaAux.add(c);
            }
        }

        if (listaAux.isEmpty()) {
            throw new UserException("No hay cuentas asociadas a este usuario");
        }

        return listaAux;

    }

    @Override
    public Cuenta consultarCuenta(int num_cuenta) throws IOException {
        List<Cuenta> listaCuentas = CuentasJSON.leerListaCuentas();
        try {
            for (Cuenta c : listaCuentas) {
                if (c.getNumeroCuenta() == num_cuenta)
                    return c;
            }
        } catch (Exception e) {
            throw e;
        }
        CuentasJSON.guardarListaCuentas(listaCuentas);
        return null;
    }

    @Override
    public boolean modificarCuentas(Cuenta c) throws IOException {
        List<Cuenta> listaCuentas = CuentasJSON.leerListaCuentas();
        boolean conseguido = false;
        for (Cuenta cAux : listaCuentas) {
            if (cAux.getNumeroCuenta() == c.getNumeroCuenta()) {
                cAux = c;
                conseguido = true;
            }
        }

        CuentasJSON.guardarListaCuentas(listaCuentas);
        return conseguido;
    }

    @Override
    public JSONArray getMovimiento(int maxMeses, Cuenta c) throws IOException {
        JSONArray movimientosEnMeses = new JSONArray();
        for (Object object : c.getMovimientos()) {
            JSONObject jo = (JSONObject) object;
            int mesesPasados = Integer.parseInt(jo.getString("fecha").split("-")[1])
                    * Integer.parseInt(jo.getString("fecha").split("-")[2]);

            if (restaMes(mesesPasados, maxMeses)) {
                movimientosEnMeses.put(jo);
            }
        }
        return movimientosEnMeses;
    }

    private boolean restaMes(int mes, int maxMeses) throws IOException {
        int currentDate = Calendar.getInstance().get(Calendar.MONTH) * Calendar.getInstance().get(Calendar.YEAR);
        if (Math.abs(currentDate - mes) < maxMeses) {
            return true;
        }
        return false;
    }
}
