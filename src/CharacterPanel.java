import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

// JPanel that handles everything related to the player's character

@SuppressWarnings("serial")
class CharacterPanel extends JPanel {
    
    private static String IMG_FILE = "files/po.png";
    private static BufferedImage img;
    
    private double health;
    private double maxHealth;
    private double exp;
    private double maxExp;
    private int score;
    private Scores highScores;
    private int charLevel;
    private int status;

    CharacterPanel(double health, double exp) {
        // gets the high scores currently stored in the text file
        try {
            highScores = Scores.make("files/highScores.txt");
        }
        catch (IOException i) {
            System.out.println("IO error");
        }
        
        // set all private variables for the character
        this.health = health;
        this.maxHealth = health;
        this.exp = exp;
        this.maxExp = 10.0;
        this.score = 0;
        this.charLevel = 1;
        this.status = 0;

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    // getter method that returns current score of the player
    public int getScore() {
        return score;
    }
    
    // method that increases the score by an integer s
    public void incrScore(int s) {
        score = score + s;
        repaint();
    }
    
    // method that adds health to the current health of the character, up to 
    // the maximum health
    public void incrHealth(double h) {
        health = health + h;
        if (health > maxHealth) {
            health = maxHealth;
        }
        repaint();
    }
    
    // setter method that sets the score as an integer s
    public void setScore(int s) {
        score = s;
        repaint();
    }
    
    // setter method that sets the maximum health of the player 
    public void setMaxHealth(double h) {
        maxHealth = h;
    }
    
    // getter method that retrieves current health of the player
    public double getHealth() {
        return health;
    }
    
    // getter method that returns the character level (exp, not stage)
    public int getCharLevel() {
        return charLevel;
    }
    
    // retunrs the current amount of exp the player has
    public double getCurrExp() {
        return exp;
    }
    
    // returns the maximum amount of exp the player can have
    public double getMaxExp() {
        return maxExp;
    }
    
    // returns the status of the player (normal, paralyzed, asleep)
    public int getStatus() {
        return status;
    }
    
    // sets the current health of the player
    public void setHealth(int h) {
        health = h;
        repaint();
    }
    
    // method that does damage to the character
    public void damageChar(double damage) {
        double finalHealth = health - damage;
        if (finalHealth <= 0) {
            this.health = 0;
        }
        else {
            this.health = finalHealth;
        }
        repaint();
    }
    
    // method that adds exp to current exp
    public void addExp(double e) {
        exp = exp + e;
        repaint();
    }
    
    // method that sets current exp
    public void setExp(double e) {
        exp = e;
        repaint();
    }
    
    // method that sets maximum exp
    public void setMaxExp(double e) {
        maxExp = e;
        repaint();
    }
    
    // method that sets the character level
    public void setCharLevel(int l) {
        charLevel = l;
        repaint();
    }
    
    // method that sets the status of the character (normal, paralyzed, asleep)
    public void setStatus(int s) {
        status = s;
        repaint();
    }
    
    // method that checks whether or not it's time to level up
    public boolean expCheck() {
        return (exp >= maxExp);
    }
    
    // saves the current score in the high scores file
    public void save(String name) throws IOException {
        highScores.saveScore(score, name);
        repaint();
    }
    
    // represents the high scores as an ArrayList
    public ArrayList<singleScore> getHighScores() {
        return highScores.orderedScores();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 25, 25, 100, 100, null);
        g.drawString("Score: " + score, 25, 160);
        g.drawString("Level: " + charLevel, 25, 210);
        String stat = "Normal";
        if (status == 0) {
            stat = "Normal";
        }
        else if (status == 1) {
            stat = "Paralyzed";
        }
        else if (status == 2) {
            stat = "Asleep";
        }
        g.drawString("Status: " + stat, 25, 230);
        g.drawRect(25, 250, 40, 300);
        g.drawRect(85, 250, 40, 300);
        g.setColor(Color.GREEN);
        g.fillRect(25, 250, 40, (int) (health * (300 / maxHealth)));
        g.setColor(Color.YELLOW);
        g.fillRect(85, 250, 40, (int) (exp * (300 / maxExp)));
        g.setColor(Color.BLACK);
        g.drawString("Health", 28, 570);
        g.drawString("EXP", 93, 570);
        
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(150, 600);
    }
}