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
        System.out.println("Carnivore: " + clist.size() + " Herbivore: " + hlist.size() + " Plant: " + plist.size());
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
        char animalChar = field[x][y].animals.getSymbol();
        char objectChar = field[x][y].objects.getSymbol();
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
                field[x][y].objects = rock;
                olist.add(rock);
            }
            else if ((random == 1) && (isAvail(x, y, '*'))) {
                Plant plant = new Plant(x,y);
                field[x][y].objects = plant;
                plist.add(plant);
            }
            else if ((random == 3) && ((isAvail(x, y, '!'))))
            {
                Carnivore bear = new Carnivore(x, y);
                field[x][y].animals = bear;
                clist.add(bear);
            }
            else if ((random == 4) && (isAvail(x, y, '@')))
            {
                Herbivore rabbit = new Herbivore(x, y);
                field[x][y].animals = rabbit;
                hlist.add(rabbit);
            }
        }
    }

    public void plantGen(int x, int y)
    {
        if (isAvail(x, y, '*'))
        {
            Plant plant = new Plant(x, y);
            field[x][y].objects = plant;
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
                System.out.println(true + " carnivore");
                int x = clist.elementAt(i).getX();
                int y = clist.elementAt(i).getY();
                field[x][y].animals = new Animal();
                clist.remove(i);
            }
        }
        for (int i = hsize - 1; i >= 0; i--) {
            if (hlist.elementAt(i).getEnergy() <= 0)
            {
                System.out.println(true + " herbivore");
                int x = hlist.elementAt(i).getX();
                int y = hlist.elementAt(i).getY();
                field[x][y].animals = new Animal();
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
                    Carnivore cub = new Carnivore(x, y, 1);
                    field[x][y].animals = cub;
                    clist.add(cub);
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
                    field[x][y].animals = kit;
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
}
