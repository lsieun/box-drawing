package lsieun.box.theme.shape;

import lsieun.box.canvas.Canvas;
import lsieun.box.canvas.Drawable;

public abstract class AbstractShape implements Drawable {
    private int startRow;
    private int startCol;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    @Override
    public void draw(Canvas canvas, int startRow, int startCol) {
        setStartRow(startRow);
        setStartCol(startCol);
    }
}