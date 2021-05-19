package dominio;

/**
 * Objeto prestamo
 */
public class Prestamo {

	private double _cantidadSolicitada;
	private int _numReferencia;
	private String _plazoDevolucion;
	private int _aval;
	private String _profesion;
	private boolean _firmaSeguroDefuncion;
	private EstadoPrestamo _estadoPrestamo;
	private Cuenta _cuentaAsociada;

	/**
	 * Constructor con argumentos para Prestamo
	 * 
	 * @param numReferencia
	 * @param cantidadSolicitada
	 * @param plazoDevolucion
	 * @param aval
	 * @param profesion
	 * @param firmaSeguroDefuncion
	 * @param estadoPrestamo
	 */
	public Prestamo(int numReferencia, double cantidadSolicitada, String plazoDevolucion, int aval, String profesion,
			boolean firmaSeguroDefuncion, EstadoPrestamo estadoPrestamo, Cuenta cuentaAsociada) {
		_numReferencia = numReferencia;
		_cantidadSolicitada = cantidadSolicitada;
		_plazoDevolucion = plazoDevolucion;
		_aval = aval;
		_profesion = profesion;
		_firmaSeguroDefuncion = firmaSeguroDefuncion;
		_estadoPrestamo = estadoPrestamo;
		_cuentaAsociada = cuentaAsociada;
	}

	public Cuenta getCuentaAsociada() {
		return _cuentaAsociada;
	}

	public int getNumReferencia() {
		return _numReferencia;
	}

	public void setNumReferencia(int numReferencia) {
		_numReferencia = numReferencia;
	}

	public double getCantidadSolicitada() {
		return _cantidadSolicitada;
	}

	public void setCantidadSolicitada(double cantidadSolicitada) {
		_cantidadSolicitada = cantidadSolicitada;
	}

	public String getPlazoDevolucion() {
		return _plazoDevolucion;
	}

	public void setPlazoDevolucion(String plazoDevolucion) {
		_plazoDevolucion = plazoDevolucion;
	}

	public int getAval() {
		return _aval;
	}

	public void setAval(int aval) {
		_aval = aval;
	}

	public String getProfesion() {
		return _profesion;
	}

	public void setProfesion(String profesion) {
		_profesion = profesion;
	}

	public boolean isFirmaSeguroDefuncion() {
		return _firmaSeguroDefuncion;
	}

	public void setFirmaSeguroDefuncion(boolean firmaSeguroDefuncion) {
		_firmaSeguroDefuncion = firmaSeguroDefuncion;
	}

	public EstadoPrestamo getEstadoPrestamo() {
		return _estadoPrestamo;
	}

	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		_estadoPrestamo = estadoPrestamo;
	}

	public boolean esCuentaAsociada(Cuenta c) {
		return _cuentaAsociada.equals(c);
	}

}
