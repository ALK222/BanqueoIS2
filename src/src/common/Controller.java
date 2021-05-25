package common;

import common.view.MainWindow;
import dominio.Persona;

/**
 * Controlador principal del programa
 */
public class Controller {

    private Persona _currentUser;

    private boolean _isAdmin;

    /**
     * Constructor basico del controller
     */
    public Controller() {
        _currentUser = new Persona(null, null, null, 0, null, null);
        _isAdmin = false;
    }

    public Persona getCurrentUser() {
        return _currentUser;
    }

    public boolean isAdmin() {
        return _isAdmin;
    }

    public void setUser(Persona first) {
        _currentUser = first;
    }

    public void setAdmin(Boolean second) {
        _isAdmin = second;
    }

    /**
     * Inicio de la GUI
     */
    public void initGUI() {
        new MainWindow(this);
    }

    /**
     * Pasos a seguir tras cerrar sesion
     */
    public void sesionCerrada() {
        _currentUser = new Persona(null, null, null, 0, null, null);
        _isAdmin = false;
        Main.inicioSesionGui(this);
    }

}
