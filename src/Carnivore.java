import java.util.Random;
import java.util.Vector;

/**
 * Created by porrith on 4/7/15.
 */
public class Carnivore extends Animal {
    private int x, y;
    private char symbol = '!';
    private int energy = 10;
    private int age;

    public Carnivore()
    {
        x = 0;
        y = 0;
        age = RNG.nextInt(10);
    }

    public Carnivore(int x, int y)
    {
        this.x = x;
        this.y = y;
        age = RNG.nextInt(10);
    }

    public void Carnivore(int x, int y, Ecosystem passField)
    {
        this.x = x;
        this.y = y;
        age = RNG.nextInt(10);
    }

    public int getEnergy()
    {
        return energy;
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
