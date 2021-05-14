package dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto Cliente, usuario sin permisos administrativos especiales.
 * 
 * Extiende de la Clase Persona.
 * 
 * @see Persona
 */
public class Cliente extends Persona {

	private String _gestorDni;
	private List<Cuenta> _listaCuentas;

	public Cliente(String dni, String nombre, String domicilio, int tlf, String gestorDni, String contrasena,
			String email) {
		super(dni, nombre, domicilio, tlf, contrasena, email);
		this._gestorDni = gestorDni;
		this._listaCuentas = new ArrayList<>(); // lo mas probable que asi si no con parametro
	}

	public String getGestorDni() {
		return _gestorDni;
	}

	public void setGestorDni(String gestorDni) {
		this._gestorDni = gestorDni;
	}

	public List<Cuenta> getListaCuentas() {
		return _listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this._listaCuentas = listaCuentas;
	}

}
