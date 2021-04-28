package usuarios;

import java.util.ArrayList;
import java.util.List;

import cuenta.Cuenta;

public class Cliente extends Persona {

	private String _gestorDni;
	private List<Cuenta> _listaCuentas;

	public Cliente(String dni, String nombre, String domicilio, int tlf, String gestorDni) {
		super(dni, nombre, domicilio, tlf);
		this._gestorDni = gestorDni;
		this._listaCuentas = new ArrayList<>(); // lo mas probable que asi si no con parametro
	}

	public String getGestor_dni() {
		return _gestorDni;
	}

	public void setGestor_dni(String gestorDni) {
		this._gestorDni = gestorDni;
	}

	public List<Cuenta> getListaCuentas() {
		return _listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this._listaCuentas = listaCuentas;
	}

}
