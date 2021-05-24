package subsprestamos.view;

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
import subsprestamos.FachadaSubsPrestamos;
import subsprestamos.IFachadaSubsPrestamos;
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;

public class AltaGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected JLabel cantidadLabel;
    protected JTextField cantidad;
    protected JLabel plazo_debLabel;
    protected JTextField plazo_deb;
    protected JLabel avalLabel;
    protected JTextField aval;
    protected JLabel profesionLabel;
    protected JTextField profesion;
    protected JLabel firma_seguro_funcionLabel;
    protected JTextField firma_seguro_funcion;
    protected JLabel estadoLabel;
    protected JTextField estado;
    protected JButton altaButton;
    protected JButton cancelButton;
  

    public AltaGUI() {
        initGUI();
    }

    public void initGUI() {
        // construct components
    	cantidadLabel = new JLabel("Cantidad");
    	cantidad = new JTextField(5);
    	plazo_debLabel = new JLabel("Plazo devolución");
    	plazo_deb = new JTextField(5);
    	avalLabel = new JLabel("Aval");
    	aval = new JTextField(5);
    	profesionLabel = new JLabel("Profesión");
    	profesion = new JTextField(5);
    	firma_seguro_funcionLabel = new JLabel("Firma seguro función");
    	firma_seguro_funcion = new JTextField(5);
    	estadoLabel = new JLabel("Estado");
    	estado = new JTextField(5);
        altaButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        // Set button actions
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (firma_seguro_funcion.getText().equals("") || cantidad.getText().equals("")){
                        throw new GUIException("Alguno de los valores obligatorios esta vacio");
                    }
                    IFachadaSubsPrestamos subsPrestamos = new FachadaSubsPrestamos();
                    int resultado = 1;
                    if (gestorCheck.isSelected()) {
                        resultado = subsPrestamos.altaPrestamo(new Gestor(dniLabel.getText(), nombre.getText(),
                                calleLabel.getText() + "," + portalPisoLabel.getText() + ";"
                                        + codigoPostalLabel.getText() + ";" + poblacionLabel.getText(),
                                Integer.parseInt(telefonoLabel.getText()),
                                String.valueOf(contrasenaLabel.getPassword()), emailLabel.getText()));
                    } else {
                        resultado = subsUsuarios.altaUsuario(new Cliente(dniLabel.getText(), nombre.getText(),
                                calleLabel.getText() + "," + portalPisoLabel.getText() + ";"
                                        + codigoPostalLabel.getText() + ";" + poblacionLabel.getText(),
                                Integer.parseInt(telefonoLabel.getText()),
                                String.valueOf(contrasenaLabel.getPassword()), emailLabel.getText(),
                                dniGestorLabel.getText()));
                    }
                    switch (resultado) {
                        case 0:
                            JOptionPane.showMessageDialog(null, "Prestamo añadido correctamente");
                            quit();
                            break;
                        case 1:
                            throw new UserException("Fallo al añadir el préstamo. Compruebe que no exista ya");
                        case 10:
                            throw new GUIException(
                                    "Fallo al encontrar el archivo de préstamos. Contacte con el soporte.");
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
        cantidadLabel.setToolTipText("Cantidad del préstamo");
        plazo_debLabel.setToolTipText("Plazo de devolución del préstamo");
        avalLabel.setToolTipText("Dni del aval");
        profesionLabel.setToolTipText("Profesión");
        firma_seguro_funcionLabel.setToolTipText("Firma Seguro Función");
        estadoLabel.setToolTipText("Estado del préstamo");

        // adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        // add components
        add(cantidadLabel);
        add(cantidad);
        add(plazo_debLabel);
        add(plazo_deb);
        add(avalLabel);
        add(aval);
        add(profesionLabel);
        add(profesion);
        add(firma_seguro_funcionLabel);
        add(firma_seguro_funcion);
        add(estadoLabel);
        add(estado);
        add(altaButton);
        add(cancelButton);

        // set component bounds (only needed by Absolute Positioning)
        cantidadLabel.setBounds(30, 10, 100, 25);
        cantidad.setBounds(30, 35, 870, 35);
        avalLabel.setBounds(30, 70, 100, 25);
        aval.setBounds(30, 95, 200, 35);
        firma_seguro_funcionLabel.setBounds(30, 130, 100, 25);
        firma_seguro_funcion.setBounds(30, 155, 400, 35);
        plazo_debLabel.setBounds(30, 190, 100, 25);
        plazo_deb.setBounds(30, 215, 200, 35);
        estadoLabel.setBounds(30, 250, 50, 25);
        estado.setBounds(30, 275, 350, 35);
        profesionLabel.setBounds(400, 250, 100, 25);
        profesion.setBounds(400, 275, 100, 35);
        altaButton.setBounds(705, 505, 100, 35);
        cancelButton.setBounds(815, 505, 100, 35);
        
    }

    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
