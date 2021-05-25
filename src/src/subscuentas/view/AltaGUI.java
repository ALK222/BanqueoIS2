package subscuentas.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;

import common.exception.CuentaException;
import common.exception.GUIException;
import dominio.Cuenta;
import subscuentas.FachadaSubsCuentas;
import subscuentas.IFachadaSubsCuentas;

/**
 * GUI del alta de cuentas
 * 
 * @see JPanel
 */
public class AltaGUI extends JPanel {

    protected JLabel movimientos;
    protected JLabel titular;
    protected JLabel firma_digital;
    protected JLabel dni;
    protected JLabel num_cuenta;
    protected JLabel saldo;
    protected JTextField saldoText;
    protected JTextField num_cuentaText;
    protected JTextField dniText;
    protected JTextField firma_digitalText;
    protected JTextField titularText;
    protected JTextField movimientosText;
    protected JButton alta;
    protected JButton volver;

    /**
     * Constructor de la GUI
     */
    public AltaGUI() {
        initGUI();
    }

    /**
     * Constructor del panel de la GUI
     */
    public void initGUI() {
        // construct components
        movimientos = new JLabel("Movimientos");
        titular = new JLabel("Titular");
        firma_digital = new JLabel("Firma digital");
        num_cuenta = new JLabel("Numero de cuenta"); // TODO : hay que mirar lo del numero de cuenta y la firma digital
        saldo = new JLabel("Saldo");
        dni = new JLabel("DNI");
        saldoText = new JTextField(5);
        num_cuentaText = new JTextField(5);
        firma_digitalText = new JTextField(5);
        titularText = new JTextField(5);
        movimientosText = new JTextField(5);
        dniText = new JTextField(5);
        alta = new JButton("ACEPTAR");
        volver = new JButton("VOLVER");

        // Set button actions
        alta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (titularText.getText().equals("") || dniText.getText().equals("")
                            || firma_digitalText.getText().equals(""))
                        throw new GUIException("Alguno de los valores obligatorios esta vacío");

                    IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                    int resultado = 1;
                    if (movimientosText.getText().equals("") && saldoText.getText().equals("0")) {
                        resultado = subsCuentas.altaCuenta(new Cuenta(titularText.getText(), dniText.getText(),
                                Integer.parseInt(num_cuentaText.getText()), 0, firma_digitalText.getText(),
                                new JSONArray()));
                    } else {
                        JSONArray movimientosArray = new JSONArray();
                        movimientosArray.put(movimientosText.getText());
                        resultado = subsCuentas.altaCuenta(new Cuenta(titularText.getText(), dniText.getText(),
                                Integer.parseInt(num_cuentaText.getText()), Double.parseDouble(saldoText.getText()),
                                firma_digitalText.getText(), movimientosArray));
                    }

                    switch (resultado) {
                        case 0:
                            JOptionPane.showMessageDialog(null, "Cuenta añadida correctamente");
                            quit();
                            break;
                        case 1:
                            throw new CuentaException("Fallo al añadir la cuenta. Compruebe que no exista ya");
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

        volver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }

        });

        // set components properties
        movimientos.setToolTipText("Movimientos de la cuenta");
        titular.setToolTipText("Titular de la cuenta a la que dar de alta");
        firma_digital.setToolTipText("Firma digital de la cuenta a la que dar de alta");
        num_cuenta.setToolTipText("Numero de la cuenta  a dar de alta ");
        saldo.setToolTipText("Saldo de la cuenta a la que dar de alta");
        dni.setToolTipText("DNI del titular de la cuenta a dar de alta");
        alta.setToolTipText("Alta");
        volver.setToolTipText("Volver");

        // adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        // add components
        add(movimientos);
        add(movimientosText);
        add(dni);
        add(dniText);
        add(titular);
        add(titularText);
        add(firma_digital);
        add(firma_digitalText);
        add(num_cuenta);
        add(num_cuentaText);
        add(saldo);
        add(saldoText);
        add(alta);
        add(volver);

        // set component bounds (only needed by Absolute Positioning)

        movimientos.setBounds(30, 250, 90, 25);
        movimientosText.setBounds(30, 275, 350, 35);
        dni.setBounds(30, 70, 100, 25);
        dniText.setBounds(30, 95, 200, 35);
        titular.setBounds(30, 10, 100, 25);
        titularText.setBounds(30, 35, 870, 35);
        firma_digital.setBounds(30, 190, 100, 25);
        firma_digitalText.setBounds(30, 215, 200, 35);
        num_cuenta.setBounds(30, 130, 150, 25);
        num_cuentaText.setBounds(30, 155, 400, 35);
        saldo.setBounds(400, 250, 100, 25);
        saldoText.setBounds(400, 275, 100, 35);
        alta.setBounds(705, 505, 100, 35);
        volver.setBounds(815, 505, 100, 35);
    }

    /**
     * Comportamiento al cerrar la ventana
     */
    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
