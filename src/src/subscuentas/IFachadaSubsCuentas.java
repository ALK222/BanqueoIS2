package subscuentas;

import java.util.List;

import dominio.Cuenta;

public interface IFachadaSubsCuentas {

    boolean altaCuenta(Cuenta c);

    boolean bajaCuenta(Cuenta c);

    List<Cuenta> consultarListaCuentas(String titular_cuenta, String dni);

    Cuenta buscaCuenta(int numeroCuenta);

    boolean modificarCuenta(Cuenta c);

}
