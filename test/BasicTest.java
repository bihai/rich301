import org.junit.*;
import java.util.*;

import play.test.*;
import util.RichUtil;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testXXX() {
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        
        Integer[] array = ints.toArray(new Integer[0]);
        
        for (Integer integer : array) {
            System.out.println(integer);
        }
    }
}
