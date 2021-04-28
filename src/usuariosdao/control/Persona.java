package usuariosdao.control;

/**
 * Objeto Persona, contiene los datos comunes a todos los tipos de usuarios del
 * programa
 */
public class Persona {

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
	Persona(String dni, String nombre, String domicilio, int tlf, String contrasena, String email) {
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

}
