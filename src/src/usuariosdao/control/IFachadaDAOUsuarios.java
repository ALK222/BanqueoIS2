package usuariosdao.control;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import common.misc.Pair;
import dominio.Persona;

/**
 * Interfaz de la fachada de usuarios
 */
public interface IFachadaDAOUsuarios {
    /**
     * Alta de un usuario en la base de datos
     * 
     * @param p Persona a la que dar de alta
     * @return Si el usuario se ha podido registrar
     * @throws IOException
     */
    boolean altaUsuario(Persona p) throws IOException;

    /**
     * Baja de un usuario de la base de datos
     * 
     * @param p Persona a la que dar de baja
     * @return Si la persona ha podido ser dada de baja
     * @throws IOException
     */
    boolean bajaUsuario(Persona p) throws IOException;

    /**
     * Consulta la lista de usuarios para obtener solo los que cumplan los
     * requisitos de localizacion
     * 
     * @param domicilio Domicilio completo del usuario
     * @param modo      Modo de discriminacion
     * @return Una lista de usuaios discriminada por localización
     * @throws UserException Si el modo no es valido
     * @throws IOException
     */
    List<Persona> consultarListaUsuarios(String domicilio, String modo) throws UserException, IOException;

    /**
     * Busca un usuario en la base de datos
     * 
     * @param dni DNI del usuario
     * @return La persona si se ha encontrado, null si no
     * @throws UserException Si el DNI no es valido
     * @throws IOException
     */
    Persona buscarUsuario(String dni) throws UserException, IOException;

    /**
     * Modifica un usuario en la base de datos
     * 
     * @param p Persona ya modificada
     * @return Si se ha podido modificar o no
     * @throws IOException
     */
    boolean modificarUsuario(Persona p) throws IOException;

    /**
     * Inicia sesión del usuario
     * 
     * @param dni        DNI del usuario
     * @param contrasena Contrasena del usuario
     * @return Si se ha podido iniciar sesión o no
     * @throws IOException
     */
    Pair<Boolean, Boolean> iniciarSesion(String dni, String contrasena) throws IOException;

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
     * @throws IOException
     */
    boolean existeUsuario(String dni) throws IOException;
}
