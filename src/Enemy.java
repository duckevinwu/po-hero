import java.awt.Graphics;

/** 
 * An Enemy object in the game
 *
 * An Enemy has many different different fields including position, size, damage, 
 * experience given, health, and more. The things that differ between different 
 * Enemy subclasses are the attack function and the draw function. 
 */
public abstract class Enemy {
    
    // position of the enemy
    private int px; 
    private int py;

    /* Size of object, in pixels. */
    private int width;
    private int height;
    
    // other necessary fields of an enemy
    private int damage;
    private double expGiven;    
    private double maxHealth;
    private double currHealth;
    private boolean dead = false;
    private boolean isAttack = false;
    private int status = 0;

    // Constructor
    public Enemy(int px, int py, int width, int height) {
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;
        this.maxHealth =  maxHealth;
        this.currHealth = maxHealth;
    }

    /*** GETTERS **********************************************************************************/
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public double getMaxHealth() {
        return this.maxHealth;
    }
    
    public double getCurrHealth() {
        return this.currHealth;
    }
    
    // returns whether or not the enemy is dead
    public boolean isDead() {
        return this.dead;
    }
    
    // returns whether or not it is the enemy's turn to attack
    public boolean isAttacking() {
        return this.isAttack;
    }
    
    public int getAttackDamage() {
        return this.damage;
    }
    
    public double getExpGiven() {
        return this.expGiven;
    }
    
    public boolean getParalyze() {
        return (this.status == 1);
    }
    
    public boolean getSleep() {
        return (this.status == 2);
    }
    

    /*** SETTERS **********************************************************************************/
    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }
    
    public void setHealth(int h) {
        this.currHealth = h;
    }
    
    public void setMaxHealth(double h) {
        this.maxHealth = h;
    }
    
    // does damage to the current instance of Enemy
    public void damage(double d) {
        
        double h = this.currHealth - d;
        if (h <= 0) {
            this.currHealth = 0;
            this.dead = true;
        }
        else {
            this.currHealth = h;
        }
    }
    
    public void setEnemyDamage(int d) {
        this.damage = d;
    }
    
    public void setAttackBool(boolean b) {
        this.isAttack = b;
    }
    
    public void setExpGiven(double e) {
        this.expGiven = e;
    }
    
    public void setStatus(int s) {
        this.status = s;
    }
    
    // all Enemies will have an attack function and a draw function unique
    // to what subclass of Enemy they are
    public abstract void attack();
    
    public abstract void draw(Graphics g);
}
