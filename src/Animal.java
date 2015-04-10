/**
 * Created by porrith on 4/6/15.
 */
public class Animal extends Actor{
    protected int x, y;
    protected char symbol = 'X';
    protected int age = 0;
    protected int energy = 0;

    /**
     * Default constructor for an Animal.
     */
    public Animal() {
        x = 0;
        y = 0;
        setCoord(0, 0);
        setSymbol(symbol);
        setEnergy(energy);
        setAge(age);
    }

    /**
     * Returns symbol of the current object.
     * @return char
     */
    public char getSymbol()
    {
        return symbol;
    }

    /**
     * Sets the symbol of the current object
     * @param c Symbol representation of Animal.
     */
    public void setSymbol(char c)
    {
        symbol = c;
    }

    /**
     * Sets the coordinates to store in the object.
     * @param x X coordinate to store.
     * @param y Y coordinate to store.
     */
    public void setCoord(int x, int y)
    {
        this.x = x;
        this.y= y;
    }

    /**
     * Returns the age of the object.
     * @return int
     */

    public int getAge()
    {
        return age;
    }

    /**
     * Sets the object's age.
     * @param age Pass an integer to set the age.
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Sets the energy level of the object.
     * @param energy Pass an inteer to set the energy level.
     */
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    /**
     * Overrides the parent class (Actor). Performs all tasks of the animal.
     * @param passField Pass an ecosystem as a reference as Animals should do the tasks.
     */
    @Override
    public void act(Ecosystem passField) {
        move(passField);
        loseEnergy(passField);
        eat(passField);
        spawnBaby(passField);
        die(passField);
    }

    /**
     * Spawns a random baby.
     * @param passField Pass Ecosystem object to determine available spaces.
     */
    public void spawnBaby(Ecosystem passField)
    {
        passField.spawnBaby();
    }

    /**
     * Removes objects with energy <= 0.
     * @param passField Pass Ecosystem object to determine which coordinates to consider and remove.
     */
    public void die(Ecosystem passField)
    {
        passField.killObject();
    }

    /**
     * Animals lose energy every turn.
     * @param passField Pass Ecosystem object to find all Animal objects.
     */
    public void loseEnergy (Ecosystem passField)
    {
        passField.loseEnergy();
    }

    /**
     * Allows Animals to consume other objects.
     * @param passField Pass Ecosystem object to find the closes object the Animal can consume.
     */
    public void eat(Ecosystem passField)
    {
        passField.eat();
    }

    /**
     * Allows Animals to move randomly.
     * @param passField Pass Ecosystem to determine available spots to move in.
     */
    public void move(Ecosystem passField)
    {
        passField.movement();
    }
}
