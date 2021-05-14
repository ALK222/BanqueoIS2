package usuariosdao.control;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import dominio.Persona;

/**
 * Intermediario entre el SADAO de usuarios y la GUI
 */
public class FachadaDAOUsuarios implements IFachadaDAOUsuarios {

    private ISADAOUsuarios daoUsu;

    public FachadaDAOUsuarios() {
        daoUsu = new SADAOUsuarios();
    }

    @Override
    public boolean altaUsuario(Persona p) throws IOException {
        return daoUsu.altaUsuario(p);
    }

    @Override
    public boolean bajaUsuario(Persona p) throws IOException {
        return daoUsu.bajaUsuario(p);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws UserException, IOException {
        return daoUsu.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public Persona buscarUsuario(String dni) throws UserException, IOException {
        return daoUsu.buscarUsuario(dni);
    }

    @Override
    public boolean modificarUsuario(Persona p) throws IOException {
        return daoUsu.modificarUsuario(p);
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) throws IOException {
        return daoUsu.iniciarSesion(dni, contrasena);
    }

    @Override
    public boolean cerrarSesion() {
        return daoUsu.cerrarSesion();
    }

    @Override
    public boolean existeUsuario(String dni) throws IOException {
        return daoUsu.existeUsuario(dni);
    }

}
