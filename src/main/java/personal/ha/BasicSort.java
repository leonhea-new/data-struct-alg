package personal.ha;


import java.util.Arrays;
import java.util.Random;

public class BasicSort {
    int count = 0;

    void testStack(){
        count ++;
        testStack();
    }

    static void insertSort(double[] array) {
        int len = array.length;
        int times = 0;
        for (int i=1; i<len; i++) {
            double value = array[i];
            for (int j = i-1; j>=0; j--) {
                times ++;
                if (value < array[j]){
                    array[j + 1]= array[j];
                } else{
                    array[j + 1] = value;
                    break;
                }
            }
        }
        System.out.println(times);
    }

    static void swap(double[] a, int pos1, int pos2) {
        double value = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = value;
    }

    static void subQuickSort(double[] array, int s, int n){
//        count ++;
//        System.out.println("******************stack :" + count);
        int lt = s;
        int gt = n;
        int i = s + 1;
        double splitValue = array[s];

        // < , =, > 3 split
//        while (i <= gt) {
//            if (array[i] < splitValue) {
//                swap(array, i++, lt++);
//            } else if (array[i] > splitValue) {
//                swap(array, i , gt--);
//            } else {
//                i++;
//            }
//        }
        // <, >=  2 split
        while (true) {
            while (array[--gt] >= splitValue) if (gt == s) break;
            while (array[++lt] <= splitValue) if (lt == n) break;
            if (lt >= gt) break;
            swap(array, lt, gt);
        }
        swap(array, s, gt);

//        System.out.println(""+s+" - "+ (gt-1) + ", "+ (gt + 1) + " - "+n);
//        System.out.println(Arrays.toString(array));
        if (gt -1 > s)
            subQuickSort(array,s,gt - 1);
        if  (gt + 1 < n)
            subQuickSort(array, gt+1, n);
    }

    static void subQuickSort2(double[] array, int s, int n){
        while(true) {
            double split_value = array[n];

        }
    }

    static void quickSort(double[] array) {
        if (array.length == 1){
            return;
        } else if (array.length == 2) {
            if (array[1] < array[0]) {
                double tmp = array[1];
                array[1] = array[0];
                array[0] = tmp;
            }
        }
        subQuickSort(array, 0, array.length - 1);
    }

    static void heapSort(double[] array) {


    }

    static int binarySearch(double[] sorted_array, int start, int end,  double value) {
        int mid;
        while ( start <= end) {
            mid = (start + end) >> 1;
            System.out.println(start + ", " + end);
            if (sorted_array[mid] < value) {
                start = mid + 1;
            } else  if (sorted_array[mid] > value) {
                end = mid - 1;
            } else {
                if (mid == 0 || sorted_array[mid - 1] == value) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[]  arr = new Random().ints(0,10000).limit(10000).toArray();
        double[] new_arr = Arrays.stream(arr).mapToDouble(value -> (double) value).toArray();
        long startTime = System.currentTimeMillis();
        BasicSort.quickSort(new_arr);
        long endTime = System.currentTimeMillis();
        System.out.println("total process time " + (endTime - startTime));
//        System.out.println(Arrays.toString(new_arr));
        int index = BasicSort.binarySearch(new_arr, 0, (new_arr.length -1), 500);
        System.out.println(index);
//        BasicSort b = new BasicSort();
//        try {
//            b.testStack();
//        } catch (StackOverflowError e) {
//            System.out.println("******************stack :" + b.count);
//        }
    }
}
