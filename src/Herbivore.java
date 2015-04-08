import java.util.Random;

/**
 * Created by porrith on 4/7/15.
 */
public class Herbivore extends Animal {
    private int x, y;
    private char symbol = '@';
    private int energy = 10;
    private int age;

    public Herbivore()
    {
        x = 0;
        y = 0;
        age = RNG.nextInt(10);
    }

    public Herbivore(int x, int y)
    {
        this.x = x;
        this.y = y;
        age = RNG.nextInt(10);
    }

    public int getX()
    {
        return x;
    }

    public
}
