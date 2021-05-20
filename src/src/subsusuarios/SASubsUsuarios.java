package subsusuarios;

import java.io.IOException;

import dominio.Persona;
import usuariosdao.control.FachadaDAOUsuarios;
import usuariosdao.control.IFachadaDAOUsuarios;

public class SASubsUsuarios implements ISASubsUsuarios {
    IFachadaDAOUsuarios usuario;

    public SASubsUsuarios() {
        usuario = new FachadaDAOUsuarios();
    }

    @Override
    public int altaUsuario(Persona p) throws IOException {
        try {
            if (usuario.existeUsuario(p.getDni())) {
                return 1;
            } else {
                usuario.altaUsuario(p);
                return 0;
            }
        } catch (IOException e) {
            return 10;
        }
        
    }

    @Override
    public int bajaUsuario(Persona p) throws IOException { // Posible persona
        try{
            if (!usuario.existeUsuario(p.getDni())) {
                 return 1;
             } else {
                usuario.bajaUsuario(p);
                return 0;
        }
        }catch(IOException e){
            return 10;
        }
    }

    @Override
    public int consultarListaUsuarios(String domicilio, String modo) throws IOException { // Preguntar
                                                                                                                   // al
        // profesor sobre
        // como
        // modificar esto
        try{
            if(usuario.consultarListaUsuarios(domicilio, modo).equals(null)){
                return 1;
            } else{
                return 0;
            }

        }catch (IOException e){
            return 10;
        }
    }

    @Override
    public int buscarUsuario(String dni) throws IOException {
        try{
            if(usuario.buscarUsuario(dni).equals(null)){
                return 1;
            }else {
                usuario.buscarUsuario(dni);
                return 0;
        }
        }catch(IOException e){
            return 10;
        }
    }

    @Override
    public int modificarUsuario(Persona p) throws IOException { // posible bool
        try{
            if (!usuario.modificarUsuario(p)) {
                return 1;
            } else {
                return 0;
            }
        }catch(IOException e){
            return 10;
        }
    }

    @Override
    public int iniciarSesion(String dni, String contrasena) throws IOException {
        try {
            if (usuario.iniciarSesion(dni, contrasena)) {
                return 0;
            } else {
                return 1;
            }
        } catch (IOException e) {
            return 10;        
        }
    }

    @Override
    public int cerrarSesion() {
        usuario.cerrarSesion();
        return 0;
    }

}
