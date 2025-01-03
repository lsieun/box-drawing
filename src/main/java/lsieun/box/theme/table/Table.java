package lsieun.box.theme.table;

import lsieun.box.canvas.Drawable;

public interface Table extends Drawable {
    // region table - count
    int getTotalRows();

    int getTotalCols();
    // endregion


    // region cell - width and height
    default int getCellWidth(int rowIndex, int colIndex) {
        return 2 * getCellBorderWidth(rowIndex, colIndex) +
                2 * getCellPaddingWidth(rowIndex, colIndex) +
                getCellContentWidth(rowIndex, colIndex);
    }

    default int getCellHeight(int rowIndex, int colIndex) {
        return 2 * getCellBorderHeight(rowIndex, colIndex) +
                2 * getCellPaddingHeight(rowIndex, colIndex) +
                getCellContentHeight(rowIndex, colIndex);
    }
    // endregion


    // region cell - content
    int getCellContentWidth(int rowIndex, int colIndex);

    int getCellContentHeight(int rowIndex, int colIndex);
    // endregion


    // region cell - padding
    default int getCellPaddingWidth(int rowIndex, int colIndex) {
        return 0;
    }

    default int getCellPaddingHeight(int rowIndex, int colIndex) {
        return 0;
    }
    // endregion


    // region cell - border
    default int getCellBorderWidth(int rowIndex, int colIndex) {
        return 1;
    }

    default int getCellBorderHeight(int rowIndex, int colIndex) {
        return 1;
    }
    // endregion

}