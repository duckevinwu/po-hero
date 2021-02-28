import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.IOException;

// handles everything related to the current Enemy including receiving damage
// from the player and attacking the player

@SuppressWarnings("serial")
public class EnemyPanel extends JPanel {
    
    private EnemyList enemies;
    private CenterPanel cPanel;
    private int level;
    private boolean levelScreen;
    private CharacterPanel charPanel;
    private LeftPanel leftPanel;
    private AttackPanel attackPanel;

    // Game constants
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    // Initial Update interval for timer, in milliseconds
    public static final int INTERVAL = 2000;

    public EnemyPanel(int level, CenterPanel c, LeftPanel l) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        enemies = new EnemyList(level);
        cPanel = c;
        charPanel = l.getCharPanel();
        leftPanel = l;
        level = level;
        levelScreen = false;
        
        // the timer that constantly checks whether the level is over
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); 

        setFocusable(true);
        
    }
    
    // damages the enemy 
    public void damageEnemy(double d) throws IOException {
        enemies.getHead().damage(d);
        repaint();
        // if an Enemy is dead, check if player needs to level up
        if (enemies.getHead().isDead()) {
            charPanel.addExp(enemies.getHead().getExpGiven());
            // level up player if exp is hit
            if (charPanel.expCheck()) {
                int currLevel = charPanel.getCharLevel();
                int beats = attackPanel.getNumBeats();
                int currSpeed = attackPanel.getSpeed();
                double extraExp = charPanel.getCurrExp() - charPanel.getMaxExp();
                charPanel.setExp(extraExp);
                charPanel.setCharLevel(currLevel + 1);
                charPanel.setMaxExp(charPanel.getMaxExp() + 5 * currLevel);
                charPanel.setMaxHealth(20 + (5 * currLevel));
                charPanel.setHealth(20 + (5 * currLevel));
                attackPanel.setNumBeats(beats + (currLevel * 5));
                attackPanel.setSpeed(currSpeed - (currLevel * 30));
            }
        }
        // if all enemies are dead, display the level screen
        if (enemies.getHead().isDead() && enemies.getNumEnemies() == 1) {
            levelScreen = true;            
        }
        // if enemy dies, add 1 to the score
        if (enemies.getHead().isDead()) {
            Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                charPanel.incrScore(1);                
                repaint();
            }
        });
        timer.setRepeats(false);
        timer.start(); 
        }
        else {
            enemyAttack();
        }              
    }
    
    // get the current level 
    public int getLevel() {
        return level;
    }

    // start a new level
    public void startLevel() {
        level = level + 1;
        enemies = new EnemyList(level);
        levelScreen = false;        

        requestFocusInWindow();
    }
    
    // reset the game
    public void reset() {
        attackPanel = cPanel.getAttackPanel();
        level = 1;
        enemies = new EnemyList(level);
        levelScreen = false;
        charPanel.setHealth(20);
        charPanel.setMaxHealth(20);
        charPanel.setScore(0);
        charPanel.setExp(0.0);
        charPanel.setMaxExp(5.0);
        charPanel.setCharLevel(1);
        charPanel.setStatus(0);
        attackPanel.setNumBeats(10);
        attackPanel.setSpeed(200);
        requestFocusInWindow();
        
        repaint();
    }

    // set the status of the enemy 
    public void setStatus(int s) {
        enemies.getHead().setStatus(s);
    }
    
    //  returns whether or not the level screen should be displayed
    public boolean getLevelScreen() {
        return levelScreen;
    }
    
    // switches to the level transition screen
    public void postLevel() {
        cPanel.switchScreen("level");
        levelScreen = true;
    }
    
    // checks if all enemies have been killed every tick()
    void tick() {
        if (enemies.getNumEnemies() == 0) {
            postLevel();
        }
    }
    
    // function for enemy attacking the player
    public void enemyAttack() {
        Enemy enemy = enemies.getHead();
        enemy.attack();
        if (enemy.getParalyze()) {
            charPanel.setStatus(1);
        }
        else if (enemy.getSleep()) {
            charPanel.setStatus(2);
        }
        int d = enemy.getAttackDamage();
        charPanel.damageChar(d);
        enemy.setAttackBool(true);
        repaint();
        
        // primitive animation system
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enemy.setAttackBool(false);
                repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();   
        
        // if the player has 0 health, end the game
        if (charPanel.getHealth() == 0) {
            Timer t1 = new Timer(3000, new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    cPanel.actionPanelSwitch("idle");
                    cPanel.switchScreen("gameOver");
                }
            });
            t1.setRepeats(false);
            t1.start();
        }
    }
        
                                    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        enemies.draw(g);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}