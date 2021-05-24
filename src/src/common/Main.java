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

public class Main {

    public static void checkPrestamoFile() throws IOException {
        File testFile;
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

    public static void checkCuentaFile() throws IOException {
        File testFile;
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

    public static void checkTarjetaFile() throws IOException {
        File testFile;
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

    public static void checkUsuarioFile() throws IOException {
        File testFile;
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

    public static void checkfiles() throws IOException {
        checkCuentaFile();
        checkPrestamoFile();
        checkTarjetaFile();
        checkUsuarioFile();
    }

    public static void main(String[] args) {
        try {
            checkfiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller c = new Controller();

        JFrame frame = new JFrame("Inicio de Sesion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new InicioSesionGUI(c));
        frame.pack();
        frame.setVisible(true);
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

}
