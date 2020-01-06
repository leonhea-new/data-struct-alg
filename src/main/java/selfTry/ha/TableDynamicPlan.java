package selfTry.ha;

import java.util.ArrayList;
import java.util.Random;

public class TableDynamicPlan {

    private int[][] table;
    public int size;
    public Result[][] resultTable;

    public TableDynamicPlan(int size) {
        table = new int[size][size];
        resultTable = new Result[size][size];
        this.size = size;
        Random r = new Random(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = r.nextInt(size * size);
            }
        }
    }

    public Result minDistance(int i, int j) {

        if (resultTable[i][j] != null){
            return resultTable[i][j];
        }

        if (i == 0 && j == 0) {
            return new Result(table[0][0], new Integer[]{0, 0});
        }

        if (j == 0) {
            Result former = minDistance(i - 1, j);
//            resultTable[i][j] = former.addItem(table[i][j], new Integer[]{i, j});
            resultTable[i][j] = former.addItemNew(table[i][j], new Integer[]{i, j});
            return resultTable[i][j];
        }

        if (i == 0) {
            Result former = minDistance(i, j-1);
//            resultTable[i][j] = former.addItem(table[i][j], new Integer[]{i, j});
            resultTable[i][j] = former.addItemNew(table[i][j], new Integer[]{i, j});
            return resultTable[i][j];
        }

        Result leftResult = minDistance(i, j-1);
        Result uppResult = minDistance(i-1, j);

        if (leftResult.compareTo(uppResult) >= 0) {
//            resultTable[i][j] = uppResult.addItem(table[i][j], new Integer[]{i, j});
            resultTable[i][j] = uppResult.addItemNew(table[i][j], new Integer[]{i, j});
        } else {
//            resultTable[i][j] = leftResult.addItem(table[i][j], new Integer[]{i, j});
            resultTable[i][j] = leftResult.addItemNew(table[i][j], new Integer[]{i, j});
        }

        return resultTable[i][j];
    }

    void printTable() {
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++)  {
                System.out.print(table[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int size = 5;
        TableDynamicPlan table = new TableDynamicPlan(size);
        table.printTable();
        table.minDistance(size -1,size-1);
        table.resultTable[size -1][size -1].print();

    }

}

class Result implements Comparable<Result> {
    int distance;
    ArrayList<Integer []> path;

    Result() {
        distance = 0;
        path = new ArrayList<>();
    }
    Result(int itemDistance, Integer[] itemPos) {
        distance = itemDistance;
        path = new ArrayList<Integer[]>() {{
            add(itemPos);
        }};
    }

    Result addItemNew(int itemDistance, Integer[] itemPos) {
        Result newResult =  new Result();
        newResult.distance = distance;
        newResult.path.addAll(path);
        return  newResult.addItem(itemDistance, itemPos);
    }

    void addItem(Result result) {
        distance += result.distance;
        ArrayList<Integer[]> temp = (ArrayList<Integer[]>)result.path.clone();
        temp.addAll(path);
        path = temp;
    }

    Result addItem(int itemDistance, Integer[] itemPos) {
        distance += itemDistance;
        path.add(itemPos);
        return this;
    }

    public void print() {
        System.out.println("min: " + distance);
        for (Integer[] item : path) {
            System.out.println(" ->(" + item[0] + ", " + item[1] + ") ");
        }
    }


    @Override
    public int compareTo(Result item) {
        return distance - item.distance;
    }
}

