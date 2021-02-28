import java.awt.*;

/**
 * A square object that moves along the playing grid
 */
public class MoveSquare implements Square{

    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int order;
    private Color color;
    private int row;
    private int column;
    
    public MoveSquare(int order, int column, int row, int width, int height) {
        if (column == 0) {
            this.color = Color.YELLOW;
        }
        else if (column == 1) {
            this.color = Color.BLUE;
        }
        else {
            this.color = Color.RED;
        }
        this.column = column;
        this.row = row;
        this.order = order;
        this.width = width;
        this.height = height;
        this.xPos = column * width;
        this.yPos = row * height + order;
    }
    
    //moves the square along the 2D array
    public void move() {
        order = order - 1;
        this.yPos = row * height - (order * 20) + 10;
    }
    
    //checks if the square is on playing grid
    public boolean exists() {
        if (yPos >= 0) {
            return true;
        }
        return false;
    }
    
    //returns which column the square is in
    public int colNum() {
        return column;
    }
    
    //returns the y position of the square
    public int yPosition() {
        return yPos;
    }

    public void draw(Graphics g) {
        if (yPos >= 0) {
            g.setColor(this.color);
            g.fillRect(xPos, yPos, width, height);
        }
    }
}