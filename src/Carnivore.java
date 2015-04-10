/**
 * Created by porrith on 4/7/15.
 */
public class Carnivore extends Animal {
    /**
     * Coordinates to store.
     */
    private int x, y;
    /**
     * Symbol representation of the Carnivore.
     */
    private char symbol = '!';
    /**
     * Default energy value is 10.
     */
    private int energy = 10;
    /**
     * Age of the Carnivore.
     */
    private int age;

    /**
     * Constructor to make a Carnivore at a specific location.
     * @param x X coordinate.
     * @param y Y coordinate.
     */

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

    /**
     * Overloaded constructor for baby spawns as the age cannot be random.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param age Age of the Carnivore.
     */
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

    /**
     * Returns the energy of the object.
     * @return int
     */
    public int getEnergy()
    {
        return energy;
    }

    /**
     * Returns the X coordinate of the object.
     * @return X
     */
    public int getX()
    {
        return x;
    }

    /**
     * Returns the Y coordinate of the object.
     * @return int
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the location of the object. Calls it's parent class' setCoord(int x, int y) and setEnergy(int energy) methods.
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
        setCoord(x, y);
        setEnergy(energy);
    }

    /**
     * Allows the object to gain energy.
     * @param e Amount of energy gained.
     */
    public void gainEnergy(int e)
    {
        this.energy += e;
        setEnergy(energy);
    }

}
