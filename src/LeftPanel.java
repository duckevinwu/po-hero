import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

// panel that handles the character portion of the game

@SuppressWarnings("serial")
public class LeftPanel extends JPanel {
    
    private CharacterPanel cp;
    
    public LeftPanel(CharacterPanel charP) {
        setLayout(new CardLayout());
                
        cp = charP;

       add(cp, "character");            
    }
    
    // provides access to the CharacterPanel
    public CharacterPanel getCharPanel() {
        return cp;
    }
    
    // function to switch between the veiws in the CardLayout
    public void switchScreen(String name) {
        CardLayout c = (CardLayout) this.getLayout();
        c.show(this, name);
    }

}