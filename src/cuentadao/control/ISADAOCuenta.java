package cuentadao.control;

import java.util.List;

public interface ISADAOCuenta {
    boolean altaCuenta(Cuenta c);

    boolean bajaCuenta(int num_cuenta);

    List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni);

    Cuenta consultarCuenta(int num_cuenta) throws Exception;

    boolean modificarCuentas(Cuenta c);

    JSONArray getMovimiento(int max_meses);
}
