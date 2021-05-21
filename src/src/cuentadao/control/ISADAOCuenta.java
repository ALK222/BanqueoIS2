package cuentadao.control;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;

import common.exception.UserException;
import dominio.Cuenta;

public interface ISADAOCuenta {
    boolean altaCuenta(Cuenta c) throws IOException;

    boolean bajaCuenta(Cuenta c) throws IOException;

    List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni) throws IOException, UserException;

    Cuenta consultarCuenta(int num_cuenta) throws IOException;

    boolean modificarCuentas(Cuenta c) throws IOException;

    JSONArray getMovimiento(int maxMeses, Cuenta c) throws IOException;
}
