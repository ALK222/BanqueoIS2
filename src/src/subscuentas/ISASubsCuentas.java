package subscuentas;

import java.util.List;

import dominio.Cuenta;

public interface ISASubsCuentas {

    boolean altaCuenta(Cuenta c);

    boolean bajaCuenta(Cuenta c);

    List<Cuenta> consultarListaCuentas(String titularCuenta, String dni);

    Cuenta buscaCuenta(int numeroCuenta);

    boolean modificarCuenta(Cuenta c);

}
