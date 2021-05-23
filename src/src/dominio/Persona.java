package dominio;

import java.util.Objects;

/**
 * Objeto Persona, contiene los datos comunes a todos los tipos de usuarios del
 * programa
 */
public class Persona implements Comparable<Persona> {

	protected String _dni;
	protected String _nombre;
	protected String _domicilio;
	protected String _email;
	protected int _tlf;
	protected String _contrasena;

	/**
	 * Constructor de usuario con parámetros
	 * 
	 * @param dni
	 * @param nombre
	 * @param domicilio
	 * @param tlf
	 * @param contrasena
	 * @param email
	 */
	public Persona(String dni, String nombre, String domicilio, int tlf, String contrasena, String email) {
		this._dni = dni;
		this._nombre = nombre;
		this._domicilio = domicilio;
		this._tlf = tlf;
		_email = email;
		_contrasena = contrasena;
	}

	public String getDni() {
		return _dni;
	}

	public void setDni(String dni) {
		this._dni = dni;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		this._nombre = nombre;
	}

	public String getDomicilio() {
		return _domicilio;
	}

	public void setDomicilio(String domicilio) {
		this._domicilio = domicilio;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		this._email = email;
	}

	public int getTlf() {
		return _tlf;
	}

	public void setTlf(int tlf) {
		this._tlf = tlf;
	}

	public String getContrasena() {
		return _contrasena;
	}

	public void setContrasena(String contrasena) {
		_contrasena = contrasena;
	}

	// METODOS

	@Override
	public int compareTo(Persona p) {
		if (_dni.equals(p.getDni())) {
			return 0;
		}
		return -1;
	}

	@Override
	public String toString() {
		String str = "";
		str += "DNI: " + _dni;
		str += "\nNombre: " + _nombre;
		str += "\nDomicilio: " + _domicilio;
		str += "\nEmail: " + _email;
		str += "\nContrasena: " + _contrasena;
		str += "\nTelefono: " + _tlf;
		return str;
	}

	@Override
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
		Persona p = (Persona) o;
		return Objects.equals(_nombre, p.getNombre()) && Objects.equals(_contrasena, p.getContrasena())
				&& Objects.equals(_dni, p.getDni()) && Objects.equals(_domicilio, p.getDomicilio())
				&& Objects.equals(_email, p.getEmail()) && Objects.equals(_tlf, p.getTlf());

	}

}
