package subscuentas.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.exception.GUIException;

/**
 * Panel de filtrado de cuentas
 * 
 * @see JPanel
 */
public class FiltrarGUI extends JPanel {
    private JLabel infoLabel;
    private JTextField dniText;
    private JLabel dniLabel;
    private JTextField titularText;
    private JLabel titularLabel;
    private JButton aceptarButton;
    private JButton cancelButton;

    /**
     * Constructor del panel
     */
    public FiltrarGUI() {
        // construct components
        infoLabel = new JLabel("Introduce el DNI y el titular de las cuentas");
        dniText = new JTextField(5);
        dniLabel = new JLabel("DNI");
        titularText = new JTextField(5);
        titularLabel = new JLabel("Titular");
        aceptarButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        // adjust size and set layout
        setPreferredSize(new Dimension(667, 322));
        setLayout(null);

        // add components
        add(infoLabel);
        add(dniText);
        add(dniLabel);
        add(titularText);
        add(titularLabel);
        add(aceptarButton);
        add(cancelButton);

        // set component bounds (only needed by Absolute Positioning)
        infoLabel.setBounds(200, 65, 315, 65);
        dniText.setBounds(140, 175, 100, 35);
        dniLabel.setBounds(140, 150, 100, 25);
        titularText.setBounds(345, 175, 100, 35);
        titularLabel.setBounds(345, 150, 100, 25);
        aceptarButton.setBounds(415, 280, 100, 25);
        cancelButton.setBounds(535, 280, 100, 25);

        // Action listeners
        aceptarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (titularText.getText().equals(""))
                        throw new GUIException("El titular es obligatorio");
                    if (dniText.getText().equals(""))
                        throw new GUIException("El DNI es obligatorio");

                    CuentaWindow.TITULAR_CUENTA = titularText.getText();
                    CuentaWindow.DNI = dniText.getText();
                    quit();
                } catch (Exception e1) {

                }
            }

        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

    }

    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FiltrarGUI());
        frame.pack();
        frame.setVisible(true);
    }
}
