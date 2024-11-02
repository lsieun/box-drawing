package lsieun.box.theme.table;

public class TableBuddy {
    public static int[] getColWidthArray(String[][] matrix) {
        int totalRows = matrix.length;
        int totalCols = matrix[0].length;
        int[] colWidthArray = new int[totalCols];
        for (int col = 0; col < totalCols; col++) {
            for (int row = 0; row < totalRows; row++) {
                String str = matrix[row][col];
                int len = str == null ? 0 : str.length();
                if (len > colWidthArray[col]) {
                    colWidthArray[col] = len;
                }
            }
        }
        return colWidthArray;
    }
}
