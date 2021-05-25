package substarjetas.view;

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

import common.exception.GUIException;
import common.exception.UserException;
import common.misc.Pair;
import dominio.Persona;
import dominio.Tarjeta;
import substarjetas.FachadaSubsTarjetas;
import substarjetas.IFachadaSubsTarjetas;

/**
 * Ventana principal para la GUI de gestión de tarjetas
 * 
 * @see JDialog
 */
public class TarjWindow extends JDialog {

    private static final long serialVersionUID = 1L;

    private boolean _permisosGestor;
    private Persona _user;
    private static Dimension tamanoBoton = new Dimension(200, 50);

    public static String DNI = "";
    public static String TIPOFILTRADO = "";
    public static String NUMTARJETA = "";

    /**
     * Constructor para la GUI de tarjetas
     * 
     * @param permisosGestor Activa o no las funciones de gestor
     * @param p              Usuario actual del programa
     */
    public TarjWindow(boolean permisosGestor, Persona p) {
        super();
        this.setTitle("Tarjetas");
        _permisosGestor = permisosGestor;
        _user = p;
        initGUI();
    }

    /**
     * Creador de la ventana principal de la GUI de tarjetas
     */
    private void initGUI() {

        setModal(true);
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
        this.setLocationRelativeTo(null);
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
        JButton altaButton = new JButton("Alta Tarjeta");
        altaButton.setToolTipText("Alta de tarjeta, solo disponible para gestores");
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog frame = new JDialog();
                frame.setTitle("Alta tarjeta");
                frame.setModal(true);
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
        JButton bajaButton = new JButton("Baja tarjeta");
        bajaButton.setToolTipText("Baja de tarjeta, solo disponible para gestores");
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog frame1 = new JDialog();
                frame1.setTitle("Lista Tarjetas");
                frame1.setModal(true);
                try {
                    frame1.getContentPane().add(new TarjListPanel(null));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo abrir el archivo de tarjetas. Contacte con el soporte.");
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
                        IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
                        Pair<Integer, Tarjeta> aux = subsTarjetas.buscaTarjeta(Integer.parseInt(NUMTARJETA));
                        int decision = JOptionPane.showConfirmDialog(null,
                                "¿Dar de baja al tarjeta " + NUMTARJETA + "?", "Baja usuario",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        int resultado = 1;
                        try {
                            if (decision == 0) {
                                resultado = subsTarjetas.bajaTarjeta(aux.getSecond().getNum_tarjeta());

                                switch (resultado) {
                                    case 0:
                                        JOptionPane.showMessageDialog(null, "Tarjeta eliminada correctamente");
                                        quit();
                                        break;
                                    case 1:
                                        throw new UserException(
                                                "Fallo al eliminar la tarjeta. Compruebe que no exista ya");
                                    case 10:
                                        throw new GUIException(
                                                "Fallo al encontrar el archivo de tarjetas. Contacte con el soporte.");
                                    default:
                                        throw new GUIException("Error inesperado. Contacte con el soporte");
                                }
                            }

                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage());
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
        JButton modificacionButton = new JButton("Modificación tarjeta");
        modificacionButton.setToolTipText("Modificación de tarjeta, solo disponible para gestores");
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
        frame1.setTitle("Lista Tarjetas");
        frame1.setModal(true);
        try {
            frame1.getContentPane().add(new TarjListPanel(null));
            frame1.pack();
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        } catch (FileNotFoundException e1) {
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
                if (!NUMTARJETA.equals("")) {
                    JDialog frame = new JDialog();
                    frame.setTitle("Modicifación Tarjeta");
                    frame.setModal(true);
                    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
                    Pair<Integer, Tarjeta> aux = subsTarjetas.buscaTarjeta(Integer.parseInt(NUMTARJETA));
                    frame.getContentPane().add(new ModGUI(aux.getSecond(), true));
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    NUMTARJETA = "";
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
     * Creador de la ventana de modificación de tarjetas para usuarios
     */
    private void createUserModFrame() {
        JDialog frame1 = new JDialog();
        frame1.setTitle("Lista Tarjetas");
        frame1.setModal(true);
        try {
            IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
            Pair<List<Tarjeta>, Integer> aux1 = subsTarjetas.consultarListaTarjetas(_user.getDni());
            switch (aux1.getSecond()) {
                case 0:
                    frame1.getContentPane().add(new TarjListPanel(aux1.getFirst()));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                    break;
                case 1:
                    throw new GUIException("El usuario no tiene tarjetas a su nombre");
                case 2:
                    throw new GUIException("No se reconoce al usuario.");
                case 10:
                    throw new GUIException("No se pudo conectar con la base de datos. Contacte con el soporte.");
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
                if (!NUMTARJETA.equals("")) {
                    JDialog frame = new JDialog();
                    frame.setTitle("Modicifación Tarjeta");
                    frame.setModal(true);
                    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
                    Pair<Integer, Tarjeta> aux = subsTarjetas.buscaTarjeta(Integer.parseInt(NUMTARJETA));
                    frame.getContentPane().add(new ModGUI(aux.getSecond(), false));

                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    NUMTARJETA = "";
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
     * Método para crear el botón de consulta de listas
     * 
     * @param aux barra a la que añadir el botón
     */
    private void createListaButton(JToolBar aux) {
        JButton listaButton = new JButton("Lista de tarjetas");
        listaButton.setToolTipText("Lista de tarjetas, solo disponible para gestores");
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
     * Comportamiento para la lista de tarjetas cuando la usa un gestor
     */
    private void createGestorListFrame() {
        JDialog frame = new JDialog();
        frame.setTitle("Opciones de Filtrado");
        frame.setModal(true);
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
                if (!DNI.equals("")) {
                    try {
                        IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
                        Pair<List<Tarjeta>, Integer> listaFiltrada = subsTarjetas.consultarListaTarjetas(DNI);
                        int resultado = listaFiltrada.getSecond();
                        switch (resultado) {
                            case 0:
                                break;
                            case 1:
                                throw new UserException("El usuario no tiene tarjetas a su nombre.");
                            case 10:
                                throw new GUIException(
                                        "Fallo al encontrar el archivo de tarjetas. Contacte con el soporte.");
                            default:
                                throw new GUIException("Error inesperado. Contacte con el soporte");
                        }
                        JDialog frame1 = new JDialog();
                        frame1.setTitle("Lista Tarjetas");
                        frame1.setModal(true);
                        frame1.getContentPane().add(new TarjListPanel(listaFiltrada.getFirst()));
                        frame1.pack();
                        frame1.setVisible(true);
                        frame1.setLocationRelativeTo(null);
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
                                "No se pudo abrir el archivo de tarjetas. Contacte con el soporte.");
                    }
                    DNI.equals("");
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
     * Comportamiento para la lista de tarjetas cuando la usa un cliente
     */
    private void createUserListFrame() {

        try {
            IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
            Pair<List<Tarjeta>, Integer> listaFiltrada = subsTarjetas.consultarListaTarjetas(_user.getDni());
            int resultado = listaFiltrada.getSecond();
            switch (resultado) {
                case 0:
                    JDialog frame1 = new JDialog();
                    frame1.setTitle("Lista Tarjetas");
                    frame1.setModal(true);
                    frame1.getContentPane().add(new TarjListPanel(listaFiltrada.getFirst()));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                    break;
                case 1:
                    throw new UserException("El usuario no tiene tarjetas.");
                case 10:
                    throw new GUIException("Fallo al encontrar el archivo de tarjetas. Contacte con el soporte.");
                default:
                    throw new GUIException("Error inesperado. Contacte con el soporte");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}
