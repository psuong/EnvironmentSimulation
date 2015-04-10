import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by porrith on 4/6/15.
 */
public class Ecosystem {
    final private int dimension = 7;
    private Cell field[][] = new Cell[dimension][dimension];

    private Vector<Carnivore> clist;
    private Vector<Herbivore> hlist;
    private Vector<Plant> plist;
    private Vector<Obstacle>olist;

    private int csize;
    private int hsize;
    private int psize;

    private Random RNG = new Random();

    public Ecosystem()
    {
        clist = new Vector<Carnivore>();
        hlist = new Vector<Herbivore>();
        plist = new Vector<Plant>();
        olist = new Vector<Obstacle>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = new Cell();
            }
        }
    }

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

    public void printList()
    {
        System.out.println("Carnivore: " + clist.size() + " Herbivore: " + hlist.size() + " Plant: " + plist.size() + "\n");
    }

    public void writeFile() throws IOException{
        PrintWriter write = new PrintWriter("out.txt", "UTF-8");
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                String line = "[" + field[i][j].objects.getSymbol() + "|" + field[i][j].animals.getSymbol() + "] ";
                write.append(line);
            }
            write.append("\n");
        }
        write.close();
    }

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

    public void initSpawn()
    {
        int amount = RNG.nextInt(20);
        for (int i = 0; i < amount; i++) {
            int random = RNG.nextInt(5);
            int x = RNG.nextInt(dimension);
            int y = RNG.nextInt(dimension);
            if ((random == 0) && (isAvail(x, y, '#'))) {
                Obstacle rock = new Obstacle(x, y);
                field[y][x].objects = rock;
                olist.add(rock);
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
            if ((age > 15) && (energy > 10))
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
            if ((age > 15) && (energy > 10))
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

    public void loseEnergy()
    {
        csize = clist.size();
        hsize = hlist.size();
        int energy;
        for (int i = csize - 1; i >= 0; i--) {
            energy = clist.elementAt(i).getEnergy();
            clist.elementAt(i).setEnergy(energy - 1);
            //System.out.println(energy);
        }
        for (int i = hsize - 1; i >= 0; i--)
        {
            energy = hlist.elementAt(i).getEnergy();
            hlist.elementAt(i).setEnergy(energy - 1);
            //System.out.println(energy);
        }
    }

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
                System.out.println("x value: " + x);
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
                System.out.println("x value: " + x);
                if (x + 1 < dimension) {
                    if (isAvail(x + 1, y, carn)) {
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
                    if (isAvail(x - 1, y, carn)) {
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
                    if (isAvail(x, y + 1, carn))
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
                    if (isAvail(x, y - 1, carn))
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
                    rabbit.setLocation(x + 1, y + 1);
                    field[y + 1][x + 1].animals = rabbit;
                    field[y][x].animals = new Animal();
                }
            }
            if (random == 5)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if ((x - 1 >= 0) && (y - 1 >= 0))
                {
                    rabbit.setLocation(x - 1, y - 1);
                    field[y - 1][x - 1].animals = rabbit;
                    field[x][y].animals = new Animal();
                }
            }
            if (random == 6)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if ((x - 1 >= 0) && (y + 1 < dimension))
                {
                    rabbit.setLocation(x - 1, y + 1);
                    field[y + 1][x - 1].animals = rabbit;
                    field[y][x].animals = new Animal();
                }
            }
            else if (random == 7)
            {
                x = hlist.elementAt(i).getX();
                y = hlist.elementAt(i).getY();
                if ((x + 1 < dimension) && (y - 1 >= 0)) {
                    rabbit.setLocation(x + 1, y - 1);
                    field[y - 1][x + 1].animals = rabbit;
                    field[y][x].animals = new Animal();
                }
            }
        }
    }
    public void setCoordinates(int x, int y)
    {
        Carnivore bear = new Carnivore(x, y);
        field[y][x].animals = bear;
        clist.add(bear);
    }
}
