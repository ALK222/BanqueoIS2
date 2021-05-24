package usuariosdao.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.exception.UserException;
import common.misc.Pair;
import dominio.Gestor;
import dominio.Persona;

/**
 * Gestiona la informacion saliente sobre los usuarios
 */
public class SADAOUsuarios implements ISADAOUsuarios {

    public SADAOUsuarios() {
    }

    @Override
    public boolean altaUsuario(Persona p) throws IOException {
        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();
        boolean ok = listaPersonas.contains(p) ? false : listaPersonas.add(p);

        UsuariosJSON.guardarListaUsuarios(listaPersonas);
        return ok;
    }

    @Override
    public boolean bajaUsuario(Persona p) throws IOException {
        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();
        boolean ok = listaPersonas.contains(p) ? listaPersonas.remove(p) : false;

        UsuariosJSON.guardarListaUsuarios(listaPersonas);
        return ok;
    }

    @Override
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws UserException, IOException {
        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();
        // String de ejemplo: Calle noseque, 15 2A;45007;Toledo
        List<Persona> listaAux = new ArrayList<Persona>();
        switch (modo.toLowerCase()) {
            case "cp": // Codigo postal
                for (Persona p : listaPersonas) {
                    if (p.getDomicilio().split(";")[1].equalsIgnoreCase(domicilio)) {
                        listaAux.add(p);
                    }
                }
                break;
            case "c": // Ciudad
                for (Persona p : listaPersonas) {
                    if (p.getDomicilio().split(";")[2].equalsIgnoreCase(domicilio)) {
                        listaAux.add(p);
                    }
                }
                break;
            case "st": // Calle
                for (Persona p : listaPersonas) {
                    String callePersona = p.getDomicilio().split(";")[0].split(",")[0];
                    if (callePersona.equalsIgnoreCase(domicilio)) {
                        listaAux.add(p);
                    }
                }
                break;
            default:
                throw new UserException("Modo no soportado");
        }

        return listaAux;
    }

    @Override
    public Persona buscarUsuario(String dni) throws UserException, IOException {
        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();
        try {
            if (dni == null) {
                throw new UserException("DNI nulo");
            }
            for (Persona persona : listaPersonas) {
                if (persona.getDni().equals(dni)) {
                    return persona;
                }
            }
        } catch (UserException e) {
            throw e;
        }

        UsuariosJSON.guardarListaUsuarios(listaPersonas);
        return null;
    }

    @Override
    public boolean modificarUsuario(Persona p) throws IOException {

        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();

        boolean conseguido = false;
        int i = 0;
        while (i < listaPersonas.size() && !conseguido) {
            if (listaPersonas.get(i).compareTo(p) == 0) {
                conseguido = true;
            }
            if (!conseguido) {
                i++;
            }
        }
        if (conseguido) {
            listaPersonas.remove(i);
            listaPersonas.add(p);
        }

        UsuariosJSON.guardarListaUsuarios(listaPersonas);
        return conseguido;
    }

    @Override
    public Pair<Boolean, Boolean> iniciarSesion(String dni, String contrasena) throws IOException {

        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();

        for (Persona persona : listaPersonas) {
            if (persona.getDni().equals(dni) && persona.getContrasena().equals(contrasena)) {
                return new Pair<Boolean, Boolean>(true, persona.getClass() == Gestor.class);
            }
        }

        return new Pair<Boolean, Boolean>(false, false);
    }

    @Override
    public boolean cerrarSesion() {
        // TODO Auto-generated method stub
        // En controller poner el usuario activo a null
        // Y guardar cosos
        return true;
    }

    @Override
    public boolean existeUsuario(String dni) throws IOException {

        List<Persona> listaPersonas = UsuariosJSON.leerListaUsuarios();

        for (Persona persona : listaPersonas) {
            if (persona.getDni().equals(dni)) {
                return true;
            }
        }

        return false;
    }

}
