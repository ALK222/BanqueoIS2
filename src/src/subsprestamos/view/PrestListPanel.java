package subsprestamos.view;

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
import dominio.Prestamo;
import dominio.Tarjeta;
import substarjetas.view.TarjListPanel;
import substarjetas.view.TarjTableModel;
import substarjetas.view.TarjWindow;

public class PrestListPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton modButton;
    private JButton cancelButton;
    JPanel tablaPrestamos;

    private JLabel numPrestamoAModificar;
    private JTextField numPrest;

    public PrestListPanel(List<Prestamo> listaFiltrada) throws FileNotFoundException {

        // construct components
        modButton = new JButton("ACEPTAR");
        cancelButton = new JButton("CANCELAR");
        if (listaFiltrada == null) {
        	tablaPrestamos = createViewPanel(new JTable(new PrestTableModel()), "Lista de préstamos");
        } else {
        	tablaPrestamos = createViewPanel(new JTable(new PrestTableModel(listaFiltrada)), "Lista de préstamos");
        }
        numPrestamoAModificar = new JLabel("NÚM PRÉSTAMO: ");
        numPrest = new JTextField();
        Dimension dimensionLabel = new Dimension (100,25);
        numPrest.setPreferredSize(dimensionLabel);
        // adjust size and set layout
        setPreferredSize(new Dimension(500, 530));


        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelAux = new JPanel(new FlowLayout());
        JPanel panelAux2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        panel.add(tablaPrestamos, BorderLayout.NORTH);
        panelAux2.add(numPrestamoAModificar);
        panelAux2.add(numPrest);
        panel.add(panelAux2, BorderLayout.CENTER);
        panelAux.add(modButton);
        panelAux.add(cancelButton);
        panel.add(panelAux, BorderLayout.SOUTH);
        
        add(panel);
        
        
        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (numPrest.getText() != "") {
                        PrestWindow.NUMREFERENCIA = numPrest.getText();
                        quit();
                    } else {
                        throw new GUIException("Prestamo vacio, introduzca un valor");
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
        frame.getContentPane().add(new PrestListPanel(null));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

