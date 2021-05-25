package substarjetas.view;

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

/**
 * Panel para escoger la tarjeta a modificar
 * 
 * @see JPanel
 */
public class TarjListPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JButton modButton;
    private JButton cancelButton;
    JPanel tablaTarjetas;

    private JLabel numTarjetaAModificar;
    private JTextField numTarj;

    /**
     * Constructor de TarjListPanel
     * 
     * @param listaFiltrada una lista ya filtrada o null si se quiere buscar toda la
     *                      lista
     * @throws FileNotFoundException Si no se puede contactar con la base de datos
     *                               en caso de que no se le de una lista filtrada
     */
    public TarjListPanel(List<Tarjeta> listaFiltrada) throws FileNotFoundException {

        // construct components
        modButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");
        if (listaFiltrada == null) {
            tablaTarjetas = createViewPanel(new JTable(new TarjTableModel()), "Lista de tarjetas");
        } else {
            tablaTarjetas = createViewPanel(new JTable(new TarjTableModel(listaFiltrada)), "Lista de tarjetas");
        }
        numTarjetaAModificar = new JLabel("NUM TARJETA: ");
        numTarj = new JTextField();
        Dimension dimensionLabel = new Dimension(200, 25);
        numTarj.setPreferredSize(dimensionLabel);
        // adjust size and set layout
        setPreferredSize(new Dimension(500, 530));

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelAux = new JPanel(new FlowLayout());
        JPanel panelAux2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(tablaTarjetas, BorderLayout.NORTH);
        panelAux2.add(numTarjetaAModificar);
        panelAux2.add(numTarj);
        panel.add(panelAux2, BorderLayout.CENTER);
        panelAux.add(modButton);
        panelAux.add(cancelButton);
        panel.add(panelAux, BorderLayout.SOUTH);

        add(panel);

        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (numTarj.getText() != "") {
                        TarjWindow.NUMTARJETA = numTarj.getText();
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
     * Comportamiento de la ventana al cerrarse
     */
    private void quit() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TarjListPanel(null));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
