import java.util.Random;
import java.util.Vector;

/**
 * Created by porrith on 4/6/15.
 */
public class Ecosystem {
    /**
     * Default size of the 2D array. Cannot be changed.
     */
    final private int dimension = 32;
    /**
     * 2D array of Cells.
     */
    private Cell field[][] = new Cell[dimension][dimension];
    /**
     * Vector of carnivores used as a tool for calculating movements, energy levels, etc.
     */
    private Vector<Carnivore> clist;
    /**
     * Vector of herbivores used as a tool for calculating movements, energy levels, etc.
     */
    private Vector<Herbivore> hlist;
    /**
     * Vector of Plants used as a tool for calculating distance.
     */
    private Vector<Plant> plist;
    /**
     * Stores the size of the vector of Carnivores.
     */
    private int csize;
    /**
     * Stores the size of the vector of Herbivores.
     */
    private int hsize;

    /**
     * Global RNG - Avoids calling new Random() frequently.
     */
    private Random RNG = new Random();

    /**
     * Default constructor generates an empty field.
     */
    public Ecosystem()
    {
        clist = new Vector<Carnivore>();
        hlist = new Vector<Herbivore>();
        plist = new Vector<Plant>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    /**
     * Prints the 2D Array.
     */
    public void printEco()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("[" + field[i][j].objects.getSymbol() + "|" + field[i][j].animals.getSymbol() + "] ");
            }
            System.out.println("\n");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    /**
     * Prints the size of of Storage container (Vector).
     */
    public void printList()
    {
        System.out.println("Carnivore: " + clist.size() + " Herbivore: " + hlist.size() + " Plant: " + plist.size() + "\n");
    }

    /**
     * Returns true if an object can be placed in the cell.
     * @param x X coordinate of the 2D Array.
     * @param y Y coordinate of the 2D Array.
     * @param c Character tht needs to be check
     * @return boolean
     */
    public boolean isAvail(int x, int y, char c)
    {
        char animalChar = field[y][x].animals.getSymbol();
        char objectChar = field[y][x].objects.getSymbol();
        if ((c == '#') && ((animalChar == 'X') && (objectChar == 'X')))
            return true;
        else if ((c == '*') && (objectChar == 'X'))
            return true;
        else if (((c == '!') || (c == '@')) && ((objectChar != '#')) && (animalChar == 'X'))
            return true;
        return false;
    }

    /**
     * Random generation of the start of the cycle.
     */
    public void initSpawn()
    {
        int amount = RNG.nextInt(1024);
        for (int i = 0; i < amount; i++) {
            int random = RNG.nextInt(5);
            int x = RNG.nextInt(dimension);
            int y = RNG.nextInt(dimension);
            if ((random == 0) && (isAvail(x, y, '#'))) {
                Obstacle rock = new Obstacle(x, y);
                field[y][x].objects = rock;
            }
            else if ((random == 1) && (isAvail(x, y, '*'))) {
                Plant plant = new Plant(x,y);
                field[y][x].objects = plant;
                plist.add(plant);
            }
            else if ((random == 3) && ((isAvail(x, y, '!'))))
            {
                Carnivore bear = new Carnivore(x, y);
                field[y][x].animals = bear;
                clist.add(bear);
            }
            else if ((random == 4) && (isAvail(x, y, '@')))
            {
                Herbivore rabbit = new Herbivore(x, y);
                field[y][x].animals = rabbit;
                hlist.add(rabbit);
            }
        }
    }

    /**
     * Generates a plant onto the 2D array at a random location.
     */
    public void plantGen()
    {
        int x = RNG.nextInt(dimension);
        int y = RNG.nextInt(dimension);
        if (isAvail(x, y, '*'))
        {
            Plant plant = new Plant(x, y);
            field[y][x].objects = plant;
            plist.add(plant);
        }
    }

    /**
     * Removes any Animal object whose energy level falls below a certain point.
     */
    public void killObject()
    {
        csize = clist.size();
        hsize = hlist.size();
        for (int i = csize - 1; i >= 0; i--) {
            if (clist.elementAt(i).getEnergy() <= 0)
            {
                int x = clist.elementAt(i).getX();
                int y = clist.elementAt(i).getY();
                field[y][x].animals = new Animal();
                clist.remove(i);
            }
        }
        for (int i = hsize - 1; i >= 0; i--) {
            if (hlist.elementAt(i).getEnergy() <= 0)
            {
                int x = hlist.elementAt(i).getX();
                int y = hlist.elementAt(i).getY();
                field[y][x].animals = new Animal();
                hlist.remove(i);
            }
        }
    }

    /**
     * Generates a random baby on the grid if age and energy are at required levels.
     */
    public void spawnBaby()
    {
        int energy;
        int age;
        int x;
        int y;
        csize = clist.size();
        hsize = hlist.size();
        for (int i = csize - 1; i >= 0; i--) {
            energy = clist.elementAt(i).getEnergy();
            age = clist.elementAt(i).getAge();
            if ((age > 20) && (energy >= 10))
            {
                x = RNG.nextInt(dimension);
                y = RNG.nextInt(dimension);
                if (isAvail(x, y, '!'))
                {
                    Carnivore cub = new Carnivore(x, y, 0);
                    field[y][x].animals = cub;
                    clist.add(cub);
                    System.out.println(true);
                    clist.elementAt(i).setEnergy(5);
                }
            }
        }
        for (int i = hsize - 1; i >= 0; i--)
        {
            energy = hlist.elementAt(i).getEnergy();
            age = hlist.elementAt(i).getAge();
            if ((age > 15) && (energy >= 8))
            {
                x = RNG.nextInt(dimension);
                y = RNG.nextInt(dimension);
                if (isAvail(x, y, '@'))
                {
                    Herbivore kit = new Herbivore(x, y, 1);
                    field[y][x].animals = kit;
                    hlist.add(kit);
                    hlist.elementAt(i).setEnergy(5);
                }
            }
        }
    }

    /**
     * Deducts 1 energy at each call.
     */
    public void loseEnergy()
    {
        csize = clist.size();
        hsize = hlist.size();
        int energy;
        for (int i = csize - 1; i >= 0; i--) {
            energy = clist.elementAt(i).getEnergy();
            clist.elementAt(i).setEnergy(energy - 1);
        }
        for (int i = hsize - 1; i >= 0; i--)
        {
            energy = hlist.elementAt(i).getEnergy();
            hlist.elementAt(i).setEnergy(energy - 1);
        }
    }

    /**
     * Randomly moves objects across the grid.
     */
    public void movement()
    {
        char carn = '!';
        char herb = '@';
        int x;
        int y;
        int random;
        Carnivore animal;
        Herbivore rabbit;
        for (int i = 0; i < clist.size(); i++) {
            random = RNG.nextInt(8);
            animal = clist.elementAt(i);
            if (random == 0) {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if (x + 1 < dimension) {
                    if (isAvail(x + 1, y, carn)) {
                        animal.setLocation(x + 1, y);
                        field[y][x + 1].animals = animal;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 1) {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if (x - 1 >= 0) {
                    if (isAvail(x - 1, y, carn)) {
                        animal.setLocation(x - 1, y);
                        field[y][x - 1].animals = animal;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 2)
            {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if (y + 1 < dimension)
                {
                    if (isAvail(x, y + 1, carn))
                    {
                        animal.setLocation(x, y + 1);
                        field[y + 1][x].animals = animal;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 3)
            {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if (y - 1 >= 0)
                {
                    if (isAvail(x, y - 1, carn))
                    {
                        animal.setLocation(x, y - 1);
                        field[y - 1][x].animals = animal;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 4)
            {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if ((x + 1 < dimension) && (y + 1 < dimension))
                {
                    animal.setLocation(x + 1, y + 1);
                    field[y + 1][x + 1].animals = animal;
                    field[y][x].animals = new Animal();
                }
            }
            if (random == 5)
            {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if ((x - 1 >= 0) && (y - 1 >= 0))
                {
                    animal.setLocation(x - 1, y - 1);
                    field[y - 1][x - 1].animals = animal;
                    field[x][y].animals = new Animal();
                }
            }
            if (random == 6)
            {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if ((x - 1 >= 0) && (y + 1 < dimension))
                {
                    animal.setLocation(x - 1, y + 1);
                    field[y + 1][x - 1].animals = animal;
                    field[y][x].animals = new Animal();
                }
            }
            else if (random == 7)
            {
                x = clist.elementAt(i).getX();
                y = clist.elementAt(i).getY();
                if ((x + 1 < dimension) && (y - 1 >= 0)) {
                    animal.setLocation(x + 1, y - 1);
                    field[y - 1][x + 1].animals = animal;
                    field[y][x].animals = new Animal();
                }
            }
        }
        for (int i = 0; i < hlist.size(); i++) {
            random = RNG.nextInt(8);
            rabbit = hlist.elementAt(i);
            if (random == 0) {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if (x + 1 < dimension) {
                    if (isAvail(x + 1, y, herb)) {
                        rabbit.setLocation(x + 1, y);
                        field[y][x + 1].animals = rabbit;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 1) {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if (x - 1 >= 0) {
                    if (isAvail(x - 1, y, herb)) {
                        rabbit.setLocation(x - 1, y);
                        field[y][x - 1].animals = rabbit;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 2)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if (y + 1 < dimension)
                {
                    if (isAvail(x, y + 1, herb))
                    {
                        rabbit.setLocation(x, y + 1);
                        field[y + 1][x].animals = rabbit;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 3)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if (y - 1 >= 0)
                {
                    if (isAvail(x, y - 1, herb))
                    {
                        rabbit.setLocation(x, y - 1);
                        field[y - 1][x].animals = rabbit;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 4)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if ((x + 1 < dimension) && (y + 1 < dimension))
                {
                    if (isAvail(x + 1, y + 1, herb)) {
                        rabbit.setLocation(x + 1, y + 1);
                        field[y + 1][x + 1].animals = rabbit;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            if (random == 5)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if ((x - 1 >= 0) && (y - 1 >= 0))
                {
                    if (isAvail(x - 1, y - 1, herb)) {
                        rabbit.setLocation(x - 1, y - 1);
                        field[y - 1][x - 1].animals = rabbit;
                        field[x][y].animals = new Animal();
                    }
                }
            }
            if (random == 6)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if ((x - 1 >= 0) && (y + 1 < dimension))
                {
                    if (isAvail(x - 1, y + 1, herb)) {
                        rabbit.setLocation(x - 1, y + 1);
                        field[y + 1][x - 1].animals = rabbit;
                        field[y][x].animals = new Animal();
                    }
                }
            }
            else if (random == 7)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if ((x + 1 < dimension) && (y - 1 >= 0)) {
                    if (isAvail(x + 1, y - 1, herb)) {
                        rabbit.setLocation(x + 1, y - 1);
                        field[y - 1][x + 1].animals = rabbit;
                        field[y][x].animals = new Animal();
                    }
                }
            }
        }
    }

    /**
     * Allows animals to move to where there is food and destroys the other object. Removes object from list.
     */
    public void eat()
    {
        double dist;
        int xStart, xEnd, yStart, yEnd;
        Carnivore bear;
        Herbivore rabbit;
        for (int i = clist.size() - 1; i >= 0; i--) {
            for (int j = hlist.size() - 1; j >= 0 ; j--) {
                xStart = clist.elementAt(i).getX();
                yStart = clist.elementAt(i).getY();
                xEnd = hlist.elementAt(j).getX();
                yEnd = hlist.elementAt(j).getY();
                dist = Math.sqrt((Math.pow((double)(xEnd - xStart), 2.0) + Math.pow((double)(yEnd - yStart), 2.0)));
                if (dist <= 1 && (clist.elementAt(i).getEnergy() <= 5))
                {
                    bear = clist.elementAt(i);
                    bear.setLocation(xEnd, yEnd);
                    bear.gainEnergy(RNG.nextInt(5));
                    field[yEnd][xEnd].animals = bear;
                    field[xStart][yStart].animals = new Animal();
                    hlist.remove(j);
                    break;
                }
            }
        }
        for (int i = hlist.size() - 1; i >= 0 ; i--) {
            for (int j = plist.size() - 1; j >= 0; j--) {
                xStart = hlist.elementAt(i).getX();
                yStart = hlist.elementAt(i).getY();
                xEnd = plist.elementAt(j).getX();
                yEnd = plist.elementAt(j).getY();
                dist = Math.sqrt((Math.pow((double)(xEnd - xStart), 2.0) + Math.pow((double)(yEnd - yStart), 2.0)));
                if (dist <= 1 && (hlist.elementAt(i).getEnergy() <= 8))
                {
                    rabbit = hlist.elementAt(i);
                    rabbit.setLocation(xEnd, yEnd);
                    rabbit.gainEnergy(RNG.nextInt(5));
                    field[yEnd][xEnd].animals = rabbit;
                    field[yStart][yEnd].animals = new Animal();
                    plist.remove(j);
                    break;
                }
            }
        }
    }

    /**
     * Allows manual spawning of Bears for testing purposes.
     * @param x X coordinate to place object on grid.
     * @param y Y coordinate to place object on grid.
     */
    public void makeBear(int x, int y)
    {
        Carnivore bear = new Carnivore(x, y);
        field[y][x].animals = bear;
        clist.add(bear);
    }

    /**
     * Allows manual spawning of Rabbits for testing purposes.
     * @param x X coordinate to place object on grid.
     * @param y Y coordinate to place object on grid.
     */
    public void makeRabbit(int x, int y)
    {
        Herbivore rabbit = new Herbivore(x, y);
        field[y][x].animals = rabbit;
        hlist.add(rabbit);
    }
}
