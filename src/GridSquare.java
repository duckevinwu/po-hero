import java.awt.*;

/**
 * A square object that draws itself. Used in creating the background for the 
 * playing field.
 */
public class GridSquare implements Square {

    private Color color;
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    public GridSquare(Color color, int column, int row, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.xPos = column * width;
        this.yPos = row * height;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        if (yPos == 580) {
            g.drawRect(xPos, yPos, width, height);
        }
        else {
            g.fillRect(xPos, yPos, width, height);
        }
    }
}