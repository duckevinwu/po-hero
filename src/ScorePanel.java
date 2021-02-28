import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// screen that is displayed after the beat game is finished; displays the 
// score and the damage that occurs

@SuppressWarnings("serial")
class ScorePanel extends JPanel {
    
    private AttackPanel ap;

    ScorePanel(AttackPanel ap) {
        this.ap = ap;
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
    }
    
    // return the amount of damage to be done to the enemy
    public int getDamage() {
        int d = (int) (ap.getHits() * (ap.getAccuracy() / 100.0));
        if (ap.getAccuracy() == 100.0) {
            d = d * 2;
        }
        return d;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Beats hit: " + ap.getHits(), 20, 50);
        g.drawString("Accuracy: " + ap.getAccuracy() + "%", 20, 60);
        g.drawString("Damage: " + getDamage(), 20, 80);
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(150, 600);
    }
}