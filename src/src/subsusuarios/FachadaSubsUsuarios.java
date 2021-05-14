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
    public boolean altaUsuario(Persona p) throws IOException, UserException {
        return subsUsuario.altaUsuario(p);

    }

    @Override
    public boolean bajaUsuario(Persona p) throws UserException, IOException {

        return subsUsuario.bajaUsuario(p);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws UserException, IOException {
        return subsUsuario.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public Persona buscarUsuario(String dni) throws UserException, IOException {
        return subsUsuario.buscarUsuario(dni);
    }

    @Override
    public boolean modificarUsuario(Persona p) throws UserException, IOException { // posible bool
        return subsUsuario.modificarUsuario(p);
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) throws UserException, IOException {
        return subsUsuario.iniciarSesion(dni, contrasena);
    }

    @Override
    public boolean cerrarSesion() {
        return subsUsuario.cerrarSesion();
    }

}
