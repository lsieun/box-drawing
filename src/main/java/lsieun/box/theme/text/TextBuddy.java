package lsieun.box.theme.text;

public class TextBuddy {
    public static int maxLength(String[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int max = 0;
        for (String str : array) {
            if (str == null) {
                continue;
            }
            int len = str.length();
            if (len > max) {
                max = len;
            }
        }
        return max;
    }
}
