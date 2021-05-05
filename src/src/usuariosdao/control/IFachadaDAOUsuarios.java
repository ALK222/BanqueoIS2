package usuariosdao.control;

import java.util.List;

public interface IFachadaDAOUsuarios {
    boolean altaUsuario(Persona p);

    boolean bajaUsuario(Persona p);

    List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception;

    Persona buscarUsuario(String dni) throws Exception;

    Persona modificarUsuario(Persona p);

    boolean iniciarSesion(String dni, String contrasena);

    boolean cerrarSesion();

    boolean existeUsuario(String dni);
}
