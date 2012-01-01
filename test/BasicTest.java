import org.junit.*;
import java.util.*;
import play.test.*;
import util.RichUtil;
import models.*;
import models.GameMap;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testMapJson() {
        GameMap map = MapGenerator.generateMap();
        System.out.println(RichUtil.mapToJson(map));
    }
}
