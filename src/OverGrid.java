import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

/**
 * The grid that contains the moving squares. This grid is the foreground and 
 * a MusicGrid is the background. The "beats" are represented by a subtype of
 * square called a MoveSquare connected in a linked list. Represented by a 2D
 * array.
 */

public class OverGrid {

    private Color color;
    private Square[][] grid;
    private int width;
    private int height;
    private List<MoveSquare> beatList;
    
    public OverGrid(int width, int height, int sqW, int sqH, int numBeats) {
        beatList = new LinkedList<MoveSquare>();        
        for (int i = 0; i < numBeats; i++) {
            double choice = Math.random();
            int col = 0;
            if (choice < 0.33) {
                col = 0;
            }
            else if (choice < 0.66) {
                col = 1;
            }
            else {
                col = 2;
            }
            MoveSquare x = new MoveSquare(10 + (i * 3), col, 0, sqW, sqH);
            beatList.add(x);
        }
        this.width = width;
        this.height = height;
    }
    
    // returns whether or not a square exists in the hitbox for column 1
    public boolean colOne() {
        for (MoveSquare ms : beatList) {
            if (ms.colNum() == 0 && ms.yPosition() >= 570 && ms.yPosition() <= 600) {
                beatList.remove(ms);
                return true;
            }
        }
        return false;
    }
    
    // returns whether or not a square exists in hitbox for column 2
    public boolean colTwo() {
        for (MoveSquare ms : beatList) {
            if (ms.colNum() == 1 && ms.yPosition() >= 570 && ms.yPosition() <= 600) {
                beatList.remove(ms);
                return true;
            }
        }
        return false;
    }
    
    // returns whether or not a square exists in hitbox for column 3
    public boolean colThree() {
        for (MoveSquare ms : beatList) {
            if (ms.colNum() == 2 && ms.yPosition() >= 570 && ms.yPosition() <= 600) {
                beatList.remove(ms);
                return true;
            }
        }
        return false;
    }
    
    // returns number of beats lift in the beat list
    public int numBeatsLeft() {
        return beatList.size();
    }
    
    // moves the beats in the linked list; removes them from the list if the
    // beat has moved off the playing field
    public void move() {
        Iterator<MoveSquare> i = beatList.iterator();
        while (i.hasNext()) {
            MoveSquare ms = i.next();
            ms.move();
            if (ms.yPosition() >= 600) {
                i.remove();
            }
        }
    }
    

    public void draw(Graphics g) {
        for (MoveSquare ms : beatList) {
            ms.draw(g);
        }
    }
}