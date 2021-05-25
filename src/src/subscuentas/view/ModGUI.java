package subscuentas.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;

import common.exception.CuentaException;
import common.exception.GUIException;
import dominio.Cuenta;
import subscuentas.model.FachadaSubsCuentas;
import subscuentas.model.IFachadaSubsCuentas;

/**
 * GUI de modificación de cuentas
 * 
 * @see JPanel
 */
public class ModGUI extends JPanel {
    protected JTextField titular;
    protected JTextField dni;
    protected JLabel titularLabel;
    protected JLabel dniLabel;
    protected JLabel numCuentaLabel;
    protected JTextField numCuenta;
    protected JLabel saldoLabel;
    protected JTextField saldo;
    protected JLabel firmaDigitalLabel;
    protected JTextField firmaDigital;
    protected JLabel movimientosLabel;
    protected JTextField movimientos;
    protected JButton altaButton;
    protected JButton cancelButton;

    /**
     * Constructor de la GUI
     * 
     * @param c Cuenta a modificar
     */
    public ModGUI(Cuenta c) {
        initGUI();

        dni.setText(c.getTitularCuenta().getSecond());
        titular.setText(c.getTitularCuenta().getFirst());
        firmaDigital.setText(c.getFirmaDigital());
        movimientos.setText(c.getMovimientos().toString());
        numCuenta.setText(String.valueOf(c.getNumeroCuenta()));
        saldo.setText(String.valueOf(c.getSaldo()));

        dni.setEnabled(false);
    }

    /**
     * Constructor del panel de la GUI
     */
    public void initGUI() {
        // construct components
        titular = new JTextField(5);
        dni = new JTextField(5);
        titularLabel = new JLabel("Titular*");
        dniLabel = new JLabel("DNI*");
        numCuentaLabel = new JLabel("Número de Cuenta*");
        numCuenta = new JPasswordField(5);
        saldoLabel = new JLabel("Saldo");
        saldo = new JTextField(5);
        firmaDigitalLabel = new JLabel("Firma digital");
        firmaDigital = new JTextField(5);
        movimientosLabel = new JLabel("Movimientos ");
        movimientos = new JTextField(5);
        altaButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        // Set button actions
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (titular.getText().equals("") || dni.getText().equals("") || firmaDigital.getText().equals(""))
                        throw new GUIException("Alguno de los valores obligatorios esta vacío");

                    IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                    int resultado = 1;
                    JSONArray movimientosArray = new JSONArray();
                    movimientosArray.put(movimientos.getText());
                    resultado = subsCuentas.modificarCuenta(
                            new Cuenta(titular.getText(), dni.getText(), Integer.parseInt(numCuenta.getText()),
                                    Double.parseDouble(saldo.getText()), firmaDigital.getText(), movimientosArray));
                    switch (resultado) {
                        case 0:
                            JOptionPane.showMessageDialog(null, "Cuenta modificada correctamente");
                            quit();
                            break;
                        case 1:
                            throw new CuentaException("Fallo al modificar la cuenta. Compruebe que no exista ya");
                        case 10:
                            throw new GUIException(
                                    "Fallo al encontrar el archivo de cuentas. Contacte con el soporte.");
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
        titular.setToolTipText("Titular de la cuenta a dar de alta");
        dni.setToolTipText("DNI del titular a dar de alta");
        numCuenta.setToolTipText("Número de cuenta del usuario");
        saldo.setToolTipText("Saldo de la cuenta a registrar");
        firmaDigital.setToolTipText("Firma digital del cliente a dar de alta");
        movimientos.setToolTipText("Movimientos del usuario a dar de alta");

        // adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        // add components
        add(titular);
        add(dniLabel);
        add(titularLabel);
        add(dni);
        add(numCuentaLabel);
        add(numCuenta);
        add(saldoLabel);
        add(saldo);
        add(firmaDigitalLabel);
        add(firmaDigital);
        add(movimientosLabel);
        add(movimientos);
        add(altaButton);
        add(cancelButton);

        // set component bounds (only needed by Absolute Positioning)
        titular.setBounds(30, 35, 870, 35);
        dni.setBounds(30, 95, 200, 35);
        titularLabel.setBounds(30, 10, 100, 25);
        dniLabel.setBounds(30, 70, 100, 25);
        numCuentaLabel.setBounds(270, 70, 100, 25);
        numCuenta.setBounds(270, 95, 300, 35);
        saldoLabel.setBounds(30, 130, 100, 25);
        saldo.setBounds(30, 155, 400, 35);
        firmaDigitalLabel.setBounds(30, 190, 100, 25);
        firmaDigital.setBounds(30, 215, 200, 35);
        movimientosLabel.setBounds(30, 250, 50, 25);
        movimientos.setBounds(30, 275, 350, 35);
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
