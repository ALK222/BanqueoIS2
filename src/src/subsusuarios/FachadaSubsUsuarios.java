package subsusuarios;

import java.util.List;

import common.misc.Pair;
import dominio.Persona;

public class FachadaSubsUsuarios implements IFachadaSubsUsuarios {
    ISASubsUsuarios subsUsuario;

    public FachadaSubsUsuarios() {
        subsUsuario = new SASubsUsuarios();
    }

    @Override
    public int altaUsuario(Persona p) {
        return subsUsuario.altaUsuario(p);
    }

    @Override
    public int bajaUsuario(Persona p) {
        return subsUsuario.bajaUsuario(p);
    }

    @Override
    public Pair<List<Persona>, Integer> consultarListaUsuarios(String domicilio, String modo) {
        return subsUsuario.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public int buscarUsuario(String dni) {
        return subsUsuario.buscarUsuario(dni);
    }

    @Override
    public int modificarUsuario(Persona p) {
        return subsUsuario.modificarUsuario(p);
    }

    @Override
    public Pair<Integer, Boolean> iniciarSesion(String dni, String contrasena) {
        return subsUsuario.iniciarSesion(dni, contrasena);
    }

    @Override
    public int cerrarSesion() {
        return subsUsuario.cerrarSesion();
    }

}
