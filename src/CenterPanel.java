import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;

// panel that handles everything that will be displayed in the center panel

@SuppressWarnings("serial")
public class CenterPanel extends JPanel {
    
    final EnemyPanel e_panel;
    private ActionPanel actionPanel;
    private CharacterPanel charPanel;
    
    public CenterPanel(LeftPanel l) {
        setLayout(new CardLayout());
        
        charPanel = l.getCharPanel();
        
        e_panel = new EnemyPanel(1, this, l);
        
        // game over screen with a textarea to input name
        final GameOver gameOverScreen = new GameOver();
        final JLabel prompt = new JLabel("Name: ");
        final JTextArea inputBox = new JTextArea(1, 20);
        final JButton submitName = new JButton("OK");
        submitName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = inputBox.getText();
                    charPanel.save(name);
                    switchScreen("scores");
                }
                catch (IOException i) {
                    System.out.println("can't submit name");
                }
            }
        });
        gameOverScreen.add(prompt);
        gameOverScreen.add(inputBox);
        gameOverScreen.add(submitName);
        
        // main menu screen
        final MenuScreen menu = new MenuScreen();
        
        final JButton startGame = new JButton("Play");
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen("enemy");
                startGame();
                actionPanel.switchScreen("menu");
            }
        });
        
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen("instructions");
            }
        });
        
        // high score screen
        final HighScoreScreen scoreScreen = new HighScoreScreen(charPanel);
        scoreScreen.setLayout(new BorderLayout());
        
        // instructions screen
        final InstructionsScreen instrScreen = new InstructionsScreen();
        instrScreen.setLayout(new BorderLayout());
        
        final JButton highScores = new JButton("High Scores");
        highScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen("scores");
            }
        });
 
        final JButton goMainMenu = new JButton("Main Menu");
        goMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen("menu");
            }
        });
        
        final JButton goMainMenu1 = new JButton("Main Menu");
        goMainMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen("menu");
            }
        });
        
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(goMainMenu);
        
        final JPanel buttonPanel1 = new JPanel();
        buttonPanel1.add(goMainMenu1);
        
        scoreScreen.add(buttonPanel, BorderLayout.SOUTH);
        instrScreen.add(buttonPanel1, BorderLayout.SOUTH);
        
        menu.add(startGame);
        menu.add(instructions);
        menu.add(highScores);
        
        // level transition panel
        final LevelPanel lPanel = new LevelPanel(e_panel);        
        final JButton advanceLevel = new JButton("Continue");
        advanceLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen("enemy");
                startLevel();
                actionPanel.switchScreen("menu");
            }
        });
        
       lPanel.add(advanceLevel);
       
       add(menu, "menu");
       add(e_panel, "enemy");
       add(lPanel, "level");  
       add(gameOverScreen, "gameOver");
       add(scoreScreen, "scores");
       add(instrScreen, "instructions");       
    }
    
    // getter method that returns the current health of the player
    public double getCharacterHealth() {
        return charPanel.getHealth();
    }
    
    // getter method that provides access to the enemy panel
    public EnemyPanel getEnemyPanel() {
        return this.e_panel;
    }
    
    // getter method that provides access to the character panel
    public CharacterPanel getCharacterPanel() {
        return this.charPanel;
    }
    
    // method that switches panels in the ActionPanel cardlayout
    public void actionPanelSwitch(String name) {
        actionPanel.switchScreen(name);
    }
    
    // setter method that provides the CenterPanel with access to the ActionPanel
    public void setActionPanel(ActionPanel a) {
        this.actionPanel = a;
    }
    
    // getter method that provides access to the AttackPanel
    public AttackPanel getAttackPanel() {
        return actionPanel.getAP();
    }
    
    // starts a new level
    public void startLevel() {
        e_panel.startLevel();
    }
    
    // starts a new game
    public void startGame() {
        e_panel.reset();
    }
    
    // connecting method in the task of damaging an enemy
    public void damage(double d) throws IOException {
        e_panel.damageEnemy(d);
    }
    
    // getter method that checks whether or not the level transition screen
    // should be displayed
    public boolean getLevelScreen(ActionPanel a) {
        this.actionPanel = a;
        return e_panel.getLevelScreen();
    }
    
    // function to switch between the veiws in the CardLayout
    public void switchScreen(String name) {
        CardLayout c = (CardLayout) this.getLayout();
        c.show(this, name);
    }

}