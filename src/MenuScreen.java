import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// displays the main menu of the game

@SuppressWarnings("serial")
class MenuScreen extends JPanel {
    
    private static String IMG_FILE = "files/intro.png";
    
    private static BufferedImage img;

    MenuScreen() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 600, 600, null);
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}