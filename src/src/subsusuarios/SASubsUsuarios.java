package subsusuarios;

import java.io.IOException;
import java.util.List;

import dominio.Persona;
import usuariosdao.control.FachadaDAOUsuarios;
import usuariosdao.control.IFachadaDAOUsuarios;

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
        if(usuario.existeUsuario(p.getDni()) {
            System.out.println("Este usuario ya existe");
            return false;
        }
        else{
            return usuario.altaUsuario(p);
        }
    }

    @Override
    public boolean bajaUsuario(Persona p) { // Posible persona
        if(!usuario.existeUsuario(p.getDni()) {
            System.out.println("Este usuario no existe");
            return false;
        }
        else{
            return usuario.bajaUsuario(p);
        }
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
        if (usuario.modificarUsuario(p)) {
            System.out.println("Se ha modificado el perfil corredctamente");
            return true;
        }
        else {
            System.out.println("No se ha podido modificar el perfil");
            return false;
        }
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        if (usuario.iniciarSesion(dni, contrasena)) {
            return true;
        }
        else {
            System.out.println("No se ha iniciado sesion");
            return false;
        }
    }

    @Override
    public boolean cerrarSesion() {
        return usuario.cerrarSesion();
    }

}
