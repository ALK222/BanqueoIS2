package dominio;

/**
 * Objeto Cliente, usuario sin permisos administrativos especiales.
 * 
 * Extiende de la Clase Persona.
 * 
 * @see Persona
 */
public class Cliente extends Persona {

	private String _gestorDni;

	/**
	 * Constructor con parametros de Cliente
	 * 
	 * @param dni
	 * @param nombre
	 * @param domicilio
	 * @param tlf
	 * @param gestorDni
	 * @param contrasena
	 * @param email
	 */
	public Cliente(String dni, String nombre, String domicilio, int tlf, String gestorDni, String contrasena,
			String email) {
		super(dni, nombre, domicilio, tlf, contrasena, email);
		this._gestorDni = gestorDni;
	}

	public String getGestorDni() {
		return _gestorDni;
	}

	public void setGestorDni(String gestorDni) {
		this._gestorDni = gestorDni;
	}

}
