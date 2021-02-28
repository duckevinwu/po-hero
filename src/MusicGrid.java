import java.awt.*;

/**
 * The bottom grid that represents the playing field of the beat mini game
 * Represented by a 2D array
 */
public class MusicGrid {

    private Color color;
    private Square[][] grid;
    private int width;
    private int height;

    public MusicGrid(int width, int height, int sqW, int sqH) {
        grid = new GridSquare[height][width];
        this.width = width;
        this.height = height;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {     
                if (j == height - 1) {
                    grid[j][i] = new GridSquare(Color.MAGENTA, i, j, sqW, sqH);
                }
                else {
                    grid[j][i] = new GridSquare(Color.BLACK, i, j, sqW, sqH);
                }
            }
        }         
    }

    public void draw(Graphics g) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {   
                grid[j][i].draw(g);
            }
        }
    }
}