import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;

// panel that handles the right panel of the game. It handles everything
// related to the Attack part of the game (Guitar Hero part)

@SuppressWarnings("serial")
public class ActionPanel extends JPanel {
    
    AttackPanel attack_panel;
    private boolean levelScreenBool;
    
    public ActionPanel(CenterPanel cPanel) {
        setLayout(new CardLayout());
        
        cPanel.setActionPanel(this);
        
        attack_panel = new AttackPanel(10, this); 
        
        final IdleScreen idleScreen = new IdleScreen();
        
        final ScorePanel score_panel = new ScorePanel(attack_panel); 
        final JButton damage = new JButton("Hit the cat!");
        damage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                try{
                    double damage = score_panel.getDamage();
                    cPanel.damage(damage);
                    if (cPanel.getLevelScreen(ActionPanel.this) || 
                        cPanel.getCharacterHealth() == 0.0) {
                        switchScreen("idle");
                    }
                    else {
                        switchScreen("menu");
                    } 
                }
                catch (IOException i) {
                    System.out.println("save score error");
                }
            }
        });
        
        score_panel.add(damage);
        
        final AttackMenu attack_menu = new AttackMenu();        
        final JButton attack1 = new JButton("Attack");
        attack1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    CharacterPanel c = cPanel.getCharacterPanel();
                    EnemyPanel ep = cPanel.getEnemyPanel();
                    if (c.getStatus() == 1) {
                        cPanel.damage(0.0);
                        c.setStatus(0);
                        ep.setStatus(0);
                    }
                    else if (c.getStatus() == 2) {
                        double chance = Math.random();
                        if (chance < 0.5) {
                            c.setStatus(0);
                            ep.setStatus(0);
                            switchScreen("attack");
                            startGame();
                        }
                        else {
                            cPanel.damage(0.0);
                        }
                    }
                    else {
                        switchScreen("attack");
                        startGame();
                    }
                }
                catch (IOException i) {
                    System.out.println("save score error");
                }
            }
        });
        
        attack_menu.add(attack1);
     
        add(idleScreen, "idle");
        add(attack_menu, "menu");
        add(attack_panel, "attack");
        add(score_panel, "score");   
        
    }
    
    // provide access to the AttackPanel to other classes if necessary
    public AttackPanel getAP() {
        return attack_panel;
    }
    
    // function to switch between the veiws in the CardLayout
    public void switchScreen(String name) {
        CardLayout c = (CardLayout) this.getLayout();
        c.show(this, name);
    }
    
    // starts an attack
    public void startGame() {
        attack_panel.reset();
    }
}