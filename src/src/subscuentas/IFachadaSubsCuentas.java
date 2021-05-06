package subscuentas;

import java.util.List;

import cuentadao.control.Cuenta;

public interface IFachadaSubsCuentas {

    boolean altaCuenta(Cuenta c);

    boolean bajaCuenta(int numeroCuenta);

    List<Cuenta> consultarListaCuentas(String titular_cuenta, String dni);

    Cuenta buscaCuenta(int numeroCuenta);

    boolean modificarCuenta(Cuenta c);

}
