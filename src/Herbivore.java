import java.util.Random;

/**
 * Created by porrith on 4/7/15.
 */
public class Herbivore extends Animal {
    private int x, y;
    private char symbol = '@';
    private int energy;
    private int age;

    public Herbivore()
    {
        x = 0;
        y = 0;
        energy = new Random().nextInt(50);
        age = new Random().nextInt(10);
    }

    public Herbivore(int x, int y)
    {
        this.x = x;
        this.y = y;
        energy = new Random().nextInt(50);
        age = new Random().nextInt(10);
        setSymbol('@');
        setCoord(this.x ,this.y);
        setAge(age);
        setEnergy(energy);
    }

    public void scanList()
    {

    }
}
