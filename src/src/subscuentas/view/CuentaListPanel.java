package subscuentas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import common.exception.GUIException;
import dominio.Cuenta;

public class CuentaListPanel extends JPanel {
    private List<Cuenta> listaFiltrada;

    private JButton modButton;
    private JButton cancelButton;
    private JPanel tablaCuentas;

    private JLabel numRefText;
    private JTextField numRef;

    private JLabel titularAModificar;
    private JTextField titularText;

    public CuentaListPanel(List<Cuenta> listaFiltrada) throws FileNotFoundException {

        this.listaFiltrada = listaFiltrada;

        // construct components
        modButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        if (this.listaFiltrada == null) {
            tablaCuentas = createViewPanel(new JTable(new CuentaTableModel()), "Lista de cuentas");
        } else {
            tablaCuentas = createViewPanel(new JTable(new CuentaTableModel(this.listaFiltrada)), "Lista de cuentas");
        }

        numRefText = new JLabel("Número de referencia");
        numRef = new JTextField(5);
        titularAModificar = new JLabel("Titular");
        titularText = new JTextField(5);
        // adjust size and set layout
        setPreferredSize(new Dimension(397, 574));
        setLayout(null);

        // add components
        add(modButton);
        add(cancelButton);
        add(tablaCuentas);
        add(numRefText);
        add(numRef);
        add(titularAModificar);
        add(titularText);

        // set component bounds (only needed by Absolute Positioning)
        modButton.setBounds(85, 15, 100, 35);
        cancelButton.setBounds(210, 15, 100, 35);
        tablaCuentas.setBounds(25, 120, 350, 259);
        numRefText.setBounds(25, 380, 300, 25);
        numRef.setBounds(25, 405, 170, 35);

        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!numRef.getText().equals("")) {
                        CuentaWindow.NUM_CUENTA = Integer.parseInt(numRef.getText());
                        quit();
                    } else {
                        throw new GUIException("Número de referencia vacío, introduzca un valor.");
                    }
                } catch (GUIException e1) {
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
    }

    private JPanel createViewPanel(JComponent c, String title) {
        JPanel p = new JPanel(new BorderLayout());
        Border b = BorderFactory.createLineBorder(Color.black, 2);

        p.setBorder(BorderFactory.createTitledBorder(b, title, TitledBorder.LEFT, TitledBorder.TOP));

        p.add(new JScrollPane(c, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        return p;
    }

    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
