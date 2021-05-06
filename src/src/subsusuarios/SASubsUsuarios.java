package subsusuarios;

import java.util.List;

import usuariosdao.control.IFachadaDAOUsuarios;
import usuariosdao.control.Persona;

public class SASubsUsuarios implements ISASubsUsuarios{
    IFachadaDAOUsuarios usuario;
    
    @Override
    public boolean altaUsuario(Persona p) {
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
        return iniciarSesion(dni, contrasena);
    }

    @Override
    public boolean cerrarSesion() {
        return usuario.cerrarSesion();
    }

}
