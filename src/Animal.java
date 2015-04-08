import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Animal extends Actor{
    protected int x, y;
    protected char symbol = 'X';
    protected int age;
    protected int energy = 10;

    public Animal() {
        x = 0;
        y = 0;
        age = RNG.nextInt(10);
    }

    public char getSymbol()
    {
        return symbol;
    }

    public void eat()
    {

    }

    public void die()
    {

    }

    public void giveBirth()
    {

    }

    public void move()
    {

    }

    @Override
    public void act() {
        die();
        eat();
        giveBirth();
        move();
    }
}
