package subsusuarios;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import dominio.Persona;

public class FachadaSubsUsuarios implements IFachadaSubsUsuarios {
    ISASubsUsuarios subsUsuario;

    public FachadaSubsUsuarios() {
        subsUsuario = new SASubsUsuarios();
    }

    @Override
    public int altaUsuario(Persona p) throws IOException, UserException {
        return subsUsuario.altaUsuario(p);

    }

    @Override
    public int bajaUsuario(Persona p) throws UserException, IOException {

        return subsUsuario.bajaUsuario(p);
    }

    @Override
    public int consultarListaUsuarios(String domicilio, String modo) throws IOException {
        return subsUsuario.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public int buscarUsuario(String dni) throws IOException {
        return subsUsuario.buscarUsuario(dni);
    }

    @Override
    public int modificarUsuario(Persona p) throws IOException { // posible bool
        return subsUsuario.modificarUsuario(p);
    }

    @Override
    public int iniciarSesion(String dni, String contrasena) throws IOException {
        return subsUsuario.iniciarSesion(dni, contrasena);
    }

    @Override
    public int cerrarSesion() {
        return subsUsuario.cerrarSesion();
    }

}
