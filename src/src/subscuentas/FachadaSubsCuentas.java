package subscuentas;

import java.util.List;

import cuentadao.control.Cuenta;

public class FachadaSubsCuentas implements IFachadaSubsCuentas{
    ISASubsCuentas subsCuentas; 

    @Override
    public boolean altaCuenta(Cuenta c) {
        return subsCuentas.altaCuenta(c);
    }
 
    @Override
    public boolean bajaCuenta(int numeroCuenta) {
        return subsCuentas.bajaCuenta(numeroCuenta);
    }

    @Override
    public List<Cuenta> consultarListaCuentas(String titular_cuenta, String dni) {
        return subsCuentas.consultarListaCuentas(titular_cuenta, dni);
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
