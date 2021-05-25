package subsprestamos.view;

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

import common.exception.CuentaException;
import common.exception.GUIException;
import common.exception.UserException;
import common.misc.Pair;
import dominio.Cuenta;
import dominio.EstadoPrestamo;
import dominio.Prestamo;
import subscuentas.FachadaSubsCuentas;
import subscuentas.IFachadaSubsCuentas;
import subsprestamos.FachadaSubsPrestamos;
import subsprestamos.IFachadaSubsPrestamos;

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
    protected JLabel numRefLabel;
    protected JTextField numRef;
    protected JLabel numeroCuentaLabel;
    protected JTextField numeroCuenta;
    protected JButton altaButton;
    protected JButton cancelButton;

    public AltaGUI() {
        initGUI();
    }

    public void initGUI() {
        // construct components
        cantidadLabel = new JLabel("Cantidad*");
        cantidad = new JTextField(5);
        plazo_debLabel = new JLabel("Plazo devolución");
        plazo_deb = new JTextField(5);
        avalLabel = new JLabel("Aval");
        aval = new JTextField(5);
        profesionLabel = new JLabel("Profesión");
        profesion = new JTextField(5);
        firma_seguro_funcionLabel = new JLabel("Firma seguro función*");
        firma_seguro_funcion = new JTextField(5);
        numRefLabel = new JLabel("Numero de Referencia*");
        numeroCuenta = new JTextField(5);
        numeroCuentaLabel = new JLabel("Numero de Cuenta*");
        numRef = new JTextField(5);
        altaButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        // Set button actions
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (firma_seguro_funcion.getText().equals("") || cantidad.getText().equals("")
                            || numeroCuenta.getText().equals("") || numRef.getText().equals("")) {
                        throw new GUIException("Alguno de los valores obligatorios esta vacio");
                    }
                    IFachadaSubsPrestamos subsPrestamos = new FachadaSubsPrestamos();
                    int resultado = 1;
                    IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                    Pair<Cuenta, Integer> resultadoCuenta = subsCuentas
                            .buscaCuenta(Integer.parseInt(numeroCuenta.getText()));
                    switch (resultadoCuenta.getSecond()) {
                        case 0:
                            Prestamo p = new Prestamo(Integer.parseInt(numRef.getText()),
                                    Double.parseDouble(cantidad.getText()), plazo_deb.getText(),
                                    Integer.parseInt(aval.getText()), profesion.getText(),
                                    Boolean.parseBoolean(firma_seguro_funcion.getText()), EstadoPrestamo.SOLICITADO,
                                    Integer.parseInt(numeroCuenta.getText()));
                            resultado = subsPrestamos.solicitarPrestamo(resultadoCuenta.getFirst(), p);

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
                            break;
                        case 1:
                            throw new CuentaException("La cuenta asociada no fue encotrada");
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
        numRef.setToolTipText("Número de referencia del prestamo");
        numeroCuenta.setToolTipText("Número de referencia de la cuenta");

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
        add(numRef);
        add(numRefLabel);
        add(numeroCuenta);
        add(numeroCuentaLabel);
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
        numRefLabel.setBounds(30, 250, 200, 25);
        numRef.setBounds(30, 275, 350, 35);
        numeroCuentaLabel.setBounds(30, 310, 150, 25);
        numeroCuenta.setBounds(30, 335, 350, 35);
        profesionLabel.setBounds(400, 250, 100, 25);
        profesion.setBounds(400, 275, 100, 35);
        altaButton.setBounds(705, 505, 100, 35);
        cancelButton.setBounds(815, 505, 100, 35);

    }

    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Modo de alta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new AltaGUI());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
