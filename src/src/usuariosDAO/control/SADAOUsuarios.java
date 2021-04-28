package usuariosdao.control;

import java.util.List;

public class SADAOUsuarios implements ISADAOUsuarios {

    private List<Persona> _listaPersonas;

    @Override
    public boolean altaUsuario(Persona p) {
        return _listaPersonas.contains(p) ? false : _listaPersonas.add(p);

    }

    @Override
    public boolean bajaUsuario(String dni) { // Posible persona
        // TODO: dni o persona
        return false;// _listaPersonas.contains() ? true : _listaPersonas.add(p);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String nombre) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Persona buscarUsuario(String dni) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Persona modificarUsuario(Persona p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean cerrarSesion() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean existeUsuario(String dni) {
        // TODO Auto-generated method stub
        return false;
    }

}
