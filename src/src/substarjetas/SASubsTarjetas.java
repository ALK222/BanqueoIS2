package substarjetas;

import java.util.List;

import tarjetasdao.control.IFachadaDAOTarjetas;
import tarjetasdao.control.Tarjeta;
import usuariosdao.control.Persona;

public class SASubsTarjetas implements ISASubsTarjetas{
    List<Tarjeta> _listaTarjetas; 
    List<Persona> _listaPersona; 
    IFachadaDAOTarjetas tarjeta; 


    /**
     * Da de alta una tarjeta, creando una y añadiendola a la lista
     * 
     * @param t tarjeta que se va a agregar
     * 
     */
    @Override
    public boolean altaTarjeta(Tarjeta t) { // TODO : hay que cambiar el diagrama para que devuelva boolean 
       /** if(t != null){
            return _listaTarjetas.contains(t) ? false : _listaTarjetas.add(t) ; // excepcion
        }
        return false;  */
        return tarjeta.altaTarjeta(t); 
    }

    /**
     * Da de baja una tarjeta
     * 
     * @param num_tarjeta se utiliza para buscar la tarjeta que se tiene que dar de baja
     * 
     */
    @Override
    public boolean bajaTarjeta(int num_tarjeta) {
     // TODO : posible tarjeta en vez de num tarjeta sino

        /**for(Tarjeta t : _listaTarjetas){
            if(t.getNum_tarjeta() == num_tarjeta){
                _listaTarjetas.remove(t); 
                return true; 
            }
        }
        return false;  */

        return tarjeta.bajaTarejeta(num_tarjeta); 
    }


    /**
     * Consulta la lista de tarjetas
     * 
     * 
     * @param titular_cuenta se utiliza para consultar las tarjetas de un titular
     * @param dni clave primaria, se utiliza para consultar las tarjetas de un titular 
     * 
     */
    @Override
    public List<Tarjeta> consultarListaTarjetas(String titular_cuenta, String dni) { // solo DNI 
        // TODO : mirar como el otro consulta   
        return tarjeta.consultarListaTarjetas(titular_cuenta, dni); 
    }

    /**
     * Busca una tarjeta dentro de la lista
     * 
     * @param num_tarjeta numero de la tarjeta, que se utiliza como identificador en la busqueda 
     * 
     */
    @Override
    public Tarjeta buscaTarjeta(int num_tarjeta) {
      /**  for(Tarjeta t : _listaTarjetas){
            if(t.getNum_tarjeta() == num_tarjeta){
                return t; 
            }
        }
        return null; */
        return tarjeta.buscarTarjeta(num_tarjeta); 
    }

    /**
     * Modifica los datos de una tarjeta 
     * 
     * @param t la tarjeta 
     * @throws exception lanza una excepcion si la tarjeta es null
     * 
     */
    @Override
    public boolean modificarTarjeta(Tarjeta t) {
      /**  Tarjeta aux = null; 
        try {
            if(t == null){
                throw new Exception(); 
            } else {
                for(Tarjeta tAux : _listaTarjetas){
                    if(tAux.getNum_tarjeta() == t.getNum_tarjeta()){
                        aux = t; 
                        _listaTarjetas.add(t); 
                    }
                }
                if(aux != null) {
                    _listaTarjetas.remove(aux); 
                    return true; 
                }
               
            }

        } catch (Exception e) {
           System.err.println("[ERROR] :" + e.getMessage() + e.getStackTrace());
        }
        return false;  */

        return  tarjeta.modificarTarjeta(t) == null ? null : true; 
    }
    
}
