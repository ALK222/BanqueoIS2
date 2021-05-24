package subsusuarios.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.exception.GUIException;

public class FiltrarGUI extends JPanel {
    private JComboBox<String> modeSelector;
    private JLabel modoFiltrado;
    private JButton yesButton;
    private JButton noButton;
    private JTextField modeKeyValue;
    private JLabel modeKey;

    public FiltrarGUI() {
        // construct preComponents
        String[] modeSelectorItems = { "Calle", "Ciudad", "Código Postal" };

        // construct components
        modeSelector = new JComboBox<String>(modeSelectorItems);
        modoFiltrado = new JLabel("Modo de filtrado");
        yesButton = new JButton("ACEPTAR");
        noButton = new JButton("CANCELAR");
        modeKeyValue = new JTextField(5);
        modeKey = new JLabel("Palabra Clave");

        // adjust size and set layout
        setPreferredSize(new Dimension(296, 213));
        setLayout(null);

        // add components
        add(modeSelector);
        add(modoFiltrado);
        add(yesButton);
        add(noButton);
        add(modeKeyValue);
        add(modeKey);

        // set component bounds (only needed by Absolute Positioning)
        modeSelector.setBounds(30, 35, 133, 25);
        modoFiltrado.setBounds(30, 10, 100, 25);
        yesButton.setBounds(30, 145, 100, 25);
        noButton.setBounds(160, 145, 100, 25);
        modeKeyValue.setBounds(30, 90, 100, 25);
        modeKey.setBounds(30, 65, 100, 25);
        // set button actions

        yesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (modeKey.getText() == "") {
                        throw new GUIException("Domicilio obligatorio.");
                    }
                    String modo = String.valueOf(modeSelector.getSelectedItem());
                    switch (modo) {
                        case "Calle":
                            UserWindow.TIPOFILTRADO = "st";
                            break;
                        case "Ciudad":
                            UserWindow.TIPOFILTRADO = "c";
                            break;
                        case "Código Postal":
                            UserWindow.TIPOFILTRADO = "cp";
                            break;
                        default:
                            throw new GUIException("Algo salió mal. Contacte con el soporte.");
                    }
                    UserWindow.DOMICILIO = modeKeyValue.getText();
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
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Modo de filtrado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FiltrarGUI());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
