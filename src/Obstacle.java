import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Obstacle extends Actor {
    private int x, y;
    private int age;
    private char symbol = '#';

    public Obstacle()
    {
        x = 0;
        y = 0;
        age = new Random().nextInt(50);
    }

    public Obstacle(int x, int y)
    {
        this.x = x;
        this.y = y;
        age = new Random().nextInt(50);
        setSymbol(symbol);
        setCoord(this.x, this.y);
        setAge(age);
    }
}
