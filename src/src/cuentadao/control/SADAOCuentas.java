package cuentasdao.control;

import java.util.List;

public class SADAOCuentas implemets ISADAOCuentas {
    
    private List<Cuentas> _listaCuentas;
    
    SADAOUsuarios(){
        _listaCuentas = new ArrayList<Persona>();
    }
    
    @Override
    public boolean altaCuenta(Cuentas c){
        return _listaCuentas.contains(p) ? false : _listaCuentas.add(p);
    }

    @Override
    public boolean bajaCuenta(int num_cuenta){
        return _listaPersonas.contains(p) ? true : _listaCuentas.add(p);
    }

    @Override
    public buscarListaCuentas(String titular_cuenta, String dni) throws Exception{
    
        try {
            if (dni == null && titular_cuenta == null) {
                throw new Exception("DNI nulo");
            }
            for (Cuenta cuenta : _listaCuentas) {
                if (**.getDni().equals(dni) && **.getTitular_cuenta().equals(titular_cuenta) {
                    return cuenta;
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return null;
    
    }

    @Override
    List<Cuenta> consultarCuenta(int num_cuenta){
        return null;
    }

    @Override
    public boolean modificarCuentas(Cuentas c){
        for (Cuentas cuenta : _listaCuentas) {
            if (cuneta.compareTo(p) == 0) {
                cuenta = c;
            }
    }

    @Override
    public JSONArray getMovimiento(int max_meses){
    
    }
}
