import java.util.Vector;

/**
 * Created by porrith on 4/8/15.
 */
public class Simulation {
    public static void main(String[] args) {
        Ecosystem forest = new Ecosystem();
        Animal dummy = new Animal();
        forest.setCoordinates(0, 6);
        forest.printEco();
        for (int i = 0; i < 6; i++) {
            dummy.move(forest);
            forest.printEco();
        }
    }
}
