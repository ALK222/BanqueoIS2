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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import common.exception.CuentaException;
import common.exception.GUIException;
import common.misc.Pair;
import dominio.Cuenta;
import subscuentas.FachadaSubsCuentas;
import subscuentas.IFachadaSubsCuentas;

public class CuentaWindow extends JFrame{

    private boolean _permisosGestor;
    private Cuenta _account;
    private static Dimension tamanoBoton = new Dimension(200, 50);

    public static String DNI = "";
    public static int NUM_CUENTA; 
    public static String TITULAR_CUENTA; 
    
    public CuentaWindow(boolean permisosGestor) {
        super("Cuenta");
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
        JButton altaButton = new JButton("Alta cuenta");
        altaButton.setToolTipText("Alta de cuenta, solo disponible para gestores");
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Alta Cuenta");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    private void createBajaButton(JToolBar aux) {
        JButton bajaButton = new JButton("Baja cuenta");
        bajaButton.setToolTipText("Baja de cuenta, solo disponible para gestores");
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Lista Cuentas");
                frame1.addWindowListener(new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                        Cuenta aux = subsCuentas.buscaCuenta(NUM_CUENTA).getFirst();
                        int decision = JOptionPane.showConfirmDialog(null, "¿Dar de baja a la cuenta " + NUM_CUENTA + "?",
                                "Baja cuenta", JOptionPane.YES_NO_CANCEL_OPTION);
                        int resultado = 1;
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
                                    throw new CuentaException("Fallo al eliminar la cuenta. Compruebe que no exista ya");
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
                    frame1.getContentPane().add(new CuentaListPanel(null));
                    frame1.pack();
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
                }
            }
        });
        bajaButton.setEnabled(_permisosGestor);
        bajaButton.setPreferredSize(tamanoBoton);
        aux.add(bajaButton, BorderLayout.SOUTH);
    }

    private void createModificarButton(JToolBar aux) {
        JButton modificacionButton = new JButton("Modificación cuenta");
        modificacionButton.setToolTipText("Modificación de cuenta, solo disponible para gestores");
        modificacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (_permisosGestor) {
                    JFrame frame1 = new JFrame("Lista Cuentas");
                    frame1.addWindowListener(new WindowListener() {

                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            JFrame frame = new JFrame("Modificación Cuenta");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                            Cuenta aux = subsCuentas.buscaCuenta(NUM_CUENTA).getFirst();
                            frame.getContentPane().add(new ModGUI(aux));
                            	
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
                    try {
                        frame1.getContentPane().add(new CuentaListPanel(null));
                        frame1.pack();
                        frame1.setVisible(true);
                        frame1.setLocationRelativeTo(null);
                    } catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(null,
                                "No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
                    }
                } else {
                    JFrame frame = new JFrame("Modificación cuenta");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new ModGUI(_account));

                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                }

            }
        });
        modificacionButton.setEnabled(true);
        modificacionButton.setPreferredSize(tamanoBoton);
        aux.add(modificacionButton, BorderLayout.NORTH);

    }

    private void createListaButton(JToolBar aux) {
        JButton listaButton = new JButton("Lista de cuentas");
        listaButton.setToolTipText("Lista de cuentas, solo disponible para gestores");
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Opciones de filtrado");
                frame.addWindowListener(new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent e) {
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                        try {
                            IFachadaSubsCuentas subsCuentas = new FachadaSubsCuentas();
                            Pair<List<Cuenta>, Integer> listaFiltrada = subsCuentas.consultarListaCuentas(TITULAR_CUENTA, DNI); 
                            int resultado = listaFiltrada.getSecond();
                            switch (resultado) {
                                case 0:
                                    break;
                                case 1:
                                    throw new CuentaException(
                                            "Fallo al modificar la cuenta. Compruebe que no exista ya");
                                case 10:
                                    throw new GUIException(
                                            "Fallo al encontrar el archivo de cuentas. Contacte con el soporte.");
                                default:
                                    throw new GUIException("Error inesperado. Contacte con el soporte");
                            }
                            JFrame frame1 = new JFrame("Lista Cuentas");
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
                            frame1.getContentPane().add(new CuentaListPanel(listaFiltrada.getFirst()));
                            frame1.pack();
                            frame1.setVisible(true);
                            frame.setLocationRelativeTo(null);
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null,
                                    "No se pudo abrir el archivo de cuentas. Contacte con el soporte.");
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
                // TODO: esto no lo he tocado 
                frame.getContentPane().add(new FiltrarGUI());
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        listaButton.setEnabled(_permisosGestor);
        listaButton.setPreferredSize(tamanoBoton);
        aux.add(listaButton, BorderLayout.SOUTH);
    }
}
