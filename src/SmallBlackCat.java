import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.util.Random;

/**
 * The generic Enemy class that doesn't do anything special besides attack
 * the player back if it is not killed. 
 */
public class SmallBlackCat extends Enemy {
    private static String IMG_FILE = "files/sm_bl_cat.png";
    private static String img2_file = "files/redx.png";
    private static int SIZE = 200;
    private static int INIT_POS_X = 200;
    private static int INIT_POS_Y = 150;
    private double maxHealth = 10.0;
    private int level;

    private static BufferedImage img;
    private static BufferedImage img2;

    public SmallBlackCat(int level) {
        super(INIT_POS_X, INIT_POS_Y, SIZE, SIZE);
        this.setMaxHealth(10.0 + (5 * level));
        this.setHealth((int) (10.0 + (5 * level)));
        this.level = level;
        Random ran = new Random();
        int d = ran.nextInt((3 * level) - 1 + 1) + 1;
        this.setEnemyDamage(d);
        int xp = ran.nextInt((5 + level) - 1 + 1) + 1;
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
    
    // attack doesn't do anything besides damage
    @Override
    public void attack() {
        Random ran = new Random();
        int d = ran.nextInt((3 * level) - 1 + 1) + 1;
        this.setEnemyDamage(d);
    }
    
    // get the damage the enemy should do
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
            g.drawString("Po took " + attackDamage() + " damage from Small Black Cat!", 200, 420);
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
