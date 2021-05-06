package subsusuarios;

import java.util.List;

import usuariosdao.control.Persona;

public interface ISASubsUsuarios {
    
    boolean altaUsuario(Persona p);

    boolean bajaUsuario(Persona p);

    List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception;

    Persona buscarUsuario(String dni) throws Exception;

    boolean modificarUsuario(Persona p);

    boolean iniciarSesion(String dni, String contrasena);

    boolean cerrarSesion();
}
