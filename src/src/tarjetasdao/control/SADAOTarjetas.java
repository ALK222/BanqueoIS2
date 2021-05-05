package tarjetasdao.control;

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
	public List<Tarjeta> consultarListaTarjetas(String titular_cuenta, String dni) {
		return null;
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
	public Tarjeta modificarTarjeta(Tarjeta t) { //Posible bool
		return null;
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
