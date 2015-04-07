import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Animal {
    protected int x, y;
    protected char symbol;
    protected int age;
    protected int energy;

    public Animal() {
        x = 0;
        y = 0;
        symbol = 'X';
        age = new Random().nextInt(10);
        energy = new Random().nextInt(10);
    }

    public char getSymbol()
    {
        return symbol;
    }

    public void setSymbol(char c)
    {
        symbol = c;
    }

    public void setCoord(int x, int y)
    {
        this.x = x;
        this.y= y;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
    }
}
