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
    public boolean bajaUsuario(Persona p) {
        return daoUsu.bajaUsuario(p);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception {
        return daoUsu.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public Persona buscarUsuario(String dni) throws Exception {
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
