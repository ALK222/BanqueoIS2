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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import common.exception.GUIException;
import common.exception.UserException;
import common.misc.Pair;
import dominio.Tarjeta;
import substarjetas.FachadaSubsTarjetas;
import substarjetas.IFachadaSubsTarjetas;
import substarjetas.view.AltaGUI;
import substarjetas.view.ModGUI;
import substarjetas.view.TarjListPanel;

public class TarjWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private boolean _permisosGestor;
    private Tarjeta _tarj;
    private static Dimension tamanoBoton = new Dimension(200, 50);

    public static String DNI = "";
    public static String TIPOFILTRADO = "";
    public static String NUMTARJETA = "";

    public TarjWindow(boolean permisosGestor) {
        super("Tarjeta");
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
        JButton altaButton = new JButton("Alta Tarjeta");
        altaButton.setToolTipText("Alta de tarjeta, solo disponible para gestores");
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Alta tarjeta");
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
        JButton bajaButton = new JButton("Baja tarjeta");
        bajaButton.setToolTipText("Baja de usuario, solo disponible para gestores");
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Lista Tarjetas");
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
                        int decision = JOptionPane.showConfirmDialog(null, "¿Dar de baja al tarjeta " + NUMTARJETA + "?",
                                "Baja usuario", JOptionPane.YES_NO_CANCEL_OPTION);
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
                                    throw new UserException("Fallo al eliminar la tarjeta. Compruebe que no exista ya");
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
                try {
                    frame1.getContentPane().add(new TarjListPanel(null));
                    frame1.pack();
                    frame1.setVisible(true);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo abrir el archivo de tarjetas. Contacte con el soporte.");
                }
            }
        });
        bajaButton.setEnabled(_permisosGestor);
        bajaButton.setPreferredSize(tamanoBoton);
        aux.add(bajaButton, BorderLayout.SOUTH);
    }

    private void createModificarButton(JToolBar aux) {
        JButton modificacionButton = new JButton("Modificación tarjeta");
        modificacionButton.setToolTipText("Modificación de tarjeta, solo disponible para gestores");
        modificacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (_permisosGestor) {
                    JFrame frame1 = new JFrame("Lista Tarjetas");
                    frame1.addWindowListener(new WindowListener() {

                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            JFrame frame = new JFrame("Modicifacion Tarjeta");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
                            Pair<Integer, Tarjeta> aux = subsTarjetas.buscaTarjeta(Integer.parseInt(NUMTARJETA));
                            frame.getContentPane().add(new ModGUI(aux.getSecond()));

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
                        frame1.getContentPane().add(new TarjListPanel(null));
                        frame1.pack();
                        frame1.setVisible(true);
                    } catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(null,
                                "No se pudo abrir el archivo de usuarios. Contacte con el soporte.");
                    }
                } else {
                    JFrame frame = new JFrame("Modicifacion Tarjeta");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new ModGUI(_tarj));

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
        JButton listaButton = new JButton("Lista de tarjetas");
        listaButton.setToolTipText("Lista de tarjetas, solo disponible para gestores");
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Opciones de Filtrado");
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
                            IFachadaSubsTarjetas subsTarjetas = new FachadaSubsTarjetas();
                            Pair<List<Tarjeta>, Integer> listaFiltrada = subsTarjetas.consultarListaTarjetas(DNI);
                            int resultado = listaFiltrada.getSecond();
                            switch (resultado) {
                                case 0:
                                    break;
                                case 1:
                                    throw new UserException(
                                            "Fallo al modificar la tarjeta. Compruebe que no exista ya");
                                case 10:
                                    throw new GUIException(
                                            "Fallo al encontrar el archivo de tarjetas. Contacte con el soporte.");
                                default:
                                    throw new GUIException("Error inesperado. Contacte con el soporte");
                            }
                            JFrame frame1 = new JFrame("Lista Tarjetas");
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
                            frame1.getContentPane().add(new TarjListPanel(listaFiltrada.getFirst()));
                            frame1.pack();
                            frame1.setVisible(true);
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null,
                                    "No se pudo abrir el archivo de tarjetas. Contacte con el soporte.");
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
                //frame.getContentPane().add(new FiltrarGUI());
                frame.pack();
                frame.setVisible(true);
            }
        });
        listaButton.setEnabled(_permisosGestor);
        listaButton.setPreferredSize(tamanoBoton);
        aux.add(listaButton, BorderLayout.SOUTH);
    }
}
