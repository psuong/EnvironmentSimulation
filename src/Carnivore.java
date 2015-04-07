import java.util.Random;
import java.util.Vector;

/**
 * Created by porrith on 4/7/15.
 */
public class Carnivore extends Animal {
    private int x, y;
    private char symbol = '!';
    private int energy;
    private int age;
    private static Vector<Carnivore> clist = new Vector<Carnivore>();

    public Carnivore()
    {
        x = 0;
        y = 0;
        energy = new Random().nextInt(50);
        age = new Random().nextInt(10);
    }

    public Carnivore(int x, int y)
    {
        this.x = x;
        this.y = y;
        energy = new Random().nextInt(50);
        age = new Random().nextInt(50);
        setSymbol(symbol);
        setCoord(this.x, this.y);
        setAge(age);
        setEnergy(energy);
    }

    public void addCarn (Carnivore c)
    {
        clist.add(c);
    }
}
