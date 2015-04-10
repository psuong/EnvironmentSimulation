import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Obstacle extends Actor {
    /**
     * X coordinate.
     * Y Coordinate.
     */
    private int x, y;
    /**
     * Age of the obstacle.
     */
    private int age;
    /**
     * Default char is "#".
     */
    private char symbol = '#';

    /**
     * Constructor to generate obstacles on a 2D array.
     * @param x X coordinate to place object on the grid.
     * @param y Y Coordinate to place object on the grid.
     */
    public Obstacle(int x, int y)
    {
        age = new Random().nextInt(50);
        this.x = x;
        this.y = y;
        setSymbol(symbol);
        setCoord(this.x, this.y);
        setAge(age);
    }
}
