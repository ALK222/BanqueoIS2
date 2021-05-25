package substarjetas.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.exception.GUIException;
import common.exception.UserException;
import dominio.Tarjeta;
import dominio.TipoTarjeta;
import substarjetas.FachadaSubsTarjetas;
import substarjetas.IFachadaSubsTarjetas;

/**
 * GUI de modificación de tarjetas
 * 
 * @see JPanel
 */
public class ModGUI extends JPanel {
    private static final long serialVersionUID = 1L;

    protected JLabel titularLabel;
    protected JTextField titular;
    protected JLabel numTarjetaLabel;
    protected JTextField numTarjeta;
    protected JLabel pinLabel;
    protected JTextField pin;
    protected JLabel estadoLabel;
    protected JTextField estado;
    protected JLabel tipoLabel;
    protected JTextField tipo;
    protected JLabel fechaLabel;
    protected JTextField fecha;
    protected JLabel dniLabel;
    protected JTextField dni;
    protected JButton altaButton;
    protected JButton cancelButton;

    /**
     * Constructor de la GUI
     * 
     * @param t              Tarjeta a modificar
     * @param permisosGestor Activa o no los permisos de gestor
     */
    public ModGUI(Tarjeta t, boolean permisosGestor) {
        String estadoAux = "ON";
        initGUI();
        dni.setText(t.get_dni());
        titular.setText(t.getTitular());
        numTarjeta.setText(String.valueOf(t.getNum_tarjeta()));
        pin.setText(String.valueOf(t.getPin()));
        if (t.getEstado() == false)
            estadoAux = "OFF";
        estado.setText(estadoAux);
        tipo.setText(String.valueOf(t.getTipo_tarjeta()));
        fecha.setText(t.getFechaCad());

        dni.setEnabled(false);
        numTarjeta.setEnabled(false);
        titular.setEnabled(false);
        tipo.setEnabled(permisosGestor);

    }

    /**
     * Constructor del panel de la GUI
     */
    public void initGUI() {
        // construct components
        titularLabel = new JLabel("Titular");
        titular = new JTextField(5);
        numTarjetaLabel = new JLabel("Numero Tarjeta");
        numTarjeta = new JTextField(5);
        pinLabel = new JLabel("Pin");
        pin = new JTextField(5);
        estadoLabel = new JLabel("Estado: ON/OFF");
        estado = new JTextField(5);
        tipoLabel = new JLabel("Tipo");
        tipo = new JTextField(5);
        fechaLabel = new JLabel("Fecha");
        fecha = new JTextField(5);
        dniLabel = new JLabel("Dni");
        dni = new JTextField(5);
        altaButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        // Set button actions
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (titular.getText().equals("") || pin.getText().equals("") || fecha.getText().equals("")
                            || tipo.getText().equals("") || dni.getText().equals("")) {
                        throw new GUIException("Alguno de los valores obligatorios esta vacio");
                    }
                    IFachadaSubsTarjetas subsTarejtas = new FachadaSubsTarjetas();
                    int resultado = 1;
                    boolean estadoAux;

                    if (estado.getText().equals("ON")) {
                        estadoAux = true;
                    } else {
                        estadoAux = false;
                    }

                    resultado = subsTarejtas.modificarTarjeta(new Tarjeta(titular.getText(),
                            Integer.parseInt(pin.getText()), estadoAux, Integer.parseInt(numTarjeta.getText()),
                            fecha.getText(), TipoTarjeta.parse(tipo.getText()), dni.getText()));

                    switch (resultado) {
                        case 0:
                            JOptionPane.showMessageDialog(null, "Tarjeta modificada correctamente");
                            quit();
                            break;
                        case 1:
                            throw new UserException("Fallo al modificar la tarjeta. Compruebe que no exista ya");
                        case 10:
                            throw new GUIException(
                                    "Fallo al encontrar el archivo de la tarjeta. Contacte con el soporte.");
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
        titularLabel.setToolTipText("Titular de la tarjeta a dar de alta");
        numTarjetaLabel.setToolTipText("Numero de la tarjeta a dar de alta");
        pinLabel.setToolTipText("Pin de la tarjeta a dar de alta");
        estadoLabel.setToolTipText("Como se encuentra la tarjeta de estado");
        tipoLabel.setToolTipText("Tipo de la tarjeta a dar de alta");
        fechaLabel.setToolTipText("Fecha de caducidad de la tarjeta a dar de alta");

        // adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        // add components
        add(titularLabel);
        add(titular);
        add(dniLabel);
        add(dni);
        add(numTarjetaLabel);
        add(numTarjeta);
        add(pinLabel);
        add(pin);
        add(tipoLabel);
        add(tipo);
        add(estadoLabel);
        add(estado);
        add(fechaLabel);
        add(fecha);
        add(altaButton);
        add(cancelButton);

        // set component bounds (only needed by Absolute Positioning)
        titularLabel.setBounds(30, 10, 100, 25);
        titular.setBounds(30, 35, 870, 35);
        dniLabel.setBounds(30, 70, 100, 25);
        dni.setBounds(30, 95, 200, 35);
        numTarjetaLabel.setBounds(30, 130, 100, 25);
        numTarjeta.setBounds(30, 155, 400, 35);
        pinLabel.setBounds(30, 190, 100, 25);
        pin.setBounds(30, 215, 200, 35);
        tipoLabel.setBounds(30, 250, 50, 25);
        tipo.setBounds(30, 275, 350, 35);
        estadoLabel.setBounds(400, 250, 100, 25);
        estado.setBounds(400, 275, 100, 35);
        fechaLabel.setBounds(518, 250, 100, 25);
        fecha.setBounds(518, 275, 100, 35);
        altaButton.setBounds(705, 505, 100, 35);
        cancelButton.setBounds(815, 505, 100, 35);
    }

    /**
     * Comportamiento al cerrar la ventana
     */
    private void quit() {
    	 JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(this);
         frame.dispose();
    }
}
