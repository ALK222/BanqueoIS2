package subsusuarios.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import common.exception.GUIException;
import common.exception.UserException;
import common.misc.Pair;
import dominio.Persona;
import subsusuarios.model.FachadaSubsUsuarios;
import subsusuarios.model.IFachadaSubsUsuarios;

/**
 * Ventana principal de la GUI de gestión de usuarios
 * 
 * @see JDialog
 */
public class UserWindow extends JDialog {

    private boolean _permisosGestor;
    private Persona _user;
    private static Dimension tamanoBoton = new Dimension(200, 50);

    public static String DNI = "";
    public static String TIPOFILTRADO = "";
    public static String DOMICILIO = "";

    /**
     * Constructor principal de UserWindow
     * 
     * @param permisosGestor Activa o no las funciones de gestor
     * @param persona        Usuario en uso
     */
    public UserWindow(boolean permisosGestor, Persona persona) {
        _permisosGestor = permisosGestor;
        _user = persona;
        initGUI();
    }

    /**
     * Comportamiento de la ventana al salir
     */
    private void quit() {
        this.dispose();
    }

    /**
     * Constructor de la parte gráfica de la ventana
     */
    private void initGUI() {
        setModal(true);
        setTitle("Control de usuarios");
        // Buttons
        JToolBar botonesAltaBaja = new JToolBar();
        botonesAltaBaja.setLayout(new BorderLayout());
        botonesAltaBaja.setFloatable(false);
        createAltaButton(botonesAltaBaja);
        createBajaButton(botonesAltaBaja);
        this.add(botonesAltaBaja, BorderLayout.EAST);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JToolBar botonesListaModificar = new JToolBar();
        botonesListaModificar.setLayout(new BorderLayout());
        botonesListaModificar.setFloatable(false);
        createModificarButton(botonesListaModificar);
        createListaButton(botonesListaModificar);
        this.add(botonesListaModificar, BorderLayout.WEST);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Metodo para crear el botón de alta junto a su comportamiento
     * 
     * @param aux Barra donde añadir el botón
     */
    private void createAltaButton(JToolBar aux) {
        JButton altaButton = new JButton("Alta usuario");
        altaButton.setToolTipText("Alta de usuario, solo disponible para gestores");
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAltaFrame();
            }
        });
        altaButton.setEnabled(_permisosGestor);
        altaButton.setPreferredSize(tamanoBoton);
        aux.add(altaButton, BorderLayout.NORTH);
    }

    /**
     * Creador de la ventana de alta de usuarios
     */
    private void createAltaFrame() {
        JDialog frame = new JDialog();
        frame.setModal(true);
        frame.setResizable(false);
        frame.setTitle("Alta usuario");
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new AltaGUI());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Metodo para crear el botón de baja junto a su comportamiento
     * 
     * @param aux Barra donde añadir el botón
     */
    private void createBajaButton(JToolBar aux) {
        JButton bajaButton = new JButton("Baja usuario");
        bajaButton.setToolTipText("Baja de usuario, solo disponible para gestores");
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBajaFrame();
            }
        });
        bajaButton.setEnabled(_permisosGestor);
        bajaButton.setPreferredSize(tamanoBoton);
        aux.add(bajaButton, BorderLayout.SOUTH);
    }

    /**
     * Creador de la ventana de baja de usuarios
     */
    private void createBajaFrame() {
        JDialog frame1 = new JDialog();
        frame1.setModal(true);
        frame1.setResizable(false);
        frame1.setTitle("Lista Usuarios");
        try {
            frame1.getContentPane().add(new UserListPanel(null));
            frame1.pack();
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de usuarios. Contacte con el soporte.");
        }
        // COMPORTAMIENTO AL CERRAR LA LISTA
        frame1.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // LANZAR LA BAJA DE USUARIO
                if (!DNI.equals("")) {
                    IFachadaSubsUsuarios subsUsuarios = new FachadaSubsUsuarios();
                    Persona aux = subsUsuarios.buscarUsuario(DNI).getFirst();
                    UIManager.put("OptionPane.noButtonText", "No");
                    UIManager.put("OptionPane.yesButtonText", "Si");
                    int decision = JOptionPane.showConfirmDialog(null, "¿Dar de baja al usuario " + DNI + "?",
                            "Baja usuario", JOptionPane.YES_NO_CANCEL_OPTION);
                    int resultado = 1;
                    try {
                        if (decision == 0) {
                            resultado = subsUsuarios.bajaUsuario(aux);
                            switch (resultado) {
                                case 0:
                                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
                                    quit();
                                    break;
                                case 1:
                                    throw new UserException(
                                            "Fallo al eliminar el usuario. Compruebe que no exista ya.");
                                case 10:
                                    throw new GUIException(
                                            "Fallo al encontrar el archivo de usuarios. Contacte con el soporte.");
                                default:
                                    throw new GUIException("Error inesperado. Contacte con el soporte.");
                            }
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                    DNI = "";
                }

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

    }

    /**
     * Metodo para crear el botón de modificación de usuarios
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createModificarButton(JToolBar aux) {
        JButton modificacionButton = new JButton("Modificación usuario");
        modificacionButton.setToolTipText("Modificación de usuario, solo disponible para gestores");
        modificacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (_permisosGestor) {
                    createGestorModFrame();
                } else {
                    createUserModFrame();
                }

            }
        });
        modificacionButton.setEnabled(true);
        modificacionButton.setPreferredSize(tamanoBoton);
        aux.add(modificacionButton, BorderLayout.NORTH);
    }

    /**
     * Creador de la ventana de modificación para gestores
     */
    private void createGestorModFrame() {
        JDialog frame1 = new JDialog();
        frame1.setModal(true);
        frame1.setResizable(false);
        frame1.setTitle("Lista Usuarios");
        try {
            frame1.getContentPane().add(new UserListPanel(null));
            frame1.pack();
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de usuarios. Contacte con el soporte.");
        }
        frame1.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (!DNI.equals("")) {
                    JDialog frame = new JDialog();
                    frame.setModal(true);
                    frame.setTitle("Modicifacion Usuario");
                    frame.setResizable(false);
                    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    IFachadaSubsUsuarios subsUsuarios = new FachadaSubsUsuarios();
                    Persona aux = subsUsuarios.buscarUsuario(DNI).getFirst();
                    frame.getContentPane().add(new ModGUI(aux));
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    DNI = "";
                }

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
    }

    /**
     * Creador de la ventana de modificación para clientes
     */
    private void createUserModFrame() {
        JDialog frame = new JDialog();
        frame.setModal(true);
        frame.setTitle("Modicifación Usuario");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new ModGUI(_user));

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Metodo para crear el botón de consultar listas de usuarios y su
     * comportamiento
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createListaButton(JToolBar aux) {
        JButton listaButton = new JButton("Lista de usuarios");
        listaButton.setToolTipText("Lista de usuarios, solo disponible para gestores");
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog frame = new JDialog();
                frame.setModal(true);
                frame.setResizable(false);
                frame.setTitle("Opciones de Filtrado");
                frame.getContentPane().add(new FiltrarGUI());
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.addWindowListener(new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent e) {
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        if (!TIPOFILTRADO.equals("") && !DOMICILIO.equals("")) {
                            try {
                                IFachadaSubsUsuarios subsUsuarios = new FachadaSubsUsuarios();
                                Pair<List<Persona>, Integer> listaFiltrada = subsUsuarios
                                        .consultarListaUsuarios(DOMICILIO, TIPOFILTRADO);
                                int resultado = listaFiltrada.getSecond();
                                switch (resultado) {
                                    case 0:
                                        break;
                                    case 1:
                                        throw new UserException(
                                                "Fallo al modificar el usuario. Compruebe que no exista ya");
                                    case 10:
                                        throw new GUIException(
                                                "Fallo al encontrar el archivo de usuarios. Contacte con el soporte.");
                                    default:
                                        throw new GUIException("Error inesperado. Contacte con el soporte");
                                }
                                JDialog frame1 = new JDialog();
                                frame1.setModal(true);
                                frame1.setTitle("Lista Usuarios");
                                frame1.setResizable(false);
                                frame1.getContentPane().add(new UserListPanel(listaFiltrada.getFirst()));
                                frame1.pack();
                                frame1.setVisible(true);
                                frame.setLocationRelativeTo(null);
                                frame1.addWindowListener(new WindowListener() {
                                    @Override
                                    public void windowOpened(WindowEvent e) {
                                    }

                                    @Override
                                    public void windowClosing(WindowEvent e) {
                                    }

                                    @Override
                                    public void windowClosed(WindowEvent e) {

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

                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null,
                                        "No se pudo abrir el archivo de usuarios. Contacte con el soporte.");
                            }
                            TIPOFILTRADO = "";
                            DOMICILIO = "";
                        }
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
            }
        });
        listaButton.setEnabled(_permisosGestor);
        listaButton.setPreferredSize(tamanoBoton);
        aux.add(listaButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new UserWindow(true, null);
    }
}
