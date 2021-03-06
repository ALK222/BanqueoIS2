package subscuentas.model;

import java.util.List;

import common.misc.Pair;
import dominio.Cuenta;

/**
 * Intermediario entre el sistema de aplicaciones y la GUI
 */
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
    public Pair<List<Cuenta>, Integer> consultarListaCuentas(String titularCuenta, String dni) {
        return subsCuentas.consultarListaCuentas(titularCuenta, dni);
    }

    @Override
    public Pair<Cuenta, Integer> buscaCuenta(int numeroCuenta) {
        return subsCuentas.buscaCuenta(numeroCuenta);
    }

    @Override
    public int modificarCuenta(Cuenta c) {
        return subsCuentas.modificarCuenta(c);
    }

}
