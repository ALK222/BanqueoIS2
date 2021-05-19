package subscuentas;

import java.util.List;

import dominio.Cuenta;

public class FachadaSubsCuentas implements IFachadaSubsCuentas {
    ISASubsCuentas subsCuentas;

    public FachadaSubsCuentas() {
        subsCuentas = new SASubsCuentas();
    }

    @Override
    public boolean altaCuenta(Cuenta c) {
        return subsCuentas.altaCuenta(c);
    }

    @Override
    public boolean bajaCuenta(Cuenta c) {
        return subsCuentas.bajaCuenta(c);
    }

    @Override
    public List<Cuenta> consultarListaCuentas(String titularCuenta, String dni) {
        return subsCuentas.consultarListaCuentas(titularCuenta, dni);
    }

    @Override
    public Cuenta buscaCuenta(int numeroCuenta) {
        return subsCuentas.buscaCuenta(numeroCuenta);
    }

    @Override
    public boolean modificarCuenta(Cuenta c) {
        return subsCuentas.modificarCuenta(c);
    }

}
