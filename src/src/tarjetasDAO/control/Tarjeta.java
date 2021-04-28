package tarjetasdao.control;

import java.util.Date;

public class Tarjeta {

	private String _titular;
	private int _pin;
	private boolean _estado;
	private int _numTarjeta;
	private Date _fechaCad;
	private Tipo _tipoTarjeta;

	public Tarjeta(String titular, int pin, boolean estado, int numTarjeta, Date fechaCad, Tipo tipoTarjeta) {
		this._titular = titular;
		this._pin = pin;
		this._estado = estado;
		this._numTarjeta = numTarjeta;
		this._fechaCad = fechaCad;
		this._tipoTarjeta = tipoTarjeta;
	}

	public String getTitular() {
		return _titular;
	}

	public void setTitular(String titular) {
		this._titular = titular;
	}

	public int getPin() {
		return _pin;
	}

	public void setPin(int pin) {
		this._pin = pin;
	}

	public boolean isEstado() {
		return _estado;
	}

	public void setEstado(boolean estado) {
		this._estado = estado;
	}

	public int getNum_tarjeta() {
		return _numTarjeta;
	}

	public void setNum_tarjeta(int numTarjeta) {
		this._numTarjeta = numTarjeta;
	}

	public Date getFechaCad() {
		return _fechaCad;
	}

	public void setFechaCad(Date fechaCad) {
		this._fechaCad = fechaCad;
	}

	public Tipo getTipo_tarjeta() {
		return _tipoTarjeta;
	}

	public void setTipo_tarjeta(Tipo tipoTarjeta) {
		this._tipoTarjeta = tipoTarjeta;
	}

}
