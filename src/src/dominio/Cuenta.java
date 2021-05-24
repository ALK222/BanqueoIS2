package dominio;

import java.util.Objects;

import org.json.JSONArray;

import common.misc.Pair;

/**
 * Objeto cuenta, te dice el dinero y el titular de una cuenta
 */
public class Cuenta {

	private Pair<String, String> _titularCuenta;
	private int _numeroCuenta;
	private double _saldo;
	private String _firmaDigital;
	private JSONArray _movimientos;

	public Cuenta(String titularCuenta, String dni, int numeroCuenta, double saldo, String firmaDigital,
			JSONArray movimientos) {
		_titularCuenta = new Pair<String, String>(titularCuenta, dni);
		_numeroCuenta = numeroCuenta;
		_saldo = saldo;
		_firmaDigital = firmaDigital;
		_movimientos = movimientos;
	}

	public Pair<String, String> getTitularCuenta() {
		return _titularCuenta;
	}

	public void setTitularCuenta(Pair<String, String> titularCuenta) {
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

	public JSONArray getMovimientos() {
		return _movimientos;
	}

	public void setMovimientos(JSONArray movimientos) {
		_movimientos = movimientos;
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
				&& Objects.equals(_saldo, c.getSaldo()) && Objects.equals(_titularCuenta, c.getTitularCuenta());

	}

}
