import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Holds a list of Enemies that represents the Enemies in one stage of the game
 * Uses a LinkedList because the Enemies will be displayed in the order that 
 * they are produces; also, duplicates are necessary
 */

public class EnemyList {

    private LinkedList<Enemy> enemies;
    private int level;
    
    private static String IMG_FILE = "files/background.png";
    private static BufferedImage img;
    
    // constructor that creates a LinkedList based off the current game level
    public EnemyList(int level) {
        this.level = level;
        enemies = new LinkedList<Enemy>();        
        for (int i = 0; i < level; i++) {
            // randomly add the different enemies to the EnemyList
            double choice = Math.random();
            int col = 0;
            if (choice < .7 - (0.1 * level)) {
                SmallBlackCat x = new SmallBlackCat(level);
                enemies.add(x);
            }            
            else if (choice < 0.95 - (0.05 * level)) {
                OrangeCat oc = new OrangeCat(level);
                enemies.add(oc);
            }           
            else {
                Dog d = new Dog(level);
                enemies.add(d);
            }    
        }
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    // returns the first Enemy in the list
    public Enemy getHead() {
        return enemies.getFirst();
    }
    
    // returns the number of Enemies in the list
    public int getNumEnemies() {
        return enemies.size();
    }
    
    // removes the first Enemy in the EnemyList
    public void remove() {
        enemies.remove();
    }
    
    // draws each of the Enemies in the EnemyList
    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, 600, 600, null);
        if (enemies.size() != 0) {
            enemies.getFirst().draw(g);
            g.setColor(Color.BLACK);
            g.drawString("Stage: " + (level), 10, 580);
            g.drawString("Cats Left: " + getNumEnemies(), 250, 480);
            if (enemies.getFirst().isDead()) {
                enemies.remove();
            }
        }
    }
}