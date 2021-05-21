package cuentadao.control;

import java.util.List;

import org.json.JSONArray;

import dominio.Cuenta;

public interface IFachadaDAOCuentas {
    boolean altaCuenta(Cuenta c);

    boolean bajaCuenta(Cuenta c);

    List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni);

    Cuenta consultarCuenta(int num_cuenta);

    boolean modificarCuentas(Cuenta c);

    JSONArray getMovimiento(int maxMeses, Cuenta c);
}
