import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// JPanel that has the button that starts an Attack

@SuppressWarnings("serial")
class AttackMenu extends JPanel {

    AttackMenu() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
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