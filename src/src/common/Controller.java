package common;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import common.view.InicioSesionGUI;
import common.view.MainWindow;
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

    public void initGUI() {
        new MainWindow(this);
    }

    public void sesionCerrada() {
        _currentUser = new Persona(null, null, null, 0, null, null);
        _isAdmin = false;
        JFrame frame = new JFrame("Inicio de Sesion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new InicioSesionGUI(this));
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                initGUI();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });
    }

}
