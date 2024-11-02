package lsieun.box.theme.table;

import lsieun.box.canvas.Canvas;
import lsieun.box.canvas.TextAlign;
import lsieun.box.theme.shape.rect.FullRectangle;
import lsieun.box.theme.shape.rect.RectangleBuddy;
import lsieun.box.theme.text.Text;

public class OneLineTable extends MatrixTable {
    public final TextAlign align;

    public OneLineTable(String[][] matrix) {
        this(matrix, TextAlign.CENTER_MIDDLE, 0, 1);
    }

    public OneLineTable(String[][] matrix, TextAlign align) {
        this(matrix, align, 0, 1);
    }

    public OneLineTable(String[][] matrix, TextAlign align, int cellPaddingHeight, int cellPaddingWidth) {
        super(matrix, cellPaddingHeight, cellPaddingWidth);
        this.align = align;
    }

    @Override
    public void draw(Canvas canvas, int startRow, int startCol) {

        // text - rect
        int totalRows = getTotalRows();
        int totalCols = getTotalCols();
        FullRectangle[][] rectMatrix = new FullRectangle[totalRows][totalCols];
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                int contentWidth = getCellContentWidth(i, j);
                String cellValue = getCellValue(i, j);
                Text text = Text.of(cellValue);
                FullRectangle rect = RectangleBuddy.of(
                        contentWidth, text.getTotalRows(),
                        cellPaddingWidth, cellPaddingHeight,
                        text,
                        align, false);
                rectMatrix[i][j] = rect;
            }
        }

        // draw rect
        canvas.moveTo(startRow, startCol);
        int row = startRow;

        for (int i = 0; i < totalRows; i++) {
            int col = startCol;
            for (int j = 0; j < totalCols; j++) {
                FullRectangle rect = rectMatrix[i][j];
                canvas.draw(row, col, rect);
                col += rect.getWidth() - getCellBorderWidth(i, j);
            }
            row += rectMatrix[i][0].getHeight() - getCellBorderHeight(i, 0);
        }
    }
}