package subsusuarios.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import dominio.Persona;
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;

public class UserWindow extends JFrame {

    private boolean _permisosGestor;
    private Persona _user;
    private static Dimension tamanoBoton = new Dimension(200, 50);

    public static String DNI = "";

    public UserWindow(boolean permisosGestor) {
        super("Usuario");
        _permisosGestor = permisosGestor;
        initGUI();
    }

    private void initGUI() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);

        // Buttons
        JToolBar botonesAltaBaja = new JToolBar();
        botonesAltaBaja.setLayout(new BorderLayout());
        botonesAltaBaja.setFloatable(false);
        createAltaButton(botonesAltaBaja);
        createBajaButton(botonesAltaBaja);
        this.add(botonesAltaBaja, BorderLayout.EAST);

        JToolBar botonesListaModificar = new JToolBar();
        botonesListaModificar.setLayout(new BorderLayout());
        botonesListaModificar.setFloatable(false);
        createModificarButton(botonesListaModificar);
        createListaButton(botonesListaModificar);
        this.add(botonesListaModificar, BorderLayout.WEST);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                quit();

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
            }
        });
    }

    private void quit() {
        this.dispose();
    }

    private void createAltaButton(JToolBar aux) {
        JButton altaButton = new JButton("Alta usuario");
        altaButton.setToolTipText("Alta de usuario, solo disponible para gestores");
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Alta Usuario");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new AltaGUI());
                frame.pack();
                frame.setVisible(true);
            }
        });
        altaButton.setEnabled(_permisosGestor);
        altaButton.setPreferredSize(tamanoBoton);
        aux.add(altaButton, BorderLayout.NORTH);
    }

    private void createBajaButton(JToolBar aux) {
        JButton bajaButton = new JButton("Baja usuario");
        bajaButton.setToolTipText("Baja de usuario, solo disponible para gestores");
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // BajaGUI baja = new BajaGUI();
            }
        });
        bajaButton.setEnabled(_permisosGestor);
        bajaButton.setPreferredSize(tamanoBoton);
        aux.add(bajaButton, BorderLayout.SOUTH);
    }

    private void createModificarButton(JToolBar aux) {
        JButton modificacionButton = new JButton("Modificación usuario");
        modificacionButton.setToolTipText("Modificación de usuario, solo disponible para gestores");
        modificacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (_permisosGestor) {
                    JFrame frame1 = new JFrame("Lista Usuarios");
                    frame1.addWindowListener(new WindowListener() {

                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            JFrame frame = new JFrame("Modicifacion Usuario");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            IFachadaSubsUsuarios subsUsuarios = new FachadaSubsUsuarios();
                            Persona aux = subsUsuarios.buscarUsuario(DNI).getFirst();
                            frame.getContentPane().add(new ModGUI(aux));

                            frame.pack();
                            frame.setVisible(true);
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {
                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {
                        }

                        @Override
                        public void windowActivated(WindowEvent e) {
                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {
                        }

                    });
                    try {
                        frame1.getContentPane().add(new UserListPanel());
                        frame1.pack();
                        frame1.setVisible(true);
                    } catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(null,
                                "No se pudo abrir el archivo de usuarios. Contacte con el soporte.");
                    }
                } else {
                    JFrame frame = new JFrame("Modicifacion Usuario");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new ModGUI(_user));

                    frame.pack();
                    frame.setVisible(true);
                }

            }
        });
        modificacionButton.setEnabled(true);
        modificacionButton.setPreferredSize(tamanoBoton);
        aux.add(modificacionButton, BorderLayout.NORTH);

    }

    private void createListaButton(JToolBar aux) {
        JButton listaButton = new JButton("Lista de usuarios");
        listaButton.setToolTipText("Lista de usuarios, solo disponible para gestores");
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ListaGUI lista = new ListaGUI
            }
        });
        listaButton.setEnabled(_permisosGestor);
        listaButton.setPreferredSize(tamanoBoton);
        aux.add(listaButton, BorderLayout.SOUTH);
    }
}
