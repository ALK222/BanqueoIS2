package subsusuarios.view;

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
import dominio.Persona;

/**
 * Panel para escofer el usuario a modificar
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
        if (listaFiltrada == null) {
            tablaUsuarios = createViewPanel(new JTable(new UserTableModel()), "Lista de usuarios");
        } else {
            tablaUsuarios = createViewPanel(new JTable(new UserTableModel(listaFiltrada)), "Lista de usuarios");
        }
        dniAModificar = new JLabel("DNI");
        dniLabel = new JTextField(5);
        // adjust size and set layout
        setPreferredSize(new Dimension(397, 574));
        setLayout(null);

        // add components
        add(tablaUsuarios);
        add(dniAModificar);
        add(dniLabel);
        add(modButton);
        add(cancelButton);

        // set component bounds (only needed by Absolute Positioning)
        modButton.setBounds(85, 15, 100, 35);
        cancelButton.setBounds(210, 15, 100, 35);
        tablaUsuarios.setBounds(25, 120, 350, 259);
        dniAModificar.setBounds(25, 380, 100, 25);
        dniLabel.setBounds(25, 405, 170, 35);

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
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new UserListPanel(null));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
