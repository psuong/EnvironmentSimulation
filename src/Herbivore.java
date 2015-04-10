/**
 * Created by porrith on 4/7/15.
 */
public class Herbivore extends Animal {
    /**
     * X coordinate
     * Y coordinate
     */
    private int x, y;
    /**
     * Default symbol of an Herbivore "@."
     */
    private char symbol = '@';
    /**
     * Default energy level of an Herbivore.
     */
    private int energy = 10;
    /**
     * Age variable.
     */
    private int age;

    /**
     * Constructor takes in coordinates and stores them
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Herbivore(int x, int y)
    {
        this.x = x;
        this.y = y;
        age = RNG.nextInt(10);
        setCoord(this.x, this.y);
        setAge(age);
        setEnergy(energy);
        setSymbol(symbol);
    }

    /**
     * Overloaded constructor for baby spawns as the age cannot be random.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param age Stores the age of the Herbivore.
     */
    public Herbivore(int x, int y, int age)
    {
        this.x = x;
        this.y = y;
        this.age = age;
        energy = 3;
    }

    /**
     * Returns the x coordinate
     * @return int
     */
    public int getX()
    {
        return x;
    }

    /**
     * Retrusn the y coordinate
     * @return int
     */
    public int getY()
    {
        return y;
    }

    /**
     * Returns the current energy level.
     * @return int
     */
    public int getEnergy()
    {
        return energy;
    }

    /**
     * Sets the energy level.
     * @param energy Pass an integer to set the energy level.
     */
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    /**
     * Sets the location of the Herbivore. Calls parent class' method; SetCoord(int x, int y);
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
        setCoord(x, y);
    }

    /**
     * Sets the energy levl of the Object. Calls the parent class' method setEnergy(int e).
     * @param e Pass an integer to add a value to the current energy.
     */
    public void gainEnergy(int e)
    {
        this.energy += e;
        setEnergy(energy);
    }
}
