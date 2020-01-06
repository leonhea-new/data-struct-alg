package personal.ha;

/**
 * Hello world!
 *
 */
public class CirclrQueue <T> {
    T[] items;
    int head;
    int tail;
    int length;

    CirclrQueue(T[] items){
        this.items = items;
        head = 0;
        length = items.length;
        tail = length - 1;
    }

    public void put(T item) {
        if( (tail + 1) % length == head){
            System.err.println(" no space to add item");
        } else {
            System.out.println("success put one item");
            tail = (tail + 1 )% length;
            items[tail] = item;
        }
    }

    public T get() {
        if (head == tail){
            System.out.println("no item in queue");
            return null;
        } else {
            System.out.println("success get on item");
            T get_item = items[head];
            head = (head + 1) % length;
            return get_item;
        }
    }

    @Override
    public String toString() {
        String result = "[";
        for (Object item : items) {
            result += item.toString();
            result += ", ";
        }
        result += " ]";
        return result;
    }

    public static void main(String[] args ) {
        CirclrQueue c = new CirclrQueue(new String[]{"a", "b", "c"});
        System.out.println(c.get());
        System.out.println(c.get());
        System.out.println(c.get());
        c.put("add 1");
        System.out.println(c);

    }
}
