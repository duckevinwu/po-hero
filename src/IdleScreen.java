import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// screen that is displayed when the Attack game is not being played

@SuppressWarnings("serial")
class IdleScreen extends JPanel {

    IdleScreen() {
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(150, 600);
    }
}