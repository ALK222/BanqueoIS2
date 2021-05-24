package common.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {
        File testFile;
        InputStream in;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/banqueo.png");
            in = new FileInputStream(testFile);
        } catch (FileNotFoundException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/banqueo.png");
                in = new FileInputStream(testFile);
            } catch (FileNotFoundException ex) {
                try {
                    testFile = new File("./resources/banqueo.png");
                    in = new FileInputStream(testFile);
                } catch (FileNotFoundException ex1) {
                    testFile = new File(System.getProperty("user.dir") + "src/resources/banqueo.png");
                    try {
                        in = new FileInputStream(testFile);
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
