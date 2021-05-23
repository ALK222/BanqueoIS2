package common;

import dominio.Persona;

/**
 * Controlador principal del programa
 */
public class Controller {

    private Persona _currentUser;

    private boolean _isAdmin;

    public Controller() {
        _currentUser = new Persona(null, null, null, 0, null, null);
        _isAdmin = false;
    }

    public void setUser(Persona first) {
        _currentUser = first;
    }

    public void setAdmin(Boolean second) {
        _isAdmin = second;
    }

    public void initGUI() {

    }

}
