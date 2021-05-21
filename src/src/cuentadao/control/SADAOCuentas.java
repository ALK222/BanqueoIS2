package cuentadao.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dominio.Cuenta;
import dominio.Persona;

public class SADAOCuentas implements ISADAOCuenta {

    @Override
    public boolean altaCuenta(Cuenta c) {
        return _listaCuentas.contains(c) ? false : _listaCuentas.add(c);
    }

    @Override
    public boolean bajaCuenta(Cuenta c) {

        for (Cuenta cuenta : _listaCuentas) {
            if (cuenta.equals(c)) {
                _listaCuentas.remove(c);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni) {

        List<Cuenta> listaCuentasUsuario = new ArrayList<Cuenta>();
        Persona usuario = null;
        try {
            if (dni == null || titular_cuenta == null) {
                throw new Exception();
            }

            for (Persona p : _listaUsuarios) {
                if (p.getDni().equals(dni) && p.getNombre().equals(titular_cuenta)) { // se puede hacer solo con el dni
                    usuario = p;
                }
            }

            for (Cuenta c : _listaCuentas) {
                if (c.getTitularCuenta().equals(usuario.getNombre())) {
                    listaCuentasUsuario.add(c);
                }
            }

            return listaCuentasUsuario.isEmpty() ? null : listaCuentasUsuario;

        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage() + e.getStackTrace());
        }

        return null;
    }

    @Override
    public Cuenta consultarCuenta(int num_cuenta) {
        for (Cuenta c : _listaCuentas) {
            if (c.getNumeroCuenta() == num_cuenta) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean modificarCuentas(Cuenta c) {
        for (Cuenta cAux : _listaCuentas) {
            if (cAux.getNumeroCuenta() == c.getNumeroCuenta()) {
                cAux = c;
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONArray getMovimiento(int maxMeses, Cuenta c) {
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

    private boolean restaMes(int mes, int maxMeses) {
        int currentDate = Calendar.getInstance().get(Calendar.MONTH) * Calendar.getInstance().get(Calendar.YEAR);
        if (Math.abs(currentDate - mes) < maxMeses) {
            return true;
        }
        return false;
    }
}
