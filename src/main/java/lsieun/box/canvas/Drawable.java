package lsieun.box.canvas;

@FunctionalInterface
public interface Drawable {
    void draw(Canvas canvas, int startRow, int startCol);
}
