import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Animal extends Actor{
    protected int x, y;
    protected char symbol = 'X';
    protected int age = 0;
    protected int energy = 0;

    public Animal() {
        x = 0;
        y = 0;
        setCoord(0, 0);
        setSymbol(symbol);
        setEnergy(0);
        setAge(age);
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

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public void spawnBaby(Ecosystem ecosystem)
    {
        ecosystem.spawnBaby();
    }
}
