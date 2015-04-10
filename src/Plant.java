import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Plant extends Actor {
    private int x,y;
    private char symbol = '*';
    private int age;

    Plant()
    {
        x = 0;
        y = 0;
        age = new Random().nextInt(10);
    }

    Plant(int x, int y)
    {
        this.x = x;
        this.y = y;
        age = new Random().nextInt(10);
        setSymbol(symbol);
        setCoord(this.x, this.y);
        setAge(age);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
