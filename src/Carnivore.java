/**
 * Created by porrith on 4/7/15.
 */
public class Carnivore extends Animal {
    private int x, y;
    private char symbol = '!';
    private int energy = 0;
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
        setCoord(this.x, this.y);
        setEnergy(energy);
        setAge(age);
        setSymbol(symbol);
    }

    public Carnivore(int x, int y, int age)
    {
        this.x = x;
        this.y = y;
        this.age = age;
        energy = 3;
        setCoord(this.x, this.y);
        setEnergy(energy);
        setAge(age);
        setSymbol(symbol);
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

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
        setCoord(x, y);
    }

}
