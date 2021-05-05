package cuentadao.control;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import usuariosdao.control.Persona;



public class SADAOCuentas implements ISADAOCuenta {
    
    private List<Cuenta> _listaCuentas;
    private List<Persona> _listaUsuarios; 
    
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
    
        List<Cuenta> listaCuentasUsuario = new ArrayList<Cuenta>(); 
        Persona usuario = null; 
        try{
            if(dni == null || titular_cuenta == null){
                throw new Exception(); 
            }

            for(Persona p : _listaUsuarios){
                if(p.getDni().equals(dni) && p.getNombre().equals(titular_cuenta)){ // se puede hacer solo con el dni 
                    usuario = p; 
                }
            }

            for(Cuenta c : _listaCuentas){
                if(c.getTitularCuenta().equals(usuario.getNombre())){
                    listaCuentasUsuario.add(c); 
                }
            }

            return listaCuentasUsuario.isEmpty() ? null : listaCuentasUsuario; 

        }catch(Exception e){
            System.err.println("[ERROR] " + e.getMessage() + e.getStackTrace());
        }
    
        return null; 
    }

    public Cuenta consultarCuenta(int num_cuenta) {
        for(Cuenta c : _listaCuentas){
            if(c.getNumeroCuenta() == num_cuenta){
                return c; 
            }
        }
        return null; 
    }

    public boolean modificarCuentas(Cuenta c){ 
        for(Cuenta cAux : _listaCuentas){
            if(cAux.getNumeroCuenta() == c.getNumeroCuenta()){
                cAux = c; 
                return true; 
            }
        }
        return false; 
    }

    public JSONArray getMovimiento(int max_meses){ // TODO : mirar como esta estructurado el JSONArray de movimientos, no tenemos ningun ejemplo 
           /** JSONObject aux = new JSONObject(); 
            JSONArray arrayMovs = new JSONArray(); 
            for(Cuenta c : _listaCuentas){
                
            } */

            return null; 
    }
}
