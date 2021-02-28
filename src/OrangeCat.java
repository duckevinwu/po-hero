import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.util.Random;

// The Orange Cat subclass of superclass Enemy. It's the medium enemy and it 
// differs from other Enemies in that its attack can cause paralysis

public class OrangeCat extends Enemy {
    private static String IMG_FILE = "files/orange_cat.png";
    private static String img2_file = "files/redx.png";
    private static int SIZE = 200;
    private static int INIT_POS_X = 200;
    private static int INIT_POS_Y = 150;
    private double maxHealth = 15.0;
    private int level;

    private static BufferedImage img;
    private static BufferedImage img2;

    public OrangeCat(int level) {
        super(INIT_POS_X, INIT_POS_Y, SIZE, SIZE);
        this.level = level;
        this.setMaxHealth(15.0 + (5 * level));
        this.setHealth((int) (15.0 + (5 * level)));
        Random ran = new Random();
        int d = ran.nextInt((5 * level) - 3 + 1) + 3;
        this.setEnemyDamage(d);
        int xp = ran.nextInt(10 + level - 4 + 1) + 4;
        this.setExpGiven(xp + 0.0);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        
        try {
            if (img2 == null) {
                img2 = ImageIO.read(new File(img2_file));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    // attack function that has a 1/3 chance of causing paralysis
    @Override
    public void attack() {
        Random ran = new Random();
        int d = ran.nextInt((5 * level) - 3 + 1) + 3;
        double chance = Math.random();
        if (chance < 0.33) {
            this.setStatus(1);
        }
        this.setEnemyDamage(d);
    }
    
    // returns the damage done done by the enemy
    public int attackDamage() {        
        return getAttackDamage();
    }
    
    // draw
    @Override
    public void draw(Graphics g) {
        Font classicFont = new Font("Dialog", Font.PLAIN, 12);
        Font attackFont = new Font("SansSerif", Font.BOLD, 15);
        if (this.isAttacking()) {
            g.setFont(attackFont);
            g.drawString("Po took " + attackDamage() + " damage from Orange Cat!", 200, 420);
            if (this.getParalyze()) {
                g.drawString("Po is paralyzed!", 200, 460);
            }
        }
        g.setFont(classicFont);
        g.drawImage(img, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, null);
        g.setColor(Color.BLACK);
        g.fillRect(100, 500, 400, 50);
        g.setColor(Color.RED);
        g.fillRect(100, 500, (int) (this.getCurrHealth() * (400 / this.getMaxHealth())), 50);
        if (this.getCurrHealth() == 0) {
            g.drawImage(img2, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, null);
        }
    }
}
