package subscuentas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

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
import subscuentas.FachadaSubsCuentas;
import subscuentas.IFachadaSubsCuentas;

public class CuentaListPanel extends JPanel {

    private JButton modButton;
    private JButton cancelButton;
    private JPanel tablaCuentas;

    private JLabel dniAModificar;
    private JTextField dniLabel;

    private JLabel titularAModificar;
    private JTextField titularText;

    public CuentaListPanel() throws FileNotFoundException {
        // construct components
        modButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");

        tablaCuentas = createViewPanel(new JTable(new CuentaTableModel()), "Lista de cuentas");

        dniAModificar = new JLabel("DNI");
        dniLabel = new JTextField(5);
        titularAModificar = new JLabel("Titular");
        titularText = new JTextField(5);
        // adjust size and set layout
        setPreferredSize(new Dimension(397, 574));
        setLayout(null);

        // add components
        add(modButton);
        add(cancelButton);
        add(tablaCuentas);
        add(dniAModificar);
        add(dniLabel);
        add(titularAModificar);
        add(titularText);

        // set component bounds (only needed by Absolute Positioning)
        modButton.setBounds(85, 15, 100, 35);
        cancelButton.setBounds(210, 15, 100, 35);
        tablaCuentas.setBounds(25, 120, 350, 259);
        dniAModificar.setBounds(25, 380, 100, 25);
        dniLabel.setBounds(25, 405, 170, 35);
        titularAModificar.setBounds(45, 120, 350, 259);
        titularText.setBounds(45, 120, 350, 259); // TODO : Comprobar que esto este bien, y tambien lo del nombre en el
                                                  // consulta de sadao

        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!dniLabel.getText().equals("") && !titularText.getText().equals("")) {
                        IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas(); // TODO : esto ha sido modificado
                                                                                    // respecto al usuarioListPanel
                        subsCuentas.consultarListaCuentas(titularText.getText(), dniLabel.getText()); // TODO : no estoy
                                                                                                      // seguro
                    } else {
                        throw new GUIException("DNI vacio, introduzca un valor");
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
