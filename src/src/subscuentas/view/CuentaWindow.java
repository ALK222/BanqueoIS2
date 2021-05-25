package subscuentas.view;

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
import javax.swing.JPanel;
import javax.swing.JToolBar;

import common.exception.CuentaException;
import common.exception.GUIException;
import common.misc.Pair;
import dominio.Cuenta;
import dominio.Persona;
import subscuentas.model.FachadaSubsCuentas;
import subscuentas.model.IFachadaSubsCuentas;

/**
 * Ventana principal de la GUI de gestión de cuentas
 * 
 * @see JDialog
 */
public class CuentaWindow extends JDialog {

    private boolean _permisosGestor;
    private Persona _currentUser;
    private static Dimension tamanoBoton = new Dimension(200, 50);

    public static String DNI = "";
    public static String NUM_CUENTA = "";
    public static String TITULAR_CUENTA = "";

    /**
     * Constructor para la GUI de cuentas
     * 
     * @param permisosGestor Activa o no las funciones de gestor
     * @param p              Usuario actual del programa
     */
    public CuentaWindow(boolean permisosGestor, Persona p) {
        // super("Cuenta");
        _permisosGestor = permisosGestor;
        _currentUser = p;
        initGUI();
    }

    /**
     * Creador de la ventana principal de la GUI de cuentas
     */
    private void initGUI() {
        setModal(true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
        setTitle("Control de cuentas");
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

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    /**
     * Comportamiento de la ventana al cerrarse
     */
    private void quit() {
        this.dispose();
    }

    /**
     * Método para crear el botón de alta de cuentas
     * 
     * @param aux Barra a la que añadir el botón
     */
    private void createAltaButton(JToolBar aux) {
        JButton altaButton = new JButton("Alta cuenta");
        altaButton.setToolTipText("Alta de cuenta, solo disponible para gestores");
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog frame = new JDialog();
                frame.setTitle("Alta cuenta");
                frame.setModal(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new AltaGUI());
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        altaButton.setEnabled(_permisosGestor);
        altaButton.setPreferredSize(tamanoBoton);
        aux.add(altaButton, BorderLayout.NORTH);
    }

    /**
     * Método para crear el botón de baja de cuentas
     * 
     * @param aux Barra a la que añadir el botón
     */
    private void createBajaButton(JToolBar aux) {
        JButton bajaButton = new JButton("Baja cuenta");
        bajaButton.setToolTipText("Baja de cuenta, solo disponible para gestores");
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog frame1 = new JDialog();
                frame1.setTitle("Lista Cuentas");
                frame1.setModal(true);
                frame1.setResizable(false);
                try {
                    frame1.getContentPane().add(new CuentaListPanel(null));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
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
                        if (!NUM_CUENTA.equals("")) {

                            IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                            Cuenta aux = subsCuentas.buscaCuenta(Integer.parseInt(NUM_CUENTA)).getFirst();
                            int decision = JOptionPane.showConfirmDialog(null,
                                    "¿Dar de baja a la cuenta " + NUM_CUENTA + "?", "Baja cuenta",
                                    JOptionPane.YES_NO_CANCEL_OPTION);
                            int resultado = 1;
                            if (decision != 2) {
                                try {

                                    if (decision == 0) {
                                        resultado = subsCuentas.bajaCuenta(aux);
                                    }
                                    switch (resultado) {
                                        case 0:
                                            JOptionPane.showMessageDialog(null, "Cuenta eliminada correctamente");
                                            quit();
                                            break;
                                        case 1:
                                            throw new CuentaException(
                                                    "Fallo al eliminar la cuenta. Compruebe que no exista ya");
                                        case 10:
                                            throw new GUIException(
                                                    "Fallo al encontrar el archivo de cuentas. Contacte con el soporte.");
                                        default:
                                            throw new GUIException("Error inesperado. Contacte con el soporte");
                                    }
                                } catch (Exception e1) {
                                    JOptionPane.showMessageDialog(null, e1.getMessage());
                                }
                            }
                            NUM_CUENTA = "";
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
        bajaButton.setEnabled(_permisosGestor);
        bajaButton.setPreferredSize(tamanoBoton);
        aux.add(bajaButton, BorderLayout.SOUTH);
    }

    /**
     * Método para crear el botón de modificación de cuentas
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createModificarButton(JToolBar aux) {
        JButton modificacionButton = new JButton("Modificación cuenta");
        modificacionButton.setToolTipText("Modificación de cuenta, solo disponible para gestores");
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
     * Comportamienteo de la ventana de modicicación para gestores
     */
    private void createGestorModFrame() {
        JDialog frame1 = new JDialog();
        frame1.setTitle("Lista Cuentas");
        frame1.setModal(true);
        frame1.setResizable(false);
        try {
            frame1.getContentPane().add(new CuentaListPanel(null));
            frame1.pack();
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
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
                if (!NUM_CUENTA.equals("")) {
                    JDialog frame = new JDialog();
                    frame.setTitle("Modicifacion Cuenta");
                    frame.setModal(true);
                    frame.setResizable(false);
                    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                    Cuenta aux = subsCuentas.buscaCuenta(Integer.parseInt(NUM_CUENTA)).getFirst();
                    frame.getContentPane().add(new ModGUI(aux));

                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    NUM_CUENTA = "";
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
        JDialog frame1 = new JDialog();
        frame1.setTitle("Lista Cuentas");
        frame1.setModal(true);
        frame1.setResizable(false);
        try {
            IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
            Pair<List<Cuenta>, Integer> listaFiltrada = subsCuentas.consultarListaCuentas(_currentUser.getNombre(),
                    _currentUser.getDni());
            switch (listaFiltrada.getSecond()) {
                case 0:
                    frame1.getContentPane().add(new CuentaListPanel(listaFiltrada.getFirst()));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                    break;
                case 1:
                    throw new CuentaException("No se encuentra ninguna cuenta para el usuario actual.");
                case 10:
                    throw new FileNotFoundException("No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
                default:
                    throw new GUIException("Error desconocido. Contacte con el soporte");
            }

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
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
                if (!NUM_CUENTA.equals("")) {
                    JDialog frame = new JDialog();
                    frame.setTitle("Modificacion Cuenta");
                    frame.setModal(true);
                    frame.setResizable(true);
                    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                    Cuenta aux = subsCuentas.buscaCuenta(Integer.parseInt(NUM_CUENTA)).getFirst();
                    frame.getContentPane().add(new ModGUI(aux));

                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    NUM_CUENTA = "";
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
     * Método para crear el botón de listas de cuentas
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createListaButton(JToolBar aux) {
        JButton listaButton = new JButton("Lista de cuentas");
        listaButton.setToolTipText("Lista de cuentas, solo disponible para gestores");
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_permisosGestor) {
                    createGestorListFrame();
                } else {
                    createUserListFrame();
                }
            }
        });
        listaButton.setEnabled(true);
        listaButton.setPreferredSize(tamanoBoton);
        aux.add(listaButton, BorderLayout.SOUTH);
    }

    /**
     * Creador de la ventana de Listas para gestores
     */
    private void createGestorListFrame() {
        JDialog frame = new JDialog();
        frame.setTitle("Opciones de filtrado");
        frame.setModal(true);
        frame.setResizable(false);
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
                if (!TITULAR_CUENTA.equals("") && !DNI.equals("")) {
                    try {
                        IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                        Pair<List<Cuenta>, Integer> listaFiltrada = subsCuentas.consultarListaCuentas(TITULAR_CUENTA,
                                DNI);
                        int resultado = listaFiltrada.getSecond();
                        switch (resultado) {
                            case 0:
                                JDialog frame1 = new JDialog();
                                frame1.setTitle("Lista cuentas");
                                frame1.setModal(true);
                                frame1.setResizable(false);
                                frame1.getContentPane().add(new CuentaListPanel(listaFiltrada.getFirst()));
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
                                break;
                            case 1:
                                throw new CuentaException("Fallo al modificar la cuenta. Compruebe que no exista ya");
                            case 10:
                                throw new GUIException(
                                        "Fallo al encontrar el archivo de cuentas. Contacte con el soporte.");
                            default:
                                throw new GUIException("Error inesperado. Contacte con el soporte");
                        }

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null,
                                "No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
                    }
                    TITULAR_CUENTA = "";
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
     * Creador de la ventana de Listas para usuarios
     */
    private void createUserListFrame() {
        try {
            IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
            Pair<List<Cuenta>, Integer> listaFiltrada = subsCuentas.consultarListaCuentas(_currentUser.getNombre(),
                    _currentUser.getDni());
            int resultado = listaFiltrada.getSecond();
            switch (resultado) {
                case 0:
                    JDialog frame1 = new JDialog();
                    frame1.setTitle("Lista cuentas");
                    frame1.setModal(true);
                    frame1.setResizable(false);
                    frame1.getContentPane().add(new CuentaListPanel(listaFiltrada.getFirst()));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                    break;
                case 1:
                    throw new CuentaException("Fallo al modificar la cuenta. Compruebe que no exista ya");
                case 10:
                    throw new GUIException("Fallo al encontrar el archivo de cuentas. Contacte con el soporte.");
                default:
                    throw new GUIException("Error inesperado. Contacte con el soporte");
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
        }
    }
}
