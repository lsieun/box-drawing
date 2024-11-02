package lsieun.box.theme.shape.rect;

import lsieun.box.canvas.Canvas;
import lsieun.box.canvas.TextAlign;
import lsieun.box.theme.shape.AbstractShape;
import lsieun.box.theme.text.Text;


/**
 * <ul>
 *     <li>borderWidth == 1</li>
 *     <li>innerWidth = contentWidth + 2 * paddingWidth</li>
 *     <li>totalWidth = contentWidth + 2 * paddingWidth + 2 * borderWidth</li>
 *     <li>width == totalWidth</li>
 * </ul>
 */
public class FullRectangle extends AbstractShape implements Rectangle {
    public final int contentWidth;
    public final int contentHeight;
    public final int paddingWidth;
    public final int paddingHeight;

    public final Text text;
    public final TextAlign align;


    FullRectangle(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            Text text, TextAlign align, boolean requiresOdd) {
        contentWidth = contentWidth == 0 ? text.getMaxColWidth() : contentWidth;
        contentHeight = contentHeight == 0 ? text.getTotalRows() : contentHeight;
        this.contentWidth = requiresOdd ? getOdd(contentWidth) : contentWidth;
        this.contentHeight = requiresOdd ? getOdd(contentHeight) : contentHeight;
        this.paddingHeight = paddingHeight;
        this.paddingWidth = paddingWidth;
        this.text = text;
        this.align = align;
    }

    private static int getOdd(int num) {
        return ((num & 1) == 0) ? num + 1 : num;
    }

    @Override
    public void draw(Canvas canvas, int startRow, int startCol) {
        // save position
        super.draw(canvas, startRow, startCol);

        // innerWidth and innerHeight
        int innerWidth = contentWidth + 2 * paddingWidth;
        int innerHeight = contentHeight + 2 * paddingHeight;

        // draw rectangle
        canvas.moveTo(startRow, startCol);
        canvas.drawRectangle(innerWidth, innerHeight);

        // draw text
        int count = Math.min(contentHeight, text.getTotalRows());
        for (int i = 0; i < count; i++) {
            int row = getRowConsideringVerticalAlign(i);
            int col = getCol(0);
            canvas.moveTo(row, col);
            canvas.drawText(contentWidth, text.getLine(i), align.hAlign);
        }
    }

    public int getRowConsideringVerticalAlign(int textIndex) {
        switch (align.vAlign) {
            case MIDDLE: {
                int offset = contentHeight > text.getTotalRows() ? (contentHeight - text.getTotalRows()) / 2 : 0;
                return getRow(textIndex) + offset;
            }
            case BOTTOM: {
                int offset = contentHeight > text.getTotalRows() ? contentHeight - text.getTotalRows() : 0;
                return getRow(textIndex) + offset;
            }
            case TOP:
            default: {
                return getRow(textIndex);
            }
        }
    }


    @Override
    public int getContentWidth() {
        return contentWidth;
    }

    @Override
    public int getContentHeight() {
        return contentHeight;
    }

    @Override
    public int getPaddingWidth() {
        return paddingWidth;
    }

    @Override
    public int getPaddingHeight() {
        return paddingHeight;
    }

}