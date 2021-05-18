package cuentadao.control;

import java.util.List;

import org.json.JSONArray;

import dominio.Cuenta;

public class FachadaDAOCuentas implements IFachadaDAOCuentas {

    private ISADAOCuenta daoCuen;

    @Override
    public boolean altaCuenta(Cuenta c) {
        return daoCuen.altaCuenta(c);
    }

    @Override
    public boolean bajaCuenta(Cuenta c) {
        return daoCuen.bajaCuenta(c);
    }

    @Override
    public List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni) {
        return daoCuen.buscarListaCuentas(titular_cuenta, dni);
    }

    @Override
    public Cuenta consultarCuenta(int num_cuenta) {
        return daoCuen.consultarCuenta(num_cuenta);
    }

    @Override
    public boolean modificarCuentas(Cuenta c) {
        return daoCuen.modificarCuentas(c);
    }

    @Override
    public JSONArray getMovimiento(int max_meses) {
        // return daoCuen.getMovimiento(max_meses);
        return null;
    }
}
