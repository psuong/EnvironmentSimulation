import java.util.Vector;

/**
 * Created by porrith on 4/8/15.
 */
public class Simulation {
    public static void main(String[] args) {
        Ecosystem forest = new Ecosystem();
        Actor dummy = new Actor();
        forest.initSpawn();
        forest.printEco();
        forest.printList();
        dummy.die(forest);
        forest.printEco();
        forest.printList();
        dummy.spawnBaby();
        forest.printEco();
        forest.printList();
    }
}
