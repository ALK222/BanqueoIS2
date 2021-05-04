package cuentasdao.control;

import java.util.List;

public class FachadaDAOCuentas implemets IFachadaDAOCuentas {

    private ISADAOCuen
    
    @Override
    public boolean altaCuenta(Cuentas c){
        return daoCuen.altaCuenta(c);
    }

    @Override
    public boolean bajaCuenta(int num_cuenta){
        return daoCuen.bajaCuenta(num_cuenta);
    }

    @Override
    public List<Cuentas> buscarListaCuentas(String titular_cuenta, String dni){
        return daoCuen.buscarListaCuentas(titular_cuenta, dni);
    }

    @Override
    public Cuentas consultarCuenta(int num_cuenta) throws Exception{
        return daoCuen.consultarCuenta(num_cuenta);
    }

    @Override
    public boolean modificarCuentas(Cuentas c){
        return daoCuen.modificarCuentas(c);
    }
    
    @Override
    public JSONArray getMovimiento(int max_meses){
        return daoCuen.getMovimiento(max_meses);
    }
}
