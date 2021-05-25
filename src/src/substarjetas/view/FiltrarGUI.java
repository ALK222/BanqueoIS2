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

/**
 * Panel de filtrado de tarjetas
 * 
 * @see JPanel
 */
public class FiltrarGUI extends JPanel {
    private JButton yesButton;
    private JButton noButton;
    private JTextField dniValue;
    private JLabel dni;

    /**
     * Constructor del panel
     */
    public FiltrarGUI() {
        // // construct preComponents
        // String[] modeSelectorItems = { "Tipo", "Fecha" };

        // construct components
        // modeSelector = new JComboBox<String>(modeSelectorItems);
        // modoFiltrado = new JLabel("Modo de Filtrado");
        yesButton = new JButton("ACEPTAR");
        noButton = new JButton("CANCELAR");
        dniValue = new JTextField(5);
        dni = new JLabel("DNI del usuario");

        // adjust size and set layout
        setPreferredSize(new Dimension(296, 213));
        setLayout(null);

        // // add components
        // add(modeSelector);
        // add(modoFiltrado);
        add(yesButton);
        add(noButton);
        add(dni);
        add(dniValue);

        // set component bounds (only needed by Absolute Positioning)
        yesButton.setBounds(30, 145, 100, 25);
        noButton.setBounds(160, 145, 100, 25);
        dniValue.setBounds(30, 90, 100, 25);
        dni.setBounds(30, 65, 100, 25);
        // set button actions

        yesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dniValue.getText() == "") {
                        throw new GUIException("Domicilio obligatorio.");
                    }
                    TarjWindow.DNI = dniValue.getText();
                    quit();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }

            }

        });

        noButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }

        });
    }

    private void quit() {
    	 JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(this);
         frame.dispose();
    }

    public static void main(String[] args) {
        JDialog frame = new JDialog();
        frame.setTitle("Filtrado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FiltrarGUI());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
