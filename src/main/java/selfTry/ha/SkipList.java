package selfTry.ha;


import java.util.Random;

public class SkipList {

    public  int MAX_LEVEL = 16;
    private Random random = new Random();
    Node headNode = new Node(-1, MAX_LEVEL);

    public SkipList(int maxLevel) {
        MAX_LEVEL = maxLevel;
        headNode = new Node(-1, MAX_LEVEL);
    }

    public Node findNode(double value) {
        Node p = headNode;
        int i;
        for ( i = MAX_LEVEL - 1; i>=0; i--) {
            while (p.next[i] != null && p.next[i].data < value) {
                p = p.next[i];
            }
            System.out.println(i + " " + p.data);
        }
        if (p.next[0] != null && p.next[0].data == value) {
            return p.next[0];
        } else {
            return null;
        }
    }

    public void insert(double value) {
        Node p = headNode;
        int i;
        int nodeLevel = randomLevle();
        Node insertNode = new Node(value,nodeLevel);

        for (i = MAX_LEVEL - 1; i >= 0; i--) {
            while(p.next[i] != null && p.next[i].data < value) p = p.next[i];
            if (i < nodeLevel) {
                Node nextNode = p.next[i];
                p.next[i] = insertNode;
                insertNode.next[i] = nextNode;
            }
        }
    }

    int randomLevle() {
        int level = 0;
        for (int i=0; i<MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    void printAll() {
        Node p = headNode;
        StringBuilder sb = new StringBuilder();
        while (p.next[0] != null) {
            sb.append(p.next[0]);
            p = p.next[0];
        }
        System.out.println(sb);
    }

    class Node {
        double data;
        Node[] next ;
        int maxLevel;

        Node(double data, int maxLevel) {
            this.data = data;
            this.maxLevel = maxLevel;
            this.next = new Node[maxLevel];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(data);
            sb.append("(");
            sb.append(maxLevel);
            sb.append(") ");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        SkipList s = new SkipList(20);
        s.insert(30);
        s.insert(4);
        s.insert(90);
        s.insert(50);
        s.printAll();
        System.out.println(s.findNode(50));

    }
}
