package cuentadao.control;

import java.util.List;

import org.json.JSONArray;

import usuariosdao.control.Persona;

public class SADAOCuentas implements ISADAOCuenta {
    
    private List<Cuenta> _listaCuentas;
    
    public boolean altaCuenta(Cuenta c){
         return _listaCuentas.contains(c) ? false : _listaCuentas.add(c);
    }

    public boolean bajaCuenta(int num_cuenta){
            
            for(Cuenta c : _listaCuentas){
                if(c.getNumeroCuenta() == num_cuenta){
                    _listaCuentas.remove(c); 
                    return true; 
                }
            }
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

        try{
            if(dni == null){
                throw new Exception(); 
            }





        }catch(Exception e){
            System.err.println("[ERROR] " + e.getMessage() + e.getStackTrace());
        }
    
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
