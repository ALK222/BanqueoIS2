package usuariosDAO.control;

public class Persona {

	protected String _dni;
	protected String _nombre;
	protected String _domicilio;
	protected String _email;
	protected int _tlf;

	Persona(String dni, String nombre, String domicilio, int tlf) {
		this._dni = dni;
		this._nombre = nombre;
		this._domicilio = domicilio;
		this._tlf = tlf;
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

}
