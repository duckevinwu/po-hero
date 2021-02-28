import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// screen that is displayed between levels

@SuppressWarnings("serial")
class LevelPanel extends JPanel {
    
    private EnemyPanel ep;
    
    private static String IMG_FILE = "files/levelScreen.png";
    
    private static BufferedImage img;

    LevelPanel(EnemyPanel ep) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ep = ep;
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
        Font levelFont = new Font("SansSerif", Font.BOLD, 50);
        g.setFont(levelFont);
        g.drawString("Stage: " + (ep.getLevel() + 1), 100, 150);
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}