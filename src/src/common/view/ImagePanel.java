package common.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {
        File testFile;
        InputStream in;
        try {
            testFile = new File(System.getProperty("user.dir") + "/resources/banqueo.png");
            image = ImageIO.read(testFile);
        } catch (IOException e) {
            try {
                testFile = new File(System.getProperty("user.dir") + "/src/src/resources/banqueo.png");
                image = ImageIO.read(testFile);
            } catch (IOException ex) {
                try {
                    testFile = new File("./resources/banqueo.png");
                    image = ImageIO.read(testFile);
                } catch (IOException ex1) {
                    testFile = new File(System.getProperty("user.dir") + "/src/resources/banqueo.png");
                    try {
                        image = ImageIO.read(testFile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));
        add(picLabel);
    }
}
