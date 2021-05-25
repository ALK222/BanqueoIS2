package common;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

import common.view.InicioSesionGUI;

/**
 * Lanzador principal del programa con todos los checks necesarios
 */
public class Main {

    /**
     * Comprueba si existe el fichero de Prestamos y si no lo crea
     * 
     * @throws IOException si no puede crearse el archivo
     */
    public static void checkPrestamoFile() throws IOException {
        File testFile;
        @SuppressWarnings("unused")
        InputStream in;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/Prestamos.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/Prestamos.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                try {
                    testFile = new File("./resources/Tarjetas.json");
                    in = new FileInputStream(testFile);
                } catch (FileNotFoundException ex1) {
                    try {
                        testFile = new File(System.getProperty("user.dir") + "/resources/Prestamos.json");
                        testFile.createNewFile();
                    } catch (Exception e2) {
                        testFile = new File(System.getProperty("user.dir") + "/src/resources/Prestamos.json");
                        in = new FileInputStream(testFile);
                    }
                }
            }
        }
    }

    /**
     * Comprueba si existe el fichero de cuentas y si no lo crea
     * 
     * @throws IOException si no puede crearse el archivo
     */
    public static void checkCuentaFile() throws IOException {
        File testFile;
        @SuppressWarnings("unused")
        InputStream in;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/Usuarios.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/Usuarios.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                try {
                    testFile = new File("./resources/Tarjetas.json");
                    in = new FileInputStream(testFile);
                } catch (FileNotFoundException ex1) {
                    try {
                        testFile = new File(System.getProperty("user.dir") + "/resources/Usuarios.json");
                        testFile.createNewFile();
                    } catch (Exception e2) {
                        testFile = new File(System.getProperty("user.dir") + "/src/resources/Usuarios.json");
                        in = new FileInputStream(testFile);
                    }
                }
            }
        }
    }

    /**
     * Comprueba si existe el fichero de tarjetas y si no lo crea
     * 
     * @throws IOException si no puede crearse el archivo
     */
    public static void checkTarjetaFile() throws IOException {
        File testFile;
        @SuppressWarnings("unused")
        InputStream in;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/Tarjetas.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/Tarjetas.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                try {
                    testFile = new File("./resources/Tarjetas.json");
                    in = new FileInputStream(testFile);
                } catch (FileNotFoundException ex1) {
                    try {
                        testFile = new File(System.getProperty("user.dir") + "/resources/Tarjetas.json");
                        testFile.createNewFile();
                    } catch (Exception e2) {
                        testFile = new File(System.getProperty("user.dir") + "/src/resources/Tarjetas.json");
                        in = new FileInputStream(testFile);
                    }
                }
            }
        }
    }

    /**
     * Comprueba si existe el fichero de usuarios y si no lo crea
     * 
     * @throws IOException si no puede crearse el archivo
     */
    public static void checkUsuarioFile() throws IOException {
        File testFile;
        @SuppressWarnings("unused")
        InputStream in;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/Usuarios.json");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/Usuarios.json");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                try {
                    testFile = new File("./resources/Usuarios.json");
                    in = new FileInputStream(testFile);
                } catch (FileNotFoundException ex1) {
                    try {
                        testFile = new File(System.getProperty("user.dir") + "/src/resources/Usuarios.json");
                        in = new FileInputStream(testFile);
                    } catch (Exception e2) {
                        testFile = new File(System.getProperty("user.dir") + "/resources/Usuarios.json");
                        testFile.createNewFile();
                        FileWriter wr = new FileWriter(testFile);
                        wr.append(
                                "{\"usuarios\":[{\"Nombre\": \"admin\",\"Email\": \"\",\"Telefono\": 0,\"Contrasena\": \"admin\",\"Domicilio\": \"\",\"DNI\": \"admin\"}]}");
                        wr.close();
                    }

                }
            }
        }
    }

    /**
     * Checks if al database files are present
     * 
     * @throws IOException si no se pudo encontrar o crear la base de datos
     */
    public static void checkfiles() throws IOException {
        checkCuentaFile();
        checkPrestamoFile();
        checkTarjetaFile();
        checkUsuarioFile();
    }

    /**
     * Ventana de inicio de sesión
     * 
     * @param c controller principal del programa al que pasarle el usuario y sus
     *          permisos
     */
    public static void inicioSesionGui(Controller c) {
        JFrame frame = new JFrame("Iniciar Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new InicioSesionGUI(c));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                c.initGUI();
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
     * Lanzador del programa
     * 
     * @param args argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        try {
            checkfiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller c = new Controller();
        inicioSesionGui(c);
    }

}
