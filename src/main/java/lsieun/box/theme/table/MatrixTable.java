package lsieun.box.theme.table;

import java.util.Arrays;

public abstract class MatrixTable extends AbstractTable {
    private static final String SPACE = " ";

    protected final String[][] matrix;

    public MatrixTable(String[][] matrix) {
        this(matrix, 0, 1);
    }

    public MatrixTable(String[][] matrix, int cellPaddingHeight, int cellPaddingWidth) {
        super(matrix.length, matrix[0].length, cellPaddingHeight, cellPaddingWidth);
        this.matrix = matrix;

        Arrays.fill(cellContentHeightArray, 1);
        int[] array = TableBuddy.getColWidthArray(matrix);
        System.arraycopy(array, 0, cellContentWidthArray, 0, array.length);
    }

    public String getCellValue(int row, int col) {
        String item = matrix[row][col];
        return item == null ? SPACE : item;
    }
}