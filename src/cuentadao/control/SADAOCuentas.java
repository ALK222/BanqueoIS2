package cuentadao.control;

import java.util.List;

import usuariosdao.control.Persona;

public class SADAOCuentas implements ISADAOCuenta {
    
    private List<Cuenta> _listaCuentas;
    
    public boolean altaCuenta(Cuenta c){
            return false; 
    }

    public boolean bajaCuenta(int num_cuenta){
        return false;
    }

    public List<Cuenta> buscarListaCuentas(String titular_cuenta, String dni){
    /**
        try {
            if (dni == null && titular_cuenta == null) {
                throw new Exception("DNI nulo");
            }
            for (Cuenta c  : _listaCuentas) {
                if (c.getTitularCuenta().equals(titular_cuenta)) {
                    return c;
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return null; */
    
        return null; 
    }

    public Cuenta consultarCuenta(int num_cuenta) throws Exception{
            return null; 
    }

    public boolean modificarCuentas(Cuenta c){
        return false; 
    }

    public JSONArray getMovimiento(int max_meses){
            return null;
    }
}
