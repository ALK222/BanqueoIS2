package dominio;

/**
 * Objeto Gestor, usuarios con permisos especiales de administracion.
 * 
 * Extiende de la clase Persona.
 * 
 * @see Persona
 */
public class Gestor extends Persona {

	/**
	 * Constructor del objeto gestor
	 * 
	 * @param dni        DNI del usuario
	 * @param nombre     Nombre del usuario
	 * @param domicilio  Domicilio del usuario
	 * @param tlf        Teléfono del usuario
	 * @param contrasena Contraseña del usuario
	 * @param email      Email del usuario
	 */
	public Gestor(String dni, String nombre, String domicilio, int tlf, String contrasena, String email) {
		super(dni, nombre, domicilio, tlf, contrasena, email);
	}

}
