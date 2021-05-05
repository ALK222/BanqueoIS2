package usuariosdao.control;

import java.util.ArrayList;
import java.util.List;

public class SADAOUsuarios implements ISADAOUsuarios {

    private List<Persona> _listaPersonas;

    SADAOUsuarios() {
        _listaPersonas = new ArrayList<Persona>();
    }

    @Override
    public boolean altaUsuario(Persona p) {
        return _listaPersonas.contains(p) ? false : _listaPersonas.add(p);

    }

    @Override
    public boolean bajaUsuario(Persona p) {
        return _listaPersonas.contains(p) ? true : _listaPersonas.remove(p);
    }

    @Override
    public List<Persona> consultarListaUsuarios(String nombre) {
        // TODO: Nombre estupido
        return null;
    }

    @Override
    public Persona buscarUsuario(String dni) throws Exception {
        try {
            if (dni == null) {
                throw new Exception("DNI nulo");
            }
            for (Persona persona : _listaPersonas) {
                if (persona.getDni().equals(dni)) {
                    return persona;
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return null;
    }

    @Override
    public Persona modificarUsuario(Persona p) { // posible bool
        for (Persona persona : _listaPersonas) {
            if (persona.compareTo(p) == 0) {
                persona = p;
            }
        }
        return null;
    }

    @Override
    public boolean iniciarSesion(String dni, String contrasena) {
        for (Persona persona : _listaPersonas) {
            if (persona.getDni().equals(dni) && persona.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cerrarSesion() {
        // TODO Auto-generated method stub
        // En controller poner el usuario activo a null
        // Y guardar cosos
        return true;
    }

    @Override
    public boolean existeUsuario(String dni) {
        for (Persona persona : _listaPersonas) {
            if (persona.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

}
