package subsusuarios;

import java.util.List;
import usuariosdao.control.Persona;

public interface IFachadaSubsUsuarios {
    
    boolean altaUsuario(Persona p);

    boolean bajaUsuario(String dni);

    List<Persona> consultarListaUsuarios(String nombre);

    Persona buscarUsuario(String dni) throws Exception;

    Persona modificarUsuario(Persona p);

    boolean iniciarSesion(String dni, String contrasena);

    boolean cerrarSesion();

}