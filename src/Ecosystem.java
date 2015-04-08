import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by porrith on 4/6/15.
 */
public class Ecosystem {
    final private int dimension = 5;
    protected Cell field[][] = new Cell[dimension][dimension];
    Container holdPos = new Container();

    public Ecosystem()
    {
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
        int amount = new Random().nextInt(50);
        for (int i = 0; i < amount; i++) {
            int random = new Random().nextInt(5);
            int x = new Random().nextInt(dimension);
            int y = new Random().nextInt(dimension);
            if ((random == 0) && (isAvail(x, y, '#'))) {
                field[x][y].objects = new Obstacle(x, y);
            }
            else if ((random == 1) && (isAvail(x, y, '*'))) {
                Plant plant = new Plant(x,y);
                field[x][y].objects = plant;
                holdPos.addPlant(plant);
            }
            else if ((random == 3) && ((isAvail(x, y, '!'))))
            {
                Carnivore bear = new Carnivore(x, y);
                field[x][y].animals = bear;
                holdPos.addCarn(bear);
            }
            else if (isAvail(x, y, '@'))
            {
                Herbivore rabbit = new Herbivore(x, y);
                field[x][y].animals = rabbit;
                holdPos.addHerb(rabbit);
            }
        }
    }

    public void plantGen(int x, int y)
    {
        if (isAvail(x, y, '*'))
        {
            Plant plant = new Plant(x, y);
            field[x][y].objects = plant;
            holdPos.addPlant(plant);
        }
    }

    public static void main(String[] args) throws IOException {
        Ecosystem environ = new Ecosystem();
        environ.initSpawn();
        environ.printEco();
        /*for (int i = 0; i < 10; i++) {
            int x = new Random().nextInt(32);
            int y = new Random().nextInt(32);
            environ.plantGen(x, y);
            environ.printEco();
            //    environ.writeFile();
        }*/
        System.out.println(environ.holdPos.getSize());
    }
}
