package subsusuarios.model;

import java.io.IOException;
import java.util.List;

import common.exception.UserException;
import common.misc.Pair;
import dominio.Persona;
import usuariosdao.control.FachadaDAOUsuarios;
import usuariosdao.control.IFachadaDAOUsuarios;

public class SASubsUsuarios implements ISASubsUsuarios {
    IFachadaDAOUsuarios usuario;

    public SASubsUsuarios() {
        usuario = new FachadaDAOUsuarios();
    }

    @Override
    public int altaUsuario(Persona p) {
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
    public int bajaUsuario(Persona p) {
        try {
            if (!usuario.existeUsuario(p.getDni())) {
                return 1;
            } else {
                usuario.bajaUsuario(p);
                return 0;
            }
        } catch (IOException e) {
            return 10;
        }
    }

    @Override
    public Pair<List<Persona>, Integer> consultarListaUsuarios(String domicilio, String modo) {
        try {
            List<Persona> listaFiltrada = usuario.consultarListaUsuarios(domicilio, modo);
            if (listaFiltrada == null) {
                return new Pair<List<Persona>, Integer>(null, 1);
            } else {
                return new Pair<List<Persona>, Integer>(listaFiltrada, 0);
            }

        } catch (IOException e) {
            return new Pair<List<Persona>, Integer>(null, 10);
        } catch (UserException e) {
            return new Pair<List<Persona>, Integer>(null, 2);
        }
    }

    @Override
    public Pair<Persona, Integer> buscarUsuario(String dni) {
        try {
            Persona personaBusacada = usuario.buscarUsuario(dni);
            if (personaBusacada == null) {
                return new Pair<Persona, Integer>(null, 1);
            } else {

                return new Pair<Persona, Integer>(personaBusacada, 0);
            }
        } catch (IOException e) {
            return new Pair<Persona, Integer>(null, 10);
        } catch (UserException e) {
            return new Pair<Persona, Integer>(null, 2);
        }
    }

    @Override
    public int modificarUsuario(Persona p) {
        try {
            if (!usuario.modificarUsuario(p)) {
                return 1;
            } else {
                return 0;
            }
        } catch (IOException e) {
            return 10;
        }
    }

    @Override
    public Pair<Integer, Boolean> iniciarSesion(String dni, String contrasena) {
        try {
            Pair<Boolean, Boolean> inicioSesion = usuario.iniciarSesion(dni, contrasena);
            if (inicioSesion.getFirst()) {
                return new Pair<Integer, Boolean>(0, inicioSesion.getSecond());
            } else {
                return new Pair<Integer, Boolean>(1, false);
            }
        } catch (IOException e) {
            return new Pair<Integer, Boolean>(10, false);
        }
    }

    @Override
    public int cerrarSesion() {
        usuario.cerrarSesion();
        return 0;
    }

}
