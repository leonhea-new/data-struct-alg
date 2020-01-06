package personal.ha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BasicHeap {
    private char heapType = 'B';
    private int[] array;
    public int count;
    public int size;

    public BasicHeap(char type, int capacity) {
        if (type == 'B' || type == 'S') {
            heapType = type;
        } else {
            System.out.println(" type error, type set to be 'B'");
        }
        array = new int[capacity+1];
        size = capacity;
        count = 0;
    }

    private void swap(int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public void insert(int item) {
        if(count >= size) {
            System.out.println("out size error");
            return;
        }
        array[++count] = item;
        System.out.println(Integer.toString(item) + ", " +count);
        int pos = count;
        int fatherPos;
        while(pos>1){
            fatherPos = pos >> 1;
            if ((heapType == 'B' && array[pos] > array[fatherPos]) ||
                    (heapType == 'S' && array[pos] < array[fatherPos])) {
                swap(pos, fatherPos);
                pos = fatherPos;
            } else {
                break;
            }
        }
    }

    public int delete() {
        if (count <= 0) {
            System.out.println("not item less, can not delete");
            return -1;
        }
        int head = array[1];
        array[1] = array[count];
        array[count--] = 0;
        int pos = 1;
        int nextPos;
        while (pos <= count >> 1) {
            nextPos = pos;
            if ((heapType == 'B') && (array[pos] < array[pos * 2]) ||
                    (heapType == 'S') && (array[pos] > array[pos * 2])) {
                nextPos = pos * 2;
            }
            if ((heapType == 'B') && (array[nextPos] < array[pos * 2 + 1]) ||
                    (heapType == 'S') && (array[nextPos] > array[pos * 2 + 1])) {
                nextPos = pos * 2 + 1;
            }
            if (nextPos == pos) break;
            swap(pos, nextPos);
            pos = nextPos;
        }
        return head;
    }

    public String toString(){
        return Arrays.toString(array);
    }

    public static void main(String[] args) {
        BasicHeap heap = new BasicHeap('B', 5);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(3);
        heap.insert(4);
        heap.insert(4);
        System.out.println(heap);
        System.out.println(heap.delete());
        System.out.println(heap);
        System.out.println(heap.delete());
        System.out.println(heap);
        System.out.println(heap.delete());
        System.out.println(heap);
        System.out.println(heap.delete());
        System.out.println(heap);
        System.out.println(heap.delete());
        System.out.println(heap);
        System.out.println(heap.delete());
        System.out.println(heap);
    }
}
