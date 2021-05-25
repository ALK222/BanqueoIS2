package subsusuarios.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
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
import dominio.Persona;

/**
 * Panel para escoger el usuario a modificar
 * 
 * @see JPanel
 */
public class UserListPanel extends JPanel {
    private JButton modButton;
    private JButton cancelButton;
    JPanel tablaUsuarios;

    private JLabel dniAModificar;
    private JTextField dniLabel;

    /**
     * Constructor de UserList panel
     * 
     * @param listaFiltrada una lista ya filtrada o null si se quiere buscar toda la
     *                      losta
     * @throws FileNotFoundException Si no se puede contactar con la base de datos
     *                               en caso de que no se le de una lista filtrada
     */
    public UserListPanel(List<Persona> listaFiltrada) throws FileNotFoundException {

        // construct components
        modButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");
        JTable uJTable;
        if (listaFiltrada == null) {
            uJTable = new JTable(new UserTableModel());
        } else {
            uJTable = new JTable(new UserTableModel(listaFiltrada));
        }
        uJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane aux = new JScrollPane(uJTable);
        aux.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        aux.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        aux.setPreferredSize(new Dimension(600, 300));
        tablaUsuarios = createViewPanel(aux, "Lista de usuarios");
        tablaUsuarios.setPreferredSize(new Dimension(700, 400));
        dniAModificar = new JLabel("DNI: ");
        dniLabel = new JTextField();
        Dimension dimensionLabel = new Dimension(100, 25);
        dniLabel.setPreferredSize(dimensionLabel);

        // adjust size and set layout
        setPreferredSize(new Dimension(700, 530));
        // setLayout(null);

        /*
         * // add components add(tablaUsuarios); add(dniAModificar); add(dniLabel);
         * add(modButton); add(cancelButton);
         */

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelAux = new JPanel(new FlowLayout());
        JPanel panelAux2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(tablaUsuarios, BorderLayout.NORTH);
        panelAux2.add(dniAModificar);
        panelAux2.add(dniLabel);
        panel.add(panelAux2, BorderLayout.CENTER);
        panelAux.add(modButton);
        panelAux.add(cancelButton);
        panel.add(panelAux, BorderLayout.SOUTH);

        add(panel);

        /*
         * // set component bounds (only needed by Absolute Positioning)
         * modButton.setBounds(85, 15, 100, 35); cancelButton.setBounds(210, 15, 100,
         * 35); tablaUsuarios.setBounds(25, 120, 350, 259); dniAModificar.setBounds(25,
         * 380, 100, 25); dniLabel.setBounds(25, 405, 170, 35);
         */

        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!dniLabel.getText().equals("")) {
                        UserWindow.DNI = dniLabel.getText();
                        quit();
                    } else {
                        throw new GUIException("DNI vacío, introduzca un valor");
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

    /**
     * Crea un panel con un componente y un titulo
     * 
     * @param c     Componente a poner en el panel
     * @param title Título del panel
     * @return el panel ya formado
     * @see JPanel
     */
    private JPanel createViewPanel(JComponent c, String title) {
        JPanel p = new JPanel(new BorderLayout());
        Border b = BorderFactory.createLineBorder(Color.black, 2);

        p.setBorder(BorderFactory.createTitledBorder(b, title, TitledBorder.LEFT, TitledBorder.TOP));

        p.add(new JScrollPane(c, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        return p;
    }

    /**
     * Comportamiento al cerrar la ventana
     */
    private void quit() {
        JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public static void main(String[] args) throws FileNotFoundException {
        JDialog frame = new JDialog();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new UserListPanel(null));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
