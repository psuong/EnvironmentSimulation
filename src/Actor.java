import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Actor {
    /**
     * Default char symbol of the Actor.
     */
    protected char symbol = 'X';
    /**
     * Store the age of the Actor.
     */
    protected int age;
    /**
     * X Coordinates of the Actor.
     * Y Coordinates of the Actor.
     */
    protected int x, y;
    /**
     * Default energy level.
     */
    protected int energy = 10;
    /**
     * Default RNG - Avoids calling new Random() frequently.
     */
    protected Random RNG = new Random();

    /**
     * Default Actor constructor.
     */
    Actor()
    {
        x = 0;
        y = 0;
        age = RNG.nextInt(10);
    }

    /**
     * Returns the symbol of the actor.
     * @return char
     */
    public char getSymbol()
    {
        return symbol;
    }

    /**
     * Sets the symbol of the Actor.
     * @param c Pass a char to change the symbol.
     */
    public void setSymbol(char c)
    {
        symbol = c;
    }

    /**
     * Stores the x, y coordinates.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public void setCoord(int x, int y)
    {
        this.x = x;
        this.y= y;
    }

    /**
     * Returns the age of the Actor.
     * @return int
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Sets the age of the Actor.
     * @param age Pass an integer to change "age."
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Sets the energy level of the Actor.
     * @param energy Pass an integer to change energy level.
     */
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    /**
     * Default function for Animal functions
     * @param passField Pass an Ecosystem object to obtain necessary information.
     */
    public void act(Ecosystem passField)
    {

    }
}
