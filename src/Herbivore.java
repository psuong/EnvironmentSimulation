/**
 * Created by porrith on 4/7/15.
 */
public class Herbivore extends Animal {
    private int x, y;
    private char symbol = '@';
    private int energy = 20;
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
        //age = RNG.nextInt(10);
        age = 20;
        setCoord(this.x, this.y);
        setAge(age);
        setEnergy(energy);
        setSymbol(symbol);
    }

    public Herbivore(int x, int y, int age)
    {
        this.x = x;
        this.y = y;
        this.age = age;
        energy = 3;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getEnergy()
    {
        return energy;
    }
}
