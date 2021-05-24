package common.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.Controller;
import common.exception.GUIException;
import common.exception.UserException;
import common.misc.Pair;
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;

public class InicioSesionGUI extends JPanel {
    private JTextField dniValue;
    private JLabel dniLabel;
    private JLabel contrasenaLabel;
    private JPasswordField contrasenaValue;
    private JButton accesoButton;

    public InicioSesionGUI(Controller c) {
        // construct components
        dniValue = new JTextField(5);
        dniLabel = new JLabel("DNI");
        contrasenaLabel = new JLabel("Contraseña");
        contrasenaValue = new JPasswordField(5);
        accesoButton = new JButton("ACCEDER");

        // adjust size and set layout
        setPreferredSize(new Dimension(466, 280));
        setLayout(null);

        // add components
        add(dniValue);
        add(dniLabel);
        add(contrasenaLabel);
        add(contrasenaValue);
        add(accesoButton);

        // set component bounds (only needed by Absolute Positioning)
        dniValue.setBounds(112, 75, 230, 35);
        dniLabel.setBounds(112, 50, 100, 25);
        contrasenaLabel.setBounds(112, 110, 100, 25);
        contrasenaValue.setBounds(112, 135, 230, 35);
        accesoButton.setBounds(175, 210, 100, 25);
        accesoButton.setMnemonic(KeyEvent.VK_ENTER);
        accesoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IFachadaSubsUsuarios subsUsuarios = new FachadaSubsUsuarios();
                Pair<Integer, Boolean> inicio = subsUsuarios.iniciarSesion(dniValue.getText(),
                        String.valueOf(contrasenaValue.getPassword()));
                try {
                    switch (inicio.getFirst()) {
                        case 0:
                            c.setUser(subsUsuarios.buscarUsuario(dniValue.getText()).getFirst());
                            c.setAdmin(inicio.getSecond());
                            quit();
                            break;
                        case 1:
                            throw new UserException("Inicio de sesión fallido, compruebe el usuario y la contraseña");
                        case 2:
                            break;
                        case 10:
                            throw new GUIException("No se pudo acceder a la base de datos, Contacte con el soporte");
                        default:
                            break;
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }

            }

        });

    }

    public void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new InicioSesionGUI(null));
        frame.pack();
        frame.setVisible(true);
    }
}
