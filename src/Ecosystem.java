import java.io.*;
import java.nio.file.Files;
import java.util.Random;

/**
 * Created by porrith on 4/6/15.
 */
public class Ecosystem {
    final private int dimension = 10;
    protected Cell field[][] = new Cell[dimension][dimension];

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
        System.out.println("\n");
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
        else if ((c == '@' || c == '!') && (objectChar == 'X'))
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
                field[x][y].objects = new Plant(x, y);
            }
            else if ((random == 3) && ((isAvail(x, y, '!'))))
            {
                field[x][y].animals = new Carnivore(x, y);
            }
            else if (isAvail(x, y, '@'))
            {
                field[x][y].animals = new Herbivore(x, y);
            }
        }
    }

    public void plantGen(int x, int y)
    {
        if (isAvail(x, y, '*'))
        {
            System.out.println(true);
            field[x][y].objects = new Plant(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        Ecosystem environ = new Ecosystem();
        environ.initSpawn();
        environ.printEco();
        for (int i = 0; i < 10; i++) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            environ.plantGen(x, y);
            environ.printEco();
            environ.writeFile();
        }

    }
}
