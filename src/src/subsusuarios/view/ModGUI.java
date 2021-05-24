package subsusuarios.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.exception.GUIException;
import common.exception.UserException;
import dominio.Cliente;
import dominio.Gestor;
import dominio.Persona;
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;

/**
 * GUI de modificación de usuarios
 * 
 * @see JPanel
 */
public class ModGUI extends JPanel {
    protected JTextField nombre;
    protected JTextField dniLabel;
    protected JLabel nombreLabel;
    protected JLabel dni;
    protected JLabel contrasena;
    protected JPasswordField contrasenaLabel;
    protected JLabel email;
    protected JTextField emailLabel;
    protected JLabel telefono;
    protected JTextField telefonoLabel;
    protected JLabel calle;
    protected JTextField calleLabel;
    protected JTextField portalPisoLabel;
    protected JLabel portalPiso;
    protected JTextField codigoPostalLabel;
    protected JLabel codigoPostal;
    protected JTextField poblacionLabel;
    protected JLabel poblacion;
    protected JCheckBox gestorCheck;
    protected JTextField dniGestorLabel;
    protected JLabel dniGestor;
    protected JButton altaButton;
    protected JButton cancelButton;

    /**
     * Constructor de la GUI
     * 
     * @param p Usuario a modificar
     */
    public ModGUI(Persona p) {
        initGUI();
        if (p.getClass() == Cliente.class) {
            Cliente c = (Cliente) p;
            dniGestorLabel.setText(c.getGestorDni());
        } else {
            gestorCheck.setSelected(true);
        }
        dniLabel.setText(p.getDni());
        nombre.setText(p.getNombre());
        telefonoLabel.setText(String.valueOf(p.getTlf()));
        calleLabel.setText(p.getDomicilio().split(";")[0].split(",")[0]);
        portalPisoLabel.setText(p.getDomicilio().split(";")[0].split(",")[1]);
        codigoPostalLabel.setText(p.getDomicilio().split(";")[1]);
        poblacionLabel.setText(p.getDomicilio().split(";")[2]);
        contrasenaLabel.setText(p.getContrasena());
        emailLabel.setText(p.getEmail());

        dniLabel.setEnabled(false);
    }

    /**
     * Constructor del panel de la GUI
     */
    public void initGUI() {
        // construct components
        nombre = new JTextField(5);
        dniLabel = new JTextField(5);
        nombreLabel = new JLabel("Nombre*");
        dni = new JLabel("DNI*");
        contrasena = new JLabel("Contraseña*");
        contrasenaLabel = new JPasswordField(5);
        email = new JLabel("Email");
        emailLabel = new JTextField(5);
        telefono = new JLabel("Teléfono");
        telefonoLabel = new JTextField(5);
        calle = new JLabel("Calle");
        calleLabel = new JTextField(5);
        portalPisoLabel = new JTextField(5);
        portalPiso = new JLabel("Portal Piso");
        codigoPostalLabel = new JTextField(5);
        codigoPostal = new JLabel("Código Postal");
        poblacionLabel = new JTextField(5);
        poblacion = new JLabel("Población");
        gestorCheck = new JCheckBox("Gestor");
        dniGestorLabel = new JTextField(5);
        dniGestor = new JLabel("DNI Gestor");
        altaButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        // Set button actions
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nombre.getText().equals("") || dniLabel.getText().equals("")
                            || contrasenaLabel.getPassword().length == 0) {
                        throw new GUIException("Alguno de los valores obligatorios está vacio");
                    }
                    IFachadaSubsUsuarios subsUsuarios = new FachadaSubsUsuarios();
                    int resultado = 1;
                    if (gestorCheck.isSelected()) {
                        resultado = subsUsuarios.modificarUsuario(new Gestor(dniLabel.getText(), nombre.getText(),
                                calleLabel.getText() + "," + portalPisoLabel.getText() + ";"
                                        + codigoPostalLabel.getText() + ";" + poblacionLabel.getText(),
                                Integer.parseInt(telefonoLabel.getText()),
                                String.valueOf(contrasenaLabel.getPassword()), emailLabel.getText()));
                    } else {
                        resultado = subsUsuarios.modificarUsuario(new Cliente(dniLabel.getText(), nombre.getText(),
                                calleLabel.getText() + "," + portalPisoLabel.getText() + ";"
                                        + codigoPostalLabel.getText() + ";" + poblacionLabel.getText(),
                                Integer.parseInt(telefonoLabel.getText()), dniGestorLabel.getText(),
                                String.valueOf(contrasenaLabel.getPassword()), emailLabel.getText()));
                    }
                    switch (resultado) {
                        case 0:
                            JOptionPane.showMessageDialog(null,
                                    "Usuario modificado correctamente. Si se ha modificado a si mismo tendrá que reiniciar para aplicar los cambios");
                            quit();
                            break;
                        case 1:
                            throw new UserException("Fallo al modificar el usuario. Compruebe que no exista ya");
                        case 10:
                            throw new GUIException(
                                    "Fallo al encontrar el archivo de usuarios. Contacte con el soporte.");
                        default:
                            throw new GUIException("Error inesperado. Contacte con el soporte");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }

        });

        // set components properties
        nombre.setToolTipText("Nombre del usuario a dar de alta");
        dniLabel.setToolTipText("DNI del usuario a dar de alta");
        contrasenaLabel.setToolTipText("Contraseña del usuario");
        emailLabel.setToolTipText("Email del usuario a registrar");
        telefonoLabel.setToolTipText("Teléfono del cliente a dar de alta");
        calleLabel.setToolTipText("Calle del usuario a dar de alta");
        portalPisoLabel.setToolTipText("Portal y piso del usuario a dar de alta");
        codigoPostalLabel.setToolTipText("Código Postal del usuario a dar de alta");
        poblacionLabel.setToolTipText("Población del usuario a dar de alta");
        gestorCheck.setToolTipText("Marcar si el usuario a dar de alta es un gestor");
        dniGestorLabel.setToolTipText("DNI del gestor del usuario");

        // adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        // add components
        add(nombre);
        add(dniLabel);
        add(nombreLabel);
        add(dni);
        add(contrasena);
        add(contrasenaLabel);
        add(email);
        add(emailLabel);
        add(telefono);
        add(telefonoLabel);
        add(calle);
        add(calleLabel);
        add(portalPisoLabel);
        add(portalPiso);
        add(codigoPostalLabel);
        add(codigoPostal);
        add(poblacionLabel);
        add(poblacion);
        add(gestorCheck);
        add(dniGestorLabel);
        add(dniGestor);
        add(altaButton);
        add(cancelButton);

        // set component bounds (only needed by Absolute Positioning)
        nombre.setBounds(30, 35, 870, 35);
        dniLabel.setBounds(30, 95, 200, 35);
        nombreLabel.setBounds(30, 10, 100, 25);
        dni.setBounds(30, 70, 100, 25);
        contrasena.setBounds(270, 70, 100, 25);
        contrasenaLabel.setBounds(270, 95, 300, 35);
        email.setBounds(30, 130, 100, 25);
        emailLabel.setBounds(30, 155, 400, 35);
        telefono.setBounds(30, 190, 100, 25);
        telefonoLabel.setBounds(30, 215, 200, 35);
        calle.setBounds(30, 250, 50, 25);
        calleLabel.setBounds(30, 275, 350, 35);
        portalPisoLabel.setBounds(400, 275, 100, 35);
        portalPiso.setBounds(400, 250, 100, 25);
        codigoPostalLabel.setBounds(518, 275, 100, 35);
        codigoPostal.setBounds(518, 250, 100, 25);
        poblacionLabel.setBounds(639, 275, 162, 35);
        poblacion.setBounds(638, 250, 100, 25);
        gestorCheck.setBounds(30, 335, 100, 35);
        dniGestorLabel.setBounds(155, 336, 100, 35);
        dniGestor.setBounds(155, 310, 100, 25);
        altaButton.setBounds(705, 505, 100, 35);
        cancelButton.setBounds(815, 505, 100, 35);
    }

    /**
     * Comportamiento al cerrar la ventana
     */
    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
