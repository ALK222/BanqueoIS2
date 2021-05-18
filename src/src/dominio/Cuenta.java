package dominio;

import java.util.List;
import java.util.Objects;

import org.json.JSONArray;

public class Cuenta {

	private String _titularCuenta;
	private double _numeroCuenta;
	private double _saldo;
	private String _firmaDigital;
	private JSONArray _movimientos;
	private List<Tarjeta> _listaTarjetas;
	private List<Prestamo> _listaPrestamos;

	public Cuenta(String titularCuenta, double numeroCuenta, double saldo, String firmaDigital,
			List<Tarjeta> listaTarjetas, List<Prestamo> listaPrestamos, JSONArray movimientos) {
		_titularCuenta = titularCuenta;
		_numeroCuenta = numeroCuenta;
		_saldo = saldo;
		_firmaDigital = firmaDigital;
		_listaTarjetas = listaTarjetas;
		_listaPrestamos = listaPrestamos;
		_movimientos = movimientos;
	}

	public String getTitularCuenta() {
		return _titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this._titularCuenta = titularCuenta;
	}

	public double getNumeroCuenta() {
		return _numeroCuenta;
	}

	public void setNumeroCuenta(double numeroCuenta) {
		this._numeroCuenta = numeroCuenta;
	}

	public double getSaldo() {
		return _saldo;
	}

	public void setSaldo(double saldo) { // aumenta el saldo actual con el double introducido o lo resta en caso de ser
											// negativo
		this._saldo = _saldo + saldo;
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

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Cuenta c = (Cuenta) o;
		return Objects.equals(_numeroCuenta, c.getNumeroCuenta()) && Objects.equals(_firmaDigital, c.getFirmaDigital())
				&& Objects.equals(_listaPrestamos, c.getFirmaDigital())
				&& Objects.equals(_listaTarjetas, c.getListaTarjetas())
				&& Objects.equals(_movimientos, c.getMovimientos()) && Objects.equals(_saldo, c.getSaldo())
				&& Objects.equals(_titularCuenta, c.getTitularCuenta());

	}

}
