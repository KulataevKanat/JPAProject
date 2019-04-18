package MyTest;

import java.util.StringTokenizer;

public class Words {
    private String str;

    public Words(String string) {
        str = string;
    }

    public int countWords() {
        return new StringTokenizer(str).countTokens();
    }

    public static void main(String[] args) {
        Words words = new Words("Например Например Например");
        System.out.println(words.countWords());
    }

}

