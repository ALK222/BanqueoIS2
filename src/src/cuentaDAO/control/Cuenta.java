package cuentaDAO.control;

import java.util.List;

import org.json.JSONArray;

import prestamosDAO.control.Prestamo;
import tarjetasDAO.control.Tarjeta;

public class Cuenta {

	private String _titularCuenta;
	private int _numeroCuenta;
	private double _saldo;
	private String _firmaDigital;
	private JSONArray _movimientos;
	private List<Tarjeta> _listaTarjetas;
	private List<Prestamo> _listaPrestamos;

	public Cuenta(String titularCuenta, int numeroCuenta, double saldo, String firmaDigital,
			List<Tarjeta> listaTarjetas, List<Prestamo> listaPrestamos) {
		_titularCuenta = titularCuenta;
		this._numeroCuenta = numeroCuenta;
		this._saldo = saldo;
		this._firmaDigital = firmaDigital;
		this._listaTarjetas = listaTarjetas; // lo mas probable es que el ArrayList se crea aqui y no se pasa como
												// parametro
		this._listaPrestamos = listaPrestamos;
		this._movimientos = new JSONArray();
	}

	public String getTitularCuenta() {
		return _titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this._titularCuenta = titularCuenta;
	}

	public int getNumeroCuenta() {
		return _numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this._numeroCuenta = numeroCuenta;
	}

	public double getSaldo() {
		return _saldo;
	}

	public void setSaldo(double saldo) {
		this._saldo = saldo;
	}

	public String getFirmaDigital() {
		return _firmaDigital;
	}

	public void setFirmaDigital(String firmaDigital) {
		this._firmaDigital = firmaDigital;
	}

	public List<Tarjeta> getListaTarjetas() {
		return _listaTarjetas;
	}

	public void setListaTarjetas(List<Tarjeta> listaTarjetas) {
		this._listaTarjetas = listaTarjetas;
	}

	public List<Prestamo> getListaPrestamos() {
		return _listaPrestamos;
	}

	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this._listaPrestamos = listaPrestamos;
	}

	public JSONArray getMovimientos() {
		return _movimientos;
	}

	public void setMovimientos(JSONArray movimientos) {
		this._movimientos = movimientos;
	}

}
