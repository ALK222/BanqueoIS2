package usuariosdao.control;

import java.util.List;

/**
 * Interfaz de la fachada de usuarios
 */
public interface IFachadaDAOUsuarios {
    /**
     * Alta de un usuario en la base de datos
     * 
     * @param p Persona a la que dar de alta
     * @return Si el usuario se ha podido registrar
     */
    boolean altaUsuario(Persona p);

    /**
     * Baja de un usuario de la base de datos
     * 
     * @param p Persona a la que dar de baja
     * @return Si la persona ha podido ser dada de baja
     */
    boolean bajaUsuario(Persona p);

    /**
     * Consulta la lista de usuarios para obtener solo los que cumplan los
     * requisitos de localizacion
     * 
     * @param domicilio Domicilio completo del usuario
     * @param modo      Modo de discriminacion
     * @return Una lista de usuaios discriminada por localización
     * @throws Exception Si el modo no es valido
     */
    List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception;

    /**
     * Busca un usuario en la base de datos
     * 
     * @param dni DNI del usuario
     * @return La persona si se ha encontrado, null si no
     * @throws Exception Si el DNI no es valido
     */
    Persona buscarUsuario(String dni) throws Exception;

    /**
     * Modifica un usuario en la base de datos
     * 
     * @param p Persona ya modificada
     * @return Si se ha podido modificar o no
     */
    boolean modificarUsuario(Persona p);

    /**
     * Inicia sesión del usuario
     * 
     * @param dni        DNI del usuario
     * @param contrasena Contrasena del usuario
     * @return Si se ha podido iniciar sesión o no
     */
    boolean iniciarSesion(String dni, String contrasena);

    /**
     * Cierra la sesión del usuario actual
     * 
     * @return Si el usuario ha podido cerrar sesión
     */
    boolean cerrarSesion();

    /**
     * Determina si un usuario existe o no
     * 
     * @param dni DNI del usaurio
     * @return Si existe o no
     */
    boolean existeUsuario(String dni);
}
