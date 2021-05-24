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
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;
import subsusuarios.view.UserWindow;

public class ControlPanel extends JPanel {

    private Controller _controller;

    public ControlPanel(Controller c) {
        _controller = c;
        initGUI();
    }

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
        createPrestamoButton(toolBar);
        // PRESTAMO BUTTON
        createPrestamoButton(toolBar);
        // CIERRE SESION
        createCerrarSesionButton(toolBar);
    }

    /**
     * Creates a button with the User GUI
     * 
     * @param aux toolbar to add the button
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
     * Creates a button with the Cuenta GUI
     * 
     * @param aux toolbar to add the button
     */
    private void createCuentaButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("CUENTA");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CuentaWindow(_controller.isAdmin());
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_K + KeyEvent.VK_C);
        aux.add(userButton);
    }

    /**
     * Creates a button with the Tarjeta GUI
     * 
     * @param aux toolbar to add the button
     */
    private void createTarjetaButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("TARJETA");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // new Insertar gui tarjeta
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_T);
        aux.add(userButton);
    }

    /**
     * Creates a button with the Prestamo GUI
     * 
     * @param aux toolbar to add the button
     */
    private void createPrestamoButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("PRESTAMO");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // insertar gui prestamo
            }

        });
        userButton.setMnemonic(KeyEvent.VK_CONTROL + KeyEvent.VK_P);
        aux.add(userButton);
    }

    /**
     * Creates a button to end the current sesion
     * 
     * @param aux toolbar to add the button
     */
    private void createCerrarSesionButton(JToolBar aux) {
        JButton userButton = new JButton();
        userButton.setToolTipText("Lanza el menú de usuarios");
        userButton.setText("CERRAR SESION");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int decision = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?", "Cerrar sesión",
                        JOptionPane.YES_NO_CANCEL_OPTION);
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
