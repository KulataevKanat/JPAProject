package firstTest;


import MyTest.Tests;
import MyTest.Words;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest {
    @Test
    void testSingleSuccessTest() {
        Words words = new Words("Например например например");
        assertEquals(Tests.simpleNum(12), true);
        assertEquals(words.countWords(),3);
    }


}
