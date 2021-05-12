package subsusuarios;

import java.io.IOException;
import java.util.List;

import usuariosdao.control.FachadaDAOUsuarios;
import usuariosdao.control.IFachadaDAOUsuarios;
import usuariosdao.control.Persona;

public class SASubsUsuarios implements ISASubsUsuarios {
    IFachadaDAOUsuarios usuario;

    public SASubsUsuarios() {
        usuario = new FachadaDAOUsuarios();
    }

    public SASubsUsuarios(List<Persona> listaPersonas) {
        usuario = new FachadaDAOUsuarios(listaPersonas);
    }

    @Override
    public boolean altaUsuario(Persona p) throws IOException {
        return usuario.altaUsuario(p);

    }

    @Override
    public boolean bajaUsuario(Persona p) { // Posible persona

        return usuario.bajaUsuario(p);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception {
        return usuario.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public Persona buscarUsuario(String dni) throws Exception {
        return usuario.buscarUsuario(dni);
    }

    @Override
    public boolean modificarUsuario(Persona p) { // posible bool
        return usuario.modificarUsuario(p);
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        return usuario.iniciarSesion(dni, contrasena);
    }

    @Override
    public boolean cerrarSesion() {
        return usuario.cerrarSesion();
    }

}
