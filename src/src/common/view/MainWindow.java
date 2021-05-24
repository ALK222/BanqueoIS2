package common.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.Controller;

/**
 * Ventana principal del programa
 */
public class MainWindow extends JFrame {
    private Controller _controller;

    /**
     * Constructor de la ventana principal del programa
     * 
     * @param c controller del programa
     */
    public MainWindow(Controller c) {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        _controller = c;
        this.setContentPane(mainPanel);
        mainPanel.add(new ControlPanel(_controller), BorderLayout.PAGE_START);
        mainPanel.add(new ImagePanel(), BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.setResizable(true);
    }
}
