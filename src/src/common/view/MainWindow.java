package common.view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.Controller;

/**
 * Ventana principal del programa
 * 
 * @see JFrame
 */
public class MainWindow extends JFrame {
    private Controller _controller;

    /**
     * Constructor de la ventana principal del programa
     * 
     * @param c controller del programa
     */
    public MainWindow(Controller c) {
        setTitle("Banqueo");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        _controller = c;
        this.setContentPane(mainPanel);
        mainPanel.add(new ControlPanel(_controller), BorderLayout.PAGE_START);
        try {
            mainPanel.add(new ImagePanel(), BorderLayout.PAGE_END);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen de Banqueo");
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
