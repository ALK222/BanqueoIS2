package prestamos;

import java.util.Date;

public class Prestamo {

	private double _cantidadSolicitada;
	private Date _plazoDevolucion;
	private int _aval;
	private String _profesion;
	private boolean _firmaSeguroDefuncion;
	private Estado _estadoPrestamo;

	public Prestamo(double cantidadSolicitada, Date plazoDevolucion, int aval, String profesion,
			boolean firmaSeguroDefuncion, Estado estadoPrestamo) {
		this._cantidadSolicitada = cantidadSolicitada;
		this._plazoDevolucion = plazoDevolucion;
		this._aval = aval;
		this._profesion = profesion;
		this._firmaSeguroDefuncion = firmaSeguroDefuncion;
		this._estadoPrestamo = estadoPrestamo;
	}

	public double getCantidad_solicitada() {
		return _cantidadSolicitada;
	}

	public void setCantidad_solicitada(double cantidadSolicitada) {
		this._cantidadSolicitada = cantidadSolicitada;
	}

	public Date getPlazo_devolucion() {
		return _plazoDevolucion;
	}

	public void setPlazo_devolucion(Date plazoDevolucion) {
		this._plazoDevolucion = plazoDevolucion;
	}

	public int getAval() {
		return _aval;
	}

	public void setAval(int aval) {
		this._aval = aval;
	}

	public String getProfesion() {
		return _profesion;
	}

	public void setProfesion(String profesion) {
		this._profesion = profesion;
	}

	public boolean isFirma_seguro_defuncion() {
		return _firmaSeguroDefuncion;
	}

	public void setFirma_seguro_defuncion(boolean firmaSeguroDefuncion) {
		this._firmaSeguroDefuncion = firmaSeguroDefuncion;
	}

	public Estado getEstado_prestamo() {
		return _estadoPrestamo;
	}

	public void setEstado_prestamo(Estado estadoPrestamo) {
		this._estadoPrestamo = estadoPrestamo;
	}

}
