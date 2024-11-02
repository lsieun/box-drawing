package lsieun.box.theme.table;

public abstract class AbstractTable implements Table {
    private final int totalRows;
    private final int totalCols;

    public final int cellPaddingHeight;
    public final int cellPaddingWidth;

    public final int[] cellContentHeightArray;
    public final int[] cellContentWidthArray;

    public AbstractTable(int totalRows, int totalCols) {
        this(totalRows, totalCols, 0, 1);
    }

    public AbstractTable(
            int totalRows, int totalCols,
            int cellPaddingHeight, int cellPaddingWidth
    ) {
        this.totalRows = totalRows;
        this.totalCols = totalCols;

        this.cellPaddingHeight = cellPaddingHeight;
        this.cellPaddingWidth = cellPaddingWidth;

        this.cellContentHeightArray = new int[totalRows];
        this.cellContentWidthArray = new int[totalCols];
    }

    @Override
    public int getTotalRows() {
        return totalRows;
    }

    @Override
    public int getTotalCols() {
        return totalCols;
    }

    @Override
    public int getCellContentWidth(int rowIndex, int colIndex) {
        return cellContentWidthArray[colIndex];
    }

    @Override
    public int getCellContentHeight(int rowIndex, int colIndex) {
        return cellContentHeightArray[rowIndex];
    }

    @Override
    public int getCellPaddingHeight(int rowIndex, int colIndex) {
        return cellPaddingHeight;
    }
    @Override
    public int getCellPaddingWidth(int rowIndex, int colIndex) {
        return cellPaddingWidth;
    }
}