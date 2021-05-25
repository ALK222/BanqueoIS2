package subsprestamos.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.exception.GUIException;
import common.exception.UserException;
import dominio.EstadoPrestamo;
import dominio.Prestamo;
import subsprestamos.FachadaSubsPrestamos;
import subsprestamos.IFachadaSubsPrestamos;

public class ModGUI extends JPanel {
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
    protected JLabel numReferenciaLabel;
    protected JTextField numReferencia;
    protected JLabel numeroCuentaLabel;
    protected JTextField numeroCuenta;

    protected JButton altaButton;
    protected JButton cancelButton;

    public ModGUI(Prestamo p) {
        String estadoAux = "SOLICITADO";
        String firma = "NO FIRMADO";
        initGUI();
        cantidad.setText(String.valueOf(p.getCantidadSolicitada()));
        plazo_deb.setText(p.getPlazoDevolucion());
        aval.setText(String.valueOf(p.getAval()));
        profesion.setText(p.getProfesion());
        if (p.getEstadoPrestamo().equals("SOLICITADO"))
            estadoAux = "SOLICITADO";
        else if (p.getEstadoPrestamo().equals("DENEGADO"))
            estadoAux = "DENEGADO";
        else if (p.getEstadoPrestamo().equals("ACEPTADO"))
            estadoAux = "ACEPTADO";
        estado.setText(estadoAux);

        if (p.isFirmaSeguroDefuncion() == true)
            firma = "FIRMADO";

        firma_seguro_funcion.setText(firma);
        numReferencia.setText(String.valueOf(p.getNumReferencia()));
        numeroCuenta.setText(String.valueOf(p.getCuentaAsociada()));

        numReferencia.setEnabled(false);
        numeroCuenta.setEnabled(false);
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
        numeroCuentaLabel = new JLabel("Numero de Cuenta");
        numeroCuenta = new JTextField(5);
        numReferenciaLabel = new JLabel("Numero de referencia");
        numReferencia = new JTextField(5);
        altaButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        // Set button actions
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (firma_seguro_funcion.getText().equals("") || cantidad.getText().equals("")) {
                        throw new GUIException("Alguno de los valores obligatorios esta vacio");
                    }
                    IFachadaSubsPrestamos subsPrestamos = new FachadaSubsPrestamos();
                    int resultado = 1;
                    boolean firma;

                    if (firma_seguro_funcion.getText().equals("FIRMADO")) {
                        firma = true;
                    } else {
                        firma = false;
                    }

                    resultado = subsPrestamos.modificarPrestamo(new Prestamo(Integer.parseInt(numReferencia.getText()),
                            Double.parseDouble(cantidad.getText()), plazo_deb.getText(),
                            Integer.parseInt(aval.getText()), profesion.getText(), firma,
                            EstadoPrestamo.parse(estado.getText()), Integer.parseInt(numeroCuenta.getText())));

                    switch (resultado) {
                        case 0:
                            JOptionPane.showMessageDialog(null, "Préstamo modificada correctamente");
                            quit();
                            break;
                        case 1:
                            throw new UserException("Fallo al modificar el préstamo. Compruebe que no exista ya");
                        case 10:
                            throw new GUIException(
                                    "Fallo al encontrar el archivo del préstamo. Contacte con el soporte.");
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
        numeroCuenta.setToolTipText("Número de cuenta");
        numReferencia.setToolTipText("Numero de referencia");

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
        add(numeroCuentaLabel);
        add(numeroCuenta);
        add(numReferenciaLabel);
        add(numReferencia);
        add(altaButton);
        add(cancelButton);

        // set component bounds (only needed by Absolute Positioning)
        cantidadLabel.setBounds(30, 10, 100, 25);
        cantidad.setBounds(30, 35, 870, 35);
        avalLabel.setBounds(30, 70, 100, 25);
        aval.setBounds(30, 95, 200, 35);
        firma_seguro_funcionLabel.setBounds(30, 130, 200, 25);
        firma_seguro_funcion.setBounds(30, 155, 400, 35);
        plazo_debLabel.setBounds(30, 190, 100, 25);
        plazo_deb.setBounds(30, 215, 200, 35);
        numReferenciaLabel.setBounds(30, 250, 200, 25);
        numReferencia.setBounds(30, 275, 350, 35);
        numeroCuentaLabel.setBounds(30, 310, 150, 25);
        numeroCuenta.setBounds(30, 335, 350, 35);
        profesionLabel.setBounds(400, 250, 100, 25);
        profesion.setBounds(400, 275, 100, 35);
        altaButton.setBounds(705, 505, 100, 35);
        cancelButton.setBounds(815, 505, 100, 35);
    }

    private void quit() {
        JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
