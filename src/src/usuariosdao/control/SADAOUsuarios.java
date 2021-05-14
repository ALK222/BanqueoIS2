package usuariosdao.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dominio.Persona;

/**
 * Gestiona la informacion saliente sobre los usuarios
 */
public class SADAOUsuarios implements ISADAOUsuarios {

    private List<Persona> _listaPersonas;

    public SADAOUsuarios() {
        _listaPersonas = new ArrayList<Persona>();
    }

    public SADAOUsuarios(List<Persona> listaPersonas) {
        _listaPersonas = listaPersonas;
    }

    @Override
    public boolean altaUsuario(Persona p) throws IOException {
        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();
        boolean ok = listaPersonas.contains(p) ? false : listaPersonas.add(p);

        UsuariosJSON.guardarListaUsuarios(listaPersonas);
        return ok;
    }

    @Override
    public boolean bajaUsuario(Persona p) {
        return _listaPersonas.contains(p) ? _listaPersonas.remove(p) : false;
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception {
        // String de ejemplo: Calle noseque, 15 2A;45007;Toledo
        List<Persona> listaAux = new ArrayList<Persona>();
        switch (modo.toLowerCase()) {
            case "cp": // Codigo postal
                for (Persona p : _listaPersonas) {
                    if (p.getDomicilio().split(";")[1].equalsIgnoreCase(domicilio)) {
                        listaAux.add(p);
                    }
                }
                break;
            case "c": // Ciudad
                for (Persona p : _listaPersonas) {
                    if (p.getDomicilio().split(";")[2].equalsIgnoreCase(domicilio)) {
                        listaAux.add(p);
                    }
                }
                break;
            case "st": // Calle
                for (Persona p : _listaPersonas) {
                    String callePersona = p.getDomicilio().split(";")[0].split(",")[0];
                    if (callePersona.equalsIgnoreCase(domicilio)) {
                        listaAux.add(p);
                    }
                }
                break;
            default:
                throw new Exception("Modo no soportado");
        }
        return listaAux;
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
    public boolean modificarUsuario(Persona p) {
        boolean conseguido = false;
        for (Persona persona : _listaPersonas) {
            if (persona.compareTo(p) == 0) {
                persona = p;
                conseguido = true;
            }
        }
        return conseguido;
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
