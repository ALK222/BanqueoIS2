package usuariosdao.control;

import java.util.List;

public interface IFachadaDAOUsuarios {
    boolean altaUsuario(Persona p);

    boolean bajaUsuario(String dni);

    List<Persona> consultarListaUsuarios(String nombre);

    Persona buscarUsuario(String dni);

    Persona modificarUsuario(Persona p);

    boolean iniciarSesion(String dni, String contrasena);

    boolean cerrarSesion();

    boolean existeUsuario(String dni);
}
