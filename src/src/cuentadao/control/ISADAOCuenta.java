package cuentadao.control;

import java.util.List;

import org.json.JSONArray;

import dominio.Cuenta;

public interface ISADAOCuenta {
    boolean altaCuenta(Cuenta c);

    boolean bajaCuenta(int num_cuenta);

    List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni);

    Cuenta consultarCuenta(int num_cuenta);

    boolean modificarCuentas(Cuenta c);

    JSONArray getMovimiento(int max_meses);
}
