package common.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import common.Controller;
import subscuentas.view.CuentaWindow;
import subsprestamos.view.PrestWindow;
import substarjetas.view.TarjWindow;
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;
import subsusuarios.view.UserWindow;

/**
 * Botones del programa principal
 */
public class ControlPanel extends JPanel {

    private Controller _controller;

    /**
     * Constructor del panel de botones
     * 
     * @param c controller del programa principal
     */
    public ControlPanel(Controller c) {
        _controller = c;
        initGUI();
    }

    /**
     * Constructor de la apariencia del panel
     */
    private void initGUI() {
        JToolBar toolBar = new JToolBar();
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.PAGE_START);
        toolBar.setFloatable(false);
        // USER BUTTON
        createUserButton(toolBar);
        // CUENTA BUTTON
        createCuentaButton(toolBar);
        // TARJETA BUTTON
        createTarjetaButton(toolBar);
        // PRESTAMO BUTTON
        createPrestamoButton(toolBar);
        // CIERRE SESION
        createCerrarSesionButton(toolBar);
    }

    /**
     * crea el botón que lanza la gui de usuarios
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createUserButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("USUARIO");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new UserWindow(_controller.isAdmin(), _controller.getCurrentUser());
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_U);
        aux.add(userButton);
    }

    /**
     * crea el botón que lanza la gui de cuentas
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createCuentaButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("CUENTA");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CuentaWindow(_controller.isAdmin(), _controller.getCurrentUser());
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_K + KeyEvent.VK_C);
        aux.add(userButton);
    }

    /**
     * crea el botón que lanza la gui de tarjetas
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createTarjetaButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("TARJETA");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // new Insertar gui tarjeta
                new TarjWindow(_controller.isAdmin(), _controller.getCurrentUser());
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_T);
        aux.add(userButton);
    }

    /**
     * crea el botón que lanza la gui de prestamos
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createPrestamoButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("PRESTAMO");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // insertar gui prestamo
                new PrestWindow(_controller.isAdmin(), _controller.getCurrentUser());
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_P);
        aux.add(userButton);
    }

    /**
     * crea el botón que cierra la sesion actual
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createCerrarSesionButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("CERRAR SESION");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int decision = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?", "Cerrar sesión",
                        JOptionPane.YES_NO_OPTION);
                if (decision == 0) {
                    IFachadaSubsUsuarios subsUsuarios = new FachadaSubsUsuarios();
                    subsUsuarios.cerrarSesion();
                    _controller.sesionCerrada();
                    quit();
                }
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_P);
        aux.add(userButton);
    }

    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
