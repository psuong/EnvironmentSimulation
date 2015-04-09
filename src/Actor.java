import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Actor {
    protected char symbol = 'X';
    protected int age;
    protected int x, y;
    protected int energy = 10;
    protected Random RNG = new Random();

    Actor()
    {
        x = 0;
        y = 0;
        age = RNG.nextInt(10);
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

    public void act()
    {

    }

    public void spawnBaby()
    {

    }

    public void die(Ecosystem passField)
    {
        passField.killObject();
    }
}
