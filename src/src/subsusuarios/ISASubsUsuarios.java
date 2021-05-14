package subsusuarios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dominio.Persona;

public interface ISASubsUsuarios {

    boolean altaUsuario(Persona p) throws FileNotFoundException, IOException;

    boolean bajaUsuario(Persona p);

    List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception;

    Persona buscarUsuario(String dni) throws Exception;

    boolean modificarUsuario(Persona p);

    boolean iniciarSesion(String dni, String contrasena);

    boolean cerrarSesion();
}
