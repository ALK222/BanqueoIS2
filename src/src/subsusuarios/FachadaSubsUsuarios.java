package subsusuarios;

import java.util.List;

import usuariosdao.control.Persona;

public class FachadaSubsUsuarios implements IFachadaSubsUsuarios {
    ISASubsUsuarios subsUsuario;

    public FachadaSubsUsuarios() {
        subsUsuario = new SASubsUsuarios();
    }

    public FachadaSubsUsuarios(List<Persona> listaPersonas) {
        subsUsuario = new SASubsUsuarios(listaPersonas);
    }

    @Override
    public boolean altaUsuario(Persona p) {
        return subsUsuario.altaUsuario(p);

    }

    @Override
    public boolean bajaUsuario(Persona p) { // Posible persona

        return subsUsuario.bajaUsuario(p);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception {
        return subsUsuario.consultarListaUsuarios(domicilio, modo);
    }

    @Override
    public Persona buscarUsuario(String dni) throws Exception {
        return subsUsuario.buscarUsuario(dni);
    }

    @Override
    public boolean modificarUsuario(Persona p) { // posible bool
        return subsUsuario.modificarUsuario(p);
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        return subsUsuario.iniciarSesion(dni, contrasena);
    }

    @Override
    public boolean cerrarSesion() {
        return subsUsuario.cerrarSesion();
    }

}
