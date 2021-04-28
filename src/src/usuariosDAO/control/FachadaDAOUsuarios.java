package usuariosdao.control;

import java.util.List;

public class FachadaDAOUsuarios implements IFachadaDAOUsuarios {

    private ISADAOUsuarios daoUsu;

    // TODO: falta constructor

    @Override
    public boolean altaUsuario(Persona p) {
        return daoUsu.altaUsuario(p);
    }

    @Override
    public boolean bajaUsuario(String dni) {
        return daoUsu.bajaUsuario(dni);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String nombre) {
        return daoUsu.consultarListaUsuarios(nombre);
    }

    @Override
    public Persona buscarUsuario(String dni) {
        return daoUsu.buscarUsuario(dni);
    }

    @Override
    public Persona modificarUsuario(Persona p) {
        return daoUsu.modificarUsuario(p);
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        return daoUsu.iniciarSesion(dni, contrasena);
    }

    @Override
    public boolean cerrarSesion() {
        return daoUsu.cerrarSesion();
    }

    @Override
    public boolean existeUsuario(String dni) {
        return daoUsu.existeUsuario(dni);
    }

}
