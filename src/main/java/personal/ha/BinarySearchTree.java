package personal.ha;

public class BinarySearchTree {
    Node head;

    BinarySearchTree(int data) {
        head = new Node(data);
    }

    void insert(int value) {
        Node s = head;
        while (true) {
            if (value < s.data) {
                if (s.right == null) {
                    s.right = new Node(value);
                    break;
                }else{
                    s = s.right;
                }
            }else{
                if(s.left == null) {
                    s.left = new Node(value);
                    break;
                }else {
                    s = s.left;
                }
            }
        }
    }

    Node find(int value) {
        Node s = head;
        while (s != null){
            if (value > s.data){
                s = s.left;
            }else if(value < s.data) {
                s = s.right;
            }else {
                return s;
            }
        }
        return null;
    }

    void delete(int value) {
        Node n = this.find(value);
        if (n == null) return;

    }

    public String toString(){
        return head.toString();
    }


    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            StringBuilder s =
                    new StringBuilder("     ");
            s.append(Integer.toString(data));
            s.append("     \n");
            s.append("   ");
            if (right != null)
                s.append(Integer.toString(right.data));
            else
                s.append("N");
            s.append("   ");
            if (left != null)
                s.append(Integer.toString(left.data));
            else
                s.append("N");
            s.append("  \n");
            return s.toString();
        }
    }

    public static void main(String[] args) {
        Node h = new Node(100);
        BinarySearchTree tree = new BinarySearchTree(100);
        tree.insert(50);
        tree.insert(30);
        tree.insert(90);
        tree.insert(80);
        System.out.println(tree.find(50));
//        System.out.println(h.left == null);
    }
}
