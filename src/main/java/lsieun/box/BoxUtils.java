package lsieun.box;

import lsieun.box.canvas.Canvas;
import lsieun.box.canvas.Drawable;
import lsieun.box.ds.Pair;
import lsieun.box.ds.Triplet;
import lsieun.box.theme.table.OneLineTable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <pre>
 *                                                                                     ┌─── int[]
 *                                                                                     │
 *                                   ┌─── printList() ────────────┼─── printArray() ───┼─── long[]
 *                                   │                                                 │
 *                                   │                                                 └─── T[]
 * BoxUtils ───┼─── printMatrix() ───┤
 *                                   │                                                          ┌─── int[]
 *                                   │                                                          │
 *                                   └─── printListWithTitle() ───┼─── printArrayWithTitle() ───┼─── long[]
 *                                                                                              │
 *                                                                                              └─── T[]
 * </pre>
 */
public class BoxUtils {
    public static void printMatrix(String[][] matrix) {
        Drawable drawable = new OneLineTable(matrix);
        Canvas canvas = new Canvas();
        canvas.draw(0, 0, drawable);
        canvas.rectifyPosition();
        String str = canvas.toString();
        System.out.println(str);
    }

    public static <T> void printMatrixWithTitle(T[][] data,
                                                List<Triplet<String, Integer, Function<T, ?>>> funcList) {
        int dataTotalRows = data.length;
        int dataTotalColumns = data[0].length;
        int size = funcList.size();

        int matrixTotalRows = dataTotalRows + 1;
        int matrixTotalColumns = dataTotalColumns + size;
        String[][] matrix = new String[matrixTotalRows][matrixTotalColumns];

        // title
        for (int j = 0; j < size; j++) {
            int colIndex = dataTotalColumns + j;
            matrix[0][colIndex] = obj2Str(funcList.get(j).first());
        }

        // content
        for (int i = 0; i < dataTotalRows; i++) {
            int rowIndex = i + 1;

            // data
            for (int j = 0; j < data[0].length; j++) {
                matrix[rowIndex][j] = obj2Str(data[i][j]);
            }

            // func
            for (int j = 0; j < size; j++) {
                Triplet<String, Integer, Function<T, ?>> triplet = funcList.get(j);
                int index = triplet.second();
                T val = data[i][index];
                Object obj = triplet.third().apply(val);

                int colIndex = dataTotalColumns + j;
                matrix[rowIndex][colIndex] = obj2Str(obj);
            }
        }

        printMatrix(matrix);
    }

    public static void printArray(int[] array, List<Function<Integer, ?>> funcList) {
        List<Integer> valList = Arrays.stream(array).boxed().collect(Collectors.toList());
        printList(valList, funcList);
    }

    public static void printArray(long[] array, List<Function<Long, ?>> funcList) {
        List<Long> valList = Arrays.stream(array).boxed().collect(Collectors.toList());
        printList(valList, funcList);
    }

    public static <T> void printArray(T[] array, List<Function<T, ?>> funcList) {
        printList(Arrays.asList(array), funcList);
    }

    public static <T> void printList(List<T> valList, List<Function<T, ?>> funcList) {
        int valSize = valList.size();
        int funcSize = funcList.size();
        String[][] array2D = new String[valSize][funcSize + 1];
        for (int i = 0; i < valSize; i++) {
            T val = valList.get(i);
            array2D[i][0] = String.valueOf(val);
            for (int j = 0; j < funcSize; j++) {
                Object obj = funcList.get(j).apply(val);
                array2D[i][j + 1] = obj2Str(obj);
            }
        }
        printMatrix(array2D);
    }


    public static void printArrayWithTitle(int[] array, List<Pair<String, Function<Integer, ?>>> pairList) {
        List<Integer> valList = Arrays.stream(array).boxed().collect(Collectors.toList());
        printListWithTitle(valList, pairList);
    }

    public static void printArrayWithTitle(long[] array, List<Pair<String, Function<Long, ?>>> pairList) {
        List<Long> valList = Arrays.stream(array).boxed().collect(Collectors.toList());
        printListWithTitle(valList, pairList);
    }

    public static <T> void printArrayWithTitle(T[] array, List<Pair<String, Function<T, ?>>> pairList) {
        List<T> list = Arrays.asList(array);
        printListWithTitle(list, pairList);
    }

    public static <T> void printListWithTitle(List<T> valList, List<Pair<String, Function<T, ?>>> pairList) {
        int valSize = valList.size();
        int pairSize = pairList.size();
        String[][] array2D = new String[valSize + 1][pairSize + 1];
        array2D[0][0] = "";
        for (int j = 0; j < pairSize; j++) {
            Pair<String, Function<T, ?>> pair = pairList.get(j);
            int colIndex = j + 1;
            array2D[0][colIndex] = pair.first();
        }

        for (int i = 0; i < valSize; i++) {
            T val = valList.get(i);
            int rowIndex = i + 1;
            array2D[rowIndex][0] = String.valueOf(val);
            for (int j = 0; j < pairSize; j++) {
                Object obj = pairList.get(j).second().apply(val);
                int colIndex = j + 1;
                array2D[rowIndex][colIndex] = obj2Str(obj);
            }
        }
        printMatrix(array2D);
    }


    private static String obj2Str(Object obj) {
        if (obj == null) {
            return "null";
        }
        else if (obj instanceof String) {
            return (String) obj;
        }
        else if (obj instanceof char[]) {
            char[] chars = (char[]) obj;
            return new String(chars);
        }
        else {
            return String.valueOf(obj);
        }
    }
}
