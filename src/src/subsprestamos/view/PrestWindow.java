package subsprestamos.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import common.exception.CuentaException;
import common.exception.GUIException;
import common.exception.UserException;
import common.misc.Pair;
import dominio.Cuenta;
import dominio.Persona;
import dominio.Prestamo;
import subscuentas.FachadaSubsCuentas;
import subscuentas.IFachadaSubsCuentas;
import subsprestamos.FachadaSubsPrestamos;
import subsprestamos.IFachadaSubsPrestamos;

public class PrestWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private boolean _permisosGestor;
    private Persona _user;
    private static Dimension tamanoBoton = new Dimension(200, 50);

    public static String CUENTAASOCIADA = "";
    public static String TIPOFILTRADO = "";
    public static String NUMREFERENCIA = "";
    public static String NUMCUENTA = "";

    public PrestWindow(boolean permisosGestor, Persona p) {
        _permisosGestor = permisosGestor;
        _user = p;
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
        this.setLocationRelativeTo(null);
        this.setResizable(false);
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
        JButton altaButton = new JButton("Solicitar Prestamo");
        altaButton.setToolTipText("Solicitar prestamo, solo disponible para clientes");
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Solicitar prestamo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new AltaGUI());
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        altaButton.setEnabled(true);
        altaButton.setPreferredSize(tamanoBoton);
        aux.add(altaButton, BorderLayout.NORTH);
    }

    private void createBajaButton(JToolBar aux) {
        JButton bajaButton = new JButton("Aceptar/Cancelar prestamo");
        bajaButton.setToolTipText("Cancelar el prestamo, solo disponible para gestores");
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Lista Prestamos");
                frame1.addWindowListener(new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            IFachadaSubsPrestamos subsPrestamos = new FachadaSubsPrestamos();
                            Pair<Prestamo, Integer> aux = subsPrestamos.buscarPrestamo(Integer.parseInt(NUMREFERENCIA));
                            int resultado = aux.getSecond();
                            switch (resultado) {
                                case 0:
                                    JOptionPane.showMessageDialog(null, "El préstamo no existe.");
                                    quit();
                                    break;
                                case 1:
                                    throw new UserException(
                                            "Fallo al eliminar el préstamo. Compruebe que no exista ya");
                                case 10:
                                    throw new GUIException(
                                            "Fallo al encontrar el archivo de préstamos. Contacte con el soporte.");
                                default:
                                    throw new GUIException("Error inesperado. Contacte con el soporte");
                            }
                            UIManager.put("OptionPane.noButtonText", "Baja");
                            UIManager.put("OptionPane.yesButtonText", "Alta");
                            int decision = JOptionPane.showConfirmDialog(null,
                                    "¿Cancelar prestamo " + NUMREFERENCIA + "?", "Cancelar Prestamo",
                                    JOptionPane.YES_NO_CANCEL_OPTION);

                            if (decision == 1) {
                                resultado = subsPrestamos.cancelarSolicitud(Integer.parseInt(NUMREFERENCIA));
                            } else if (decision == 0) {
                                resultado = subsPrestamos.aceptarSolicitud(Integer.parseInt(NUMREFERENCIA));
                            }

                            switch (resultado) {
                                case 0:
                                    JOptionPane.showMessageDialog(null, "Prestamo cancelado correctamente");
                                    quit();
                                    break;
                                case 1:
                                    throw new UserException(
                                            "Fallo al eliminar el préstamo. Compruebe que no exista ya");
                                case 10:
                                    throw new GUIException(
                                            "Fallo al encontrar el archivo de préstamos. Contacte con el soporte.");
                                default:
                                    throw new GUIException("Error inesperado. Contacte con el soporte");
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
                try {
                    frame1.getContentPane().add(new PrestListPanel(null));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo abrir el archivo de prestamos. Contacte con el soporte.");
                }
            }
        });
        bajaButton.setEnabled(_permisosGestor);
        bajaButton.setPreferredSize(tamanoBoton);
        aux.add(bajaButton, BorderLayout.SOUTH);
    }

    private void createModificarButton(JToolBar aux) {
        JButton modificacionButton = new JButton("Modificación préstamo");
        modificacionButton.setToolTipText("Modificación de préstamo, solo disponible para gestores");
        modificacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGestorModFrame();
            }
        });
        modificacionButton.setEnabled(_permisosGestor);
        modificacionButton.setPreferredSize(tamanoBoton);
        aux.add(modificacionButton, BorderLayout.NORTH);
    }

    private void createGestorModFrame() {
        JFrame frame1 = new JFrame("Lista Prestamos");
        try {
            frame1.getContentPane().add(new PrestListPanel(null));
            frame1.pack();
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de prestamos. Contacte con el soporte.");
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
                JFrame frame = new JFrame("Modicifacion Prestamo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                IFachadaSubsPrestamos subsPrestamos = new FachadaSubsPrestamos();
                Pair<Prestamo, Integer> aux = subsPrestamos.buscarPrestamo(Integer.parseInt(NUMREFERENCIA));
                frame.getContentPane().add(new ModGUI(aux.getFirst()));

                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
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

    private void createListaButton(JToolBar aux) {
        JButton listaButton = new JButton("Lista de prestamos");
        listaButton.setToolTipText("Lista de prestamos, solo disponible para gestores");
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_permisosGestor) {
                    createGestorListView();
                } else {
                    createUserListView();
                }

            }

        });
        listaButton.setEnabled(true);
        listaButton.setPreferredSize(tamanoBoton);
        aux.add(listaButton, BorderLayout.SOUTH);
    }

    private void createGestorListView() {
        JFrame frame1 = new JFrame("Lista Prestamos");
        try {
            frame1.getContentPane().add(new PrestListPanel(null));
            frame1.pack();
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de prestamos. Contacte con el soporte.");
        }

    }

    private void createUserListView() {
        try { // Listado de cuentas
            IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
            Pair<List<Cuenta>, Integer> aux2 = subsCuentas.consultarListaCuentas(_user.getNombre(), _user.getDni());
            int resultado = aux2.getSecond();
            switch (resultado) {
                case 0:
                    break;
                case 1:
                    throw new CuentaException("Fallo al modificar la cuenta. Compruebe que no exista ya");
                case 10:
                    throw new GUIException("Fallo al encontrar el archivo de cuentas. Contacte con el soporte.");
                default:
                    throw new GUIException("Error inesperado. Contacte con el soporte");
            }
            JFrame frame1 = new JFrame("Lista Cuentas");
            frame1.getContentPane().add(new CuentaListPanel(aux2.getFirst()));
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
                    try { // Lista de prestamos
                        IFachadaSubsPrestamos subsPrestamos = new FachadaSubsPrestamos();
                        int resultado = aux2.getSecond();
                        switch (resultado) {
                            case 0:
                                Pair<Cuenta, Integer> resultadoCuenta = subsCuentas
                                        .buscaCuenta(Integer.parseInt(NUMCUENTA));
                                if (resultadoCuenta.getSecond() == 1) {
                                    throw new CuentaException("Cuenta no encontrada.");
                                } else if (resultadoCuenta.getSecond() == 10) {
                                    throw new CuentaException(
                                            "No se pudo contactar con la base de datos. Contacte con el soporte.");
                                }
                                Pair<List<Prestamo>, Integer> listaFiltrada = subsPrestamos
                                        .consultarListaPrestamos(resultadoCuenta.getFirst());
                                if (resultadoCuenta.getSecond() == 1) {
                                    throw new CuentaException("La cuenta no tiene prestamos asociados.");
                                } else if (resultadoCuenta.getSecond() == 10) {
                                    throw new CuentaException(
                                            "No se pudo contactar con la base de datos. Contacte con el soporte.");
                                }
                                JFrame frame2 = new JFrame("Lista prestamos");
                                frame2.getContentPane().add(new PrestListPanel(listaFiltrada.getFirst()));
                                frame2.pack();
                                frame2.setVisible(true);
                                frame2.setLocationRelativeTo(null);
                                frame2.addWindowListener(new WindowListener() {
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
                                throw new UserException("Fallo al modificar el prestamo. Compruebe que no exista ya");
                            case 10:
                                throw new GUIException(
                                        "Fallo al encontrar el archivo de prestamos. Contacte con el soporte.");
                            default:
                                throw new GUIException("Error inesperado. Contacte con el soporte");
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
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de prestamos. Contacte con el soporte.");
        }
    }

}
