package cuentadao.control;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;

import common.exception.UserException;
import dominio.Cuenta;

/**
 * Intermediario entre el DAOCuentas y el SACuentas
 */
public class FachadaDAOCuentas implements IFachadaDAOCuentas {

    private ISADAOCuenta daoCuen;

    public FachadaDAOCuentas() {
        daoCuen = new SADAOCuentas();
    }

    @Override
    public boolean altaCuenta(Cuenta c) throws IOException {
        return daoCuen.altaCuenta(c);
    }

    @Override
    public boolean bajaCuenta(Cuenta c) throws IOException {
        return daoCuen.bajaCuenta(c);
    }

    @Override
    public List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni) throws IOException, UserException {
        return daoCuen.buscarListaCuentas(titular_cuenta, dni);
    }

    @Override
    public Cuenta consultarCuenta(int num_cuenta) throws IOException {
        return daoCuen.consultarCuenta(num_cuenta);
    }

    @Override
    public boolean modificarCuentas(Cuenta c) throws IOException {
        return daoCuen.modificarCuentas(c);
    }

    @Override
    public JSONArray getMovimiento(int maxMeses, Cuenta c) throws IOException {
        return daoCuen.getMovimiento(maxMeses, c);
    }
}
