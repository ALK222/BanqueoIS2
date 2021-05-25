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
	 * Constructor con parámetros de Cliente
	 * 
	 * @param dni        DNI del usuario
	 * @param nombre     Nombre del usuario
	 * @param domicilio  Domicilio del usuario
	 * @param tlf        Telefono del usuario
	 * @param gestorDni  DNI de su gestor asignado
	 * @param contrasena Contraseña del usuario
	 * @param email      Email del usuario
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
