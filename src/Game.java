import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    
    public void run() {
        
        // The overall JFrame
        final JFrame frame = new JFrame("Po Hero");
        frame.setLocation(200, 100);
        
        final CharacterPanel charPanel = new CharacterPanel(20, 0);

        // Character Panel
        final LeftPanel l_panel = new LeftPanel(charPanel);
        frame.add(l_panel, BorderLayout.WEST);

        // Enemy Display Panel
        final CenterPanel center_panel = new CenterPanel(l_panel);
        frame.add(center_panel, BorderLayout.CENTER);
        
        // Action Panel
        final ActionPanel action_panel = new ActionPanel(center_panel);        
        frame.add(action_panel, BorderLayout.EAST);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        

    }
    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}