package selfTry.ha;


import java.util.LinkedList;
import java.util.Queue;

public class Shiyan {
    public static void main(String[] args) {
        Queue<ACNode> currentLevel = new LinkedList<>();
        currentLevel.add(new ACNode('a', 0));
        currentLevel
    .add(new ACNode('b', 0));
        System.out.

                println(currentLevel.poll());
        System.out.println(currentLevel.poll());
        System.out.println(currentLevel.poll());
    }
}
