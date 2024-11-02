package lsieun.box.theme.shape.rect;

import lsieun.box.canvas.TextAlign;
import lsieun.box.theme.text.EmptyText;
import lsieun.box.theme.text.Text;

import java.util.List;

import static lsieun.box.theme.shape.rect.RectangleConst.*;

public interface RectangleBuddy {
    static FullRectangle of(Text text) {
        return of(text.getMaxColWidth(), text.getTotalRows(),
                PADDING_WIDTH_DEFAULT, PADDING_HEIGHT_DEFAULT,
                text, TEXT_ALIGN_DEFAULT, REQUIRES_ODD_DEFAULT);
    }

    static FullRectangle of(int contentWidth, Text text) {
        return of(contentWidth, text.getTotalRows(),
                PADDING_WIDTH_DEFAULT, PADDING_HEIGHT_DEFAULT,
                text, TEXT_ALIGN_DEFAULT, REQUIRES_ODD_DEFAULT);
    }

    static FullRectangle of(int contentWidth, int contentHeight) {
        return of(contentWidth, contentHeight,
                PADDING_WIDTH_DEFAULT, PADDING_HEIGHT_DEFAULT,
                EmptyText.INSTANCE, TEXT_ALIGN_DEFAULT, REQUIRES_ODD_DEFAULT);
    }

    static FullRectangle of(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            String[] array, TextAlign align, boolean requiresOdd) {
        return of(contentWidth, contentHeight,
                paddingWidth, paddingHeight,
                Text.of(array), align, requiresOdd);
    }

    static FullRectangle of(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            List<String> lines, TextAlign align, boolean requiresOdd) {
        return of(contentWidth, contentHeight,
                paddingWidth, paddingHeight,
                Text.of(lines), align, requiresOdd);
    }

    static FullRectangle of(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            Text text, TextAlign align, boolean requiresOdd) {
        return new FullRectangle(contentWidth, contentHeight,
                paddingWidth, paddingHeight,
                text, align, requiresOdd);
    }
}
