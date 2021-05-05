package tarjetasdao.control;

import java.util.ArrayList;
import java.util.List;


public class SADAOTarjetas implements ISADAOTarjetas{
	
	private List<Tarjeta> _listaTarjetas;

	@Override
	public boolean altaTarjeta(Tarjeta t) { //Se ha cambiado de Tarjeta a boolean
		return _listaTarjetas.contains(t) ? false : _listaTarjetas.add(t);
	}

	@Override
	public boolean bajaTarejeta(int num_tarjeta) {
		for(Tarjeta t: _listaTarjetas){
            if(t.getNum_tarjeta() == num_tarjeta){
            	_listaTarjetas.remove(t);
                return true;
            }
        }
        return false;
	}

	@Override
	public List<Tarjeta> consultarListaTarjetas(String dni) throws Exception{
		 List<Tarjeta> listaAux = new ArrayList<Tarjeta>();
		 for (Tarjeta t : _listaTarjetas) {
             if (t.getTitular().equals(dni)) {
                 listaAux.add(t);
             }
         }
		 if(listaAux.isEmpty()) {
			 throw new Exception("No hay tarjetas asociadas a este usuario");
		 }
		 return listaAux;
	}

	@Override
	public Tarjeta buscarTarjeta(int num_tarjeta) {
		for (Tarjeta t : _listaTarjetas){
            if (t.getNum_tarjeta() == num_tarjeta){
                return t;
            }
        }
		return null;
	}

	@Override
	public boolean modificarTarjeta(Tarjeta t) { //Posible bool
		 boolean conseguido = false;
	        for (Tarjeta tarjeta : _listaTarjetas) {
	            if (tarjeta.compareTo(t) == 0) {
	                tarjeta = t;
	                conseguido = true;
	            }
	        }
	        return conseguido;
	}

	@Override
	public boolean existeTarjeta(int num_tarjeta) {
		for (Tarjeta t : _listaTarjetas){
            if (t.getNum_tarjeta() == num_tarjeta){
                return true;
            }
        }
		return false;
	}

}
