package subsusuarios;

import java.util.List;
import usuariosdao.control.Persona;

public class SASubsUsuarios implements ISASubsUsuarios{
    List<Persona> _listaPersonas;
    
    
    @Override
    public boolean altaUsuario(Persona p) {
        return _listaPersonas.contains(p) ? false : _listaPersonas.add(p);

    }

    @Override
    public boolean bajaUsuario(String dni) { // Posible persona
        for(Persona p: _listaPersonas){
            if(p.getDni() == dni){
                _listaPersonas.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Persona> consultarListaUsuarios(String nombre) {
        return null;
    }

    @Override
    public Persona buscarUsuario(String dni) throws Exception {
            for (Persona p : _listaPersonas){
                if (p.getDni() == dni){
                    return p;
                }
            }
        return null;
    }

    @Override
    public Persona modificarUsuario(Persona p) { // posible bool
        return null;
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        for (Persona p : _listaPersonas) {
            if (p.getDni() == dni && p.getContrasena() == contrasena) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cerrarSesion() {
        return true;
    }

}
