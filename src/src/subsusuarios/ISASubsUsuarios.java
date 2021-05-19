package subsusuarios;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import dominio.Persona;

public interface ISASubsUsuarios {

    /**
     * Alta de un usuario en la base de datos
     * 
     * @param p Persona a la que dar de alta
     * @return Si el usuario se ha podido registrar
     * @throws IOException
     * @throws UserException
     */
    int altaUsuario(Persona p) throws IOException;

    /**
     * Baja de un usuario de la base de datos
     * 
     * @param p Persona a la que dar de baja
     * @return Si la persona ha podido ser dada de baja
     * @throws IOException
     * @throws UserException
     */
    int bajaUsuario(Persona p) throws IOException;

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
    int consultarListaUsuarios(String domicilio, String modo) throws IOException;

    /**
     * Busca un usuario en la base de datos
     * 
     * @param dni DNI del usuario
     * @return La persona si se ha encontrado, null si no
     * @throws UserException Si el DNI no es valido
     * @throws IOException
     */
    int buscarUsuario(String dni) throws UserException, IOException;

    /**
     * Modifica un usuario en la base de datos
     * 
     * @param p Persona ya modificada
     * @return Si se ha podido modificar o no
     * @throws IOException
     * @throws UserException
     */
    int modificarUsuario(Persona p) throws UserException, IOException;

    /**
     * Determina si un usuario existe o no
     * 
     * @param dni DNI del usaurio
     * @return Si existe o no
     * @throws IOException
     * @throws UserException
     */
    int iniciarSesion(String dni, String contrasena) throws UserException, IOException;

    /**
     * Cierra la sesión del usuario actual
     * 
     * @return Si el usuario ha podido cerrar sesión
     */
    int cerrarSesion();
}
