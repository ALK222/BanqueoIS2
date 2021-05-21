package subscuentas;

import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;

public class FachadaSubsCuentas implements IFachadaSubsCuentas {
    ISASubsCuentas subsCuentas;

    public FachadaSubsCuentas() {
        subsCuentas = new SASubsCuentas();
    }

    @Override
    public int altaCuenta(Cuenta c) {
        return subsCuentas.altaCuenta(c);
    }

    @Override
    public int bajaCuenta(Cuenta c) {
        return subsCuentas.bajaCuenta(c);
    }

    @Override
    public  Pair<List<Cuenta>,Integer> consultarListaCuentas(String titularCuenta, String dni) {
        return subsCuentas.consultarListaCuentas(titularCuenta, dni);
    }

    @Override
    public int buscaCuenta(int numeroCuenta) {
        return subsCuentas.buscaCuenta(numeroCuenta);
    }

    @Override
    public int modificarCuenta(Cuenta c) {
        return subsCuentas.modificarCuenta(c);
    }

}
