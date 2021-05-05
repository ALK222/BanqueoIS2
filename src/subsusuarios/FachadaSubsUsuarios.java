package subsusuarios;

import java.util.List;
import usuariosdao.control.Persona;

public class FachadaSubsUsuarios implements IFachadaSubsUsuarios {
    IFachadaSubsUsuarios subsUsuarios;

    @Override
    public boolean altaUsuario(Persona p) {
        return subsUsuarios.altaUsuario(p);
    }

    @Override
    public boolean bajaUsuario(String dni) {
        return subsUsuarios.bajaUsuario(dni);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String nombre) {
        return subsUsuarios.consultarListaUsuarios(nombre);
    }

    @Override
    public Persona buscarUsuario(String dni) throws Exception {
        return subsUsuarios.buscarUsuario(dni);
    }

    @Override
    public Persona modificarUsuario(Persona p) {
        return subsUsuarios.modificarUsuario(p);
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        return subsUsuarios.iniciarSesion(dni, contrasena);
    }

    @Override
    public boolean cerrarSesion() {
        return subsUsuarios.cerrarSesion();
    }

}
