package common.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel de imagen
 * 
 * @see JPanel
 */
public class ImagePanel extends JPanel {

    private BufferedImage image;

    /**
     * Constructor del panel
     * 
     * @throws IOException
     */
    public ImagePanel() throws IOException {
        File testFile;
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
                        throw e1;
                    }
                }
            }
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));
        add(picLabel);
    }
}
