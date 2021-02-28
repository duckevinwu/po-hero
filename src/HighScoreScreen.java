import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// screen that displays the top 10 high scores
@SuppressWarnings("serial")
class HighScoreScreen extends JPanel {
    
    private static String IMG_FILE = "files/scoreBack.png";
    
    private static BufferedImage img;
    
    private CharacterPanel charPanel;

    HighScoreScreen(CharacterPanel cp) {  
        charPanel = cp;
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
        g.drawString("Rank", 50, 20);
        g.drawString("Name", 100, 20);
        g.drawString("Score", 450, 20);
        for (int i = 0; i < 10; i++) {
            g.drawString("" + (i + 1), 50, ((i + 1) * 50) + 20);
        }
        ArrayList<singleScore> tm = charPanel.getHighScores();
        if (tm != null) {
            int upperLimit = 10;
            int treeSize = tm.size();
            if (treeSize < 10) {
                upperLimit = treeSize;
            }
            for (int i = 0; i < upperLimit; i++) {                
                String username = tm.get(i).getName();
                int a = tm.get(i).getScore();
                String num = "" + a;
                g.drawString(username, 100, ((i + 1) * 50) + 20);
                g.drawString(num, 450, ((i + 1) * 50) + 20);                
            }
        }
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}