/**
 * Created by porrith on 4/8/15.
 */
public class Simulation {
    public static void main(String[] args) {
        Ecosystem forest = new Ecosystem();
        Animal dummy = new Animal();
        forest.initSpawn();
        forest.printEco();
        forest.printList();
        for (int i = 0; i < 30; i++) {
            forest.plantGen();
            dummy.act(forest);
            forest.printEco();
            forest.printList();
        }
    }
}
