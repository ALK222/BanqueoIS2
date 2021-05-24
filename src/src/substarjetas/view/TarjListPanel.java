package substarjetas.view;

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
import dominio.Tarjeta;
import subsusuarios.view.UserListPanel;
import subsusuarios.view.UserWindow;

public class TarjListPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton modButton;
    private JButton cancelButton;
    JPanel tablaTarjetas;

    private JLabel numTarjetaAModificar;
    private JTextField numTarj;

    public TarjListPanel(List<Tarjeta> listaFiltrada) throws FileNotFoundException {

        // construct components
        modButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");
        if (listaFiltrada == null) {
            tablaTarjetas = createViewPanel(new JTable(new TarjTableModel()), "Lista de tarjetas");
        } else {
        	tablaTarjetas = createViewPanel(new JTable(new TarjTableModel(listaFiltrada)), "Lista de tarjetas");
        }
        numTarjetaAModificar = new JLabel("NUM TARJETA");
        numTarj = new JTextField(5);
        // adjust size and set layout
        setPreferredSize(new Dimension(397, 574));
        setLayout(null);

        // add components
        add(modButton);
        add(cancelButton);
        add(tablaTarjetas);
        add(numTarjetaAModificar);
        add(numTarj);

        // set component bounds (only needed by Absolute Positioning)
        modButton.setBounds(85, 15, 100, 35);
        cancelButton.setBounds(210, 15, 100, 35);
        tablaTarjetas.setBounds(25, 120, 350, 259);
        numTarjetaAModificar.setBounds(25, 380, 100, 25);
        numTarj.setBounds(25, 405, 170, 35);

        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (numTarj.getText() != "") {
                        UserWindow.DNI = numTarj.getText();
                        quit();
                    } else {
                        throw new GUIException("Tarjeta vacio, introduzca un valor");
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

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new UserListPanel(null));
        frame.pack();
        frame.setVisible(true);
    }
}

