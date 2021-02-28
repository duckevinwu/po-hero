import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.util.Random;

// The dog subclass of superclass Enemy. It's the strongest enemy and it 
// differs from other Enemies in that its attack can cause the sleep status

public class Dog extends Enemy {
    private static String IMG_FILE = "files/dog.png";
    private static String img2_file = "files/redx.png";
    private static int SIZE = 200;
    private static int INIT_POS_X = 200;
    private static int INIT_POS_Y = 150;
    private static double MAX_HEALTH = 20.0;
    private int level;

    private static BufferedImage img;
    private static BufferedImage img2;

    public Dog(int level) {
        // set all parameters of the Enemy
        super(INIT_POS_X, INIT_POS_Y, SIZE, SIZE);
        this.level = level;
        this.setMaxHealth(20.0 + (7 * level));
        this.setHealth((int) (20.0 + (7 * level)));
        Random ran = new Random();
        int d = ran.nextInt((6 * level) - 5 + 1) + 5;
        this.setEnemyDamage(d);
        int xp = ran.nextInt(13 + level - 5 + 1) + 5;
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
    
    // attack function that has a 1/3 probability of putting the character to sleep
    @Override
    public void attack() {
        Random ran = new Random();
        int d = ran.nextInt((6 * level) - 5 + 1) + 5;
        double chance = Math.random();
        if (chance < 0.33) {
            this.setStatus(2);
        }
        this.setEnemyDamage(d);
    }
    
    // method that returns the attack damage of the current enemy
    public int attackDamage() {        
        return getAttackDamage();
    }
    
    // draw this bad boy
    @Override
    public void draw(Graphics g) {
        Font classicFont = new Font("Dialog", Font.PLAIN, 12);
        Font attackFont = new Font("SansSerif", Font.BOLD, 15);
        if (this.isAttacking()) {
            g.setFont(attackFont);
            g.drawString("Po took " + attackDamage() + " damage from...it's not even a cat!", 
                         200, 420);
            if (this.getSleep()) {
                g.drawString("Po is asleep!", 200, 460);
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
