import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by porrith on 4/6/15.
 */
public class Ecosystem {
    final private int dimension = 5;
    protected Cell field[][] = new Cell[dimension][dimension];

    private Vector<Carnivore> clist;
    private Vector<Herbivore> hlist;
    private Vector<Plant> plist;
    private Vector<Obstacle>olist;

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
        System.out.println("-------------------------------------------------------------------------------------------");
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
        int amount = RNG.nextInt(50);
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
            else if (isAvail(x, y, '@'))
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
        for (int i = 0; i < clist.size(); i++) {
            if (clist.elementAt(i).getEnergy() <= 0)
            {
                int x = clist.elementAt(i).getX();
                int y = clist.elementAt(i).getY();
                field[x][y].animals = null;
                clist.remove(i);
            }
        }
        for (int i = 0; i < hlist.size(); i++) {
            if (hlist.elementAt(i).)
        }
    }
}
