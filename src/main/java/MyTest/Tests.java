package MyTest;


public class Tests {
    public static boolean simpleNum(int value) {
        for (int i = 2; i < value; i++) {
            if (value % i == 1) {
                return false;
            }
        }
        return true;
    }

}
