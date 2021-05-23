package common.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import common.Controller;
import subsusuarios.view.UserWindow;

public class MainWindow extends JPanel {
    private Controller _controller;

    public MainWindow(Controller c) {

        _controller = c;
        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Usuario", new UserWindow(c.isAdmin(), c.getCurrentUser()));

        this.add(tabbedPane, BorderLayout.NORTH);
    }
}
