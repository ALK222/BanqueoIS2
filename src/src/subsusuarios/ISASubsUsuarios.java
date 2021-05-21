package subsusuarios;

import java.util.List;

import common.misc.Pair;
import dominio.Persona;

public interface ISASubsUsuarios {

    /**
     * Alta de un usuario en la base de datos
     * 
     * @param p Persona a la que dar de alta
     * @return Si el usuario se ha podido registrar
     */
    int altaUsuario(Persona p);

    /**
     * Baja de un usuario de la base de datos
     * 
     * @param p Persona a la que dar de baja
     * @return Si la persona ha podido ser dada de baja
     */
    int bajaUsuario(Persona p);

    /**
     * Consulta la lista de usuarios para obtener solo los que cumplan los
     * requisitos de localizacion
     * 
     * @param domicilio Domicilio completo del usuario
     * @param modo      Modo de discriminacion
     * @return Una lista de usuaios discriminada por localización y un codigo de
     *         finalizacion
     */
    Pair<List<Persona>, Integer> consultarListaUsuarios(String domicilio, String modo);

    /**
     * Busca un usuario en la base de datos
     * 
     * @param dni DNI del usuario
     * @return La persona si se ha encontrado, null si no
     */
    int buscarUsuario(String dni);

    /**
     * Modifica un usuario en la base de datos
     * 
     * @param p Persona ya modificada
     * @return Si se ha podido modificar o no
     */
    int modificarUsuario(Persona p);

    /**
     * Determina si un usuario existe o no
     * 
     * @param dni DNI del usaurio
     * @return Si existe o no
     */
    Pair<Integer, Boolean> iniciarSesion(String dni, String contrasena);

    /**
     * Cierra la sesión del usuario actual
     * 
     * @return Si el usuario ha podido cerrar sesión
     */
    int cerrarSesion();
}
