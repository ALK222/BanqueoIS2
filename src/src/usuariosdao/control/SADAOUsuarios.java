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
    public List<Persona> consultarListaUsuarios(String domicilio, String modo) throws Exception {
        // String de ejemplo: Calle noseque, 15 2A;45007;Toledo
        String[] aux = domicilio.split(";");
        String[] calle = aux[0].split(",");
        List<Persona> listaAux = new ArrayList<Persona>();
        switch (modo.toLowerCase()) {
            case "cp": // Codigo postal
                for (Persona p : _listaPersonas) {
                    if (p.getDomicilio().split(";")[1] == aux[1]) {
                        listaAux.add(p);
                    }
                }
                break;
            case "c": // Ciudad
                for (Persona p : _listaPersonas) {
                    if (p.getDomicilio().split(";")[2] == aux[2]) {
                        listaAux.add(p);
                    }
                }
                break;
            case "st": // Calle
                for (Persona p : _listaPersonas) {
                    String callePersona = p.getDomicilio().split(";")[0].split(",")[0];
                    if (callePersona == calle[0]) {
                        listaAux.add(p);
                    }
                }
                break;
            default:
                throw new Exception("Modo no soportado");
                break;
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
