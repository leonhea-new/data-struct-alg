package selfTry.ha;

public class BasicTry {

    final double addT(double a) {
        return a + 7;
    }

    public static void main(String[] args) {
        CirclrQueue c = new CirclrQueue(new String[] {"a", "b", "c"});
//        for (Method m : CirclrQueue.class.getMethod("toString"));
    }
}

class SubClass extends BasicTry {
    double addT(String a) {
        return Integer.getInteger(a) + 7;
    }

}