package subsusuarios;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import dominio.Persona;
import usuariosdao.control.FachadaDAOUsuarios;
import usuariosdao.control.IFachadaDAOUsuarios;

public class SASubsUsuarios implements ISASubsUsuarios {
    IFachadaDAOUsuarios usuario;

    public SASubsUsuarios() {
        usuario = new FachadaDAOUsuarios();
    }

    @Override
    public boolean altaUsuario(Persona p) throws IOException, UserException {
        if (usuario.existeUsuario(p.getDni())) {
            throw new UserException("Este usuario ya existe");
        } else {
            return usuario.altaUsuario(p);
        }
    }

    @Override
    public boolean bajaUsuario(Persona p) throws UserException, IOException { // Posible persona
        if (!usuario.existeUsuario(p.getDni())) {
            throw new UserException("Este usuario no existe");
        } else {
            return usuario.bajaUsuario(p);
        }
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws UserException, IOException { // Preguntar
                                                                                                                   // al
        // profesor sobre
        // como
        // modificar esto
        return usuario.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public Persona buscarUsuario(String dni) throws UserException, IOException {
        return usuario.buscarUsuario(dni);
    }

    @Override
    public boolean modificarUsuario(Persona p) throws UserException, IOException { // posible bool
        if (!usuario.modificarUsuario(p)) {
            throw new UserException("Modificacion fallida");
        }
        return true;
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) throws UserException, IOException {
        if (usuario.iniciarSesion(dni, contrasena)) {
            return true;
        } else {
            throw new UserException("Usuario o contraseña erroneos");
        }
    }

    @Override
    public boolean cerrarSesion() {
        return usuario.cerrarSesion();
    }

}
