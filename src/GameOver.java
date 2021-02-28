import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// screen that is displayed after the beat game is finished; displays the 
// score and the damage that occurs

@SuppressWarnings("serial")
class GameOver extends JPanel {
    
    private static String IMG_FILE = "files/gameOver.png";
    
    private static BufferedImage img;

    GameOver() {  
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