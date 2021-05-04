package cuentasdao.control;

import java.util.List;

public interface IFachadaDAOCuentas {
    boolean altaCuenta(Cuentas c);

    boolean bajaCuenta(int num_cuenta);

    List<Cuentas> buscarListaCuentas(String titular_cuenta, String dni);

    Cuentas consultarCuenta(int num_cuenta) throws Exception;

    boolean modificarCuentas(Cuentas c);

    JSONArray getMovimiento(int max_meses)
}
