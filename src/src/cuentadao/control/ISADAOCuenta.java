package cuentasdao.control;

import java.util.List;

public interface ISDAOCuentas {
    boolean altaCuenta(Cuentas c);

    boolean bajaCuenta(int num_cuenta);

    public buscarListaCuentas(String titular_cuenta, String dni) throws Exception;

    List<Cuenta> consultarCuenta(int num_cuenta);

    boolean modificarCuentas(Cuentas c);

    JSONArray getMovimiento(int max_meses)
}
