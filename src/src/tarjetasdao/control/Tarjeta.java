package tarjetasdao.control;

public class Tarjeta {

	private String _titular;
	private int _pin;
	private boolean _estado;
	private int _numTarjeta;
	private String _fechaCad;
	private TipoTarjeta _tipoTarjeta;
	private String _dni; 

	public Tarjeta(String titular, int pin, boolean estado, int numTarjeta, String fechaCad, TipoTarjeta tipoTarjeta, String dni) {
		this._titular = titular;
		this._pin = pin;
		this._estado = estado;
		this._numTarjeta = numTarjeta;
		this._fechaCad = fechaCad;
		this._tipoTarjeta = tipoTarjeta;
		this._dni = dni; 
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

	public String getFechaCad() {
		return _fechaCad;
	}

	public void setFechaCad(String fechaCad) {
		this._fechaCad = fechaCad;
	}

	public TipoTarjeta getTipo_tarjeta() {
		return _tipoTarjeta;
	}

	public String get_dni() {
		return _dni;
	}

	public void set_dni(String _dni) {
		this._dni = _dni;
	}

	public void setTipo_tarjeta(TipoTarjeta tipoTarjeta) {
		this._tipoTarjeta = tipoTarjeta;
	}
	
	public int compareTo(Tarjeta t) {
		if (_numTarjeta == t._numTarjeta) {
			return 0;
		}
		return -1;
	}

}
