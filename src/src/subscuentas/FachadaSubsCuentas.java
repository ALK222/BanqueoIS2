package subscuentas;

import java.util.List;

import cuentadao.control.Cuenta;

public class FachadaSubsCuentas implements IFachadaSubsCuentas{
    ISASubsCuentas subsCuentas; 



    public boolean altaCuenta(Cuenta c) {
        //return subsTarjetas.altaCuenta(c);
    }
 
    public boolean bajaCuenta(int numeroCuenta) {
        return subsCuentas.bajaCuenta(num_tarjeta);
    }

    public List<Cuenta> consultarListaCuentas(String titular_cuenta, String dni) {
        return subsCuentas.consultarListaCuentas(titular_cuenta, dni);
    }

    public Cuenta buscaCuenta(int numeroCuenta) {
        return subsCuentas.buscacuenta(numeroCuenta);
    }

    public boolean modificarCuenta(Cuenta c) {
        return subsCuentas.modificarCuenta(c);
    }
    
}
