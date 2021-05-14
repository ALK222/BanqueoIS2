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
	 * @param dni
	 * @param nombre
	 * @param domicilio
	 * @param tlf
	 * @param contrasena
	 * @param email
	 */
	public Gestor(String dni, String nombre, String domicilio, int tlf, String contrasena, String email) {
		super(dni, nombre, domicilio, tlf, contrasena, email);
	}

}
