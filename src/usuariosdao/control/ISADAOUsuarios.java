package usuariosdao.control;

import java.util.List;

public interface ISADAOUsuarios {
    boolean altaUsuario(Persona p);

    boolean bajaUsuario(Persona p);

    List<Persona> consultarListaUsuarios(String nombre);

    Persona buscarUsuario(String dni) throws Exception;

    Persona modificarUsuario(Persona p);

    boolean iniciarSesion(String dni, String contrasena);

    boolean cerrarSesion();

    boolean existeUsuario(String dni);
}
