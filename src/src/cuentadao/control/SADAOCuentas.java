package cuentasdao.control;

import java.util.List;

public class SADAOCuentas implemets ISADAOCuentas {
    
    private List<Cuentas> _listaCuentas;
    
    public boolean altaCuenta(Cuentas c){
    
    }

    public boolean bajaCuenta(int num_cuenta){
        return false;
    }

    public List<Cuentas> buscarListaCuentas(String titular_cuenta, String dni){
    
        try {
            if (dni == null && titular_cuenta == null) {
                throw new Exception("DNI nulo");
            }
            for (Persona persona : _listaCuentas) {
                if (**.getDni().equals(dni)) {
                    return cuenta;
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return null;
    
    }

    public Cuentas consultarCuenta(int num_cuenta) throws Exception{
    
    }

    public boolean modificarCuentas(Cuentas c){
    
    }

    public JSONArray getMovimiento(int max_meses){
    
    }
}
