import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.text.DecimalFormat;
import java.math.RoundingMode;

// handles the events that occur when the beat game is going on

@SuppressWarnings("serial")
public class AttackPanel extends JPanel {
    
    private ActionPanel a_panel;
    private int beats;
    
    private MusicGrid gameGrid;
    private OverGrid overGrid;
    private double hits = 0.0;
    private double totalPress = 0.0;
    private double percent = 0.0;

    public boolean playing = false; 

    // Game constants
    public static final int WIDTH = 150;
    public static final int HEIGHT = 600;

    // Update interval for timer, in milliseconds
    private int speed = 200;
    private Timer timer;

    public AttackPanel(int beats, ActionPanel p) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.beats = beats;
        this.a_panel = p;
        
        // create the gridmaps (gameGrid is the background, overGrid is foreground)
        gameGrid = new MusicGrid(3, 30, 50, 20);
        overGrid = new OverGrid(3, 30, 50, 20, beats); 

        timer = new Timer(speed, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); 

        setFocusable(true);
        
        // listen for keyboard presses
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    if (overGrid.colOne()) {
                        hits++;
                    }
                    totalPress++;
                } else if (e.getKeyCode() == KeyEvent.VK_X) {
                    if (overGrid.colTwo()) {
                        hits++;
                    }
                    totalPress++;
                } else if (e.getKeyCode() == KeyEvent.VK_C) {
                    if (overGrid.colThree()) {
                        hits++;
                    }
                    totalPress++;
                } 
            }

        });
    }
    
    // getter method that returns the speed at which tick() is called
    public int getSpeed() {
        return speed;
    }
    
    // setter method that sets the speed at which tick() is called
    public void setSpeed(int s) {
        if (s <= 0) {
            s = 10;
        }
        timer.stop();
        timer = new Timer(s, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); 
        
    }
    
    // getter method that returns the number of beats in an attack
    public int getNumBeats() {
        return beats;
    }
    
    // setter method that sets the number of beats in an attack
    public void setNumBeats(int b) {
        beats = b;
    }
    
    // returns the percentage of beats hit rounded to two decimal places
    public double getAccuracy() {
        double b = 0.0;
        try {
            if (totalPress != 0) {                
                DecimalFormat d = new DecimalFormat("##.##");
                d.setRoundingMode(RoundingMode.DOWN);
                percent = (hits / totalPress) * 100.0;
                b = Double.parseDouble(d.format(percent));
            }
        }
        catch (NumberFormatException n) {
            System.out.println("invalid input");
        }
        return b;
    }
    
    // returns number of beats hit during the game
    public int getHits() {
        return (int) hits;
    }

    // (re)set the attack to its original state
    public void reset() {
        
        hits = 0.0;
        totalPress = 0.0;
        percent = 0.0;
        gameGrid = new MusicGrid(3, 30, 50, 20);
        overGrid = new OverGrid(3, 30, 50, 20, beats); 

        playing = true;

        requestFocusInWindow();
        
    }
    
    // triggers the postgame screen when all of the beats are gone
    public void postGame() {
        a_panel.switchScreen("score");
    }

    // tick method for the timer
    void tick() {
        if (playing) {
            // if no more beats left, go to postgame
            if (overGrid.numBeatsLeft() == 0) {
                playing = false;
                postGame();
            }
            // move the squares in the foreground
            overGrid.move();
            repaint();            
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameGrid.draw(g);
        overGrid.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}