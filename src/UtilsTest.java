import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void testEq() {
        assertTrue(Utils.eq(1.0, 1.0));
        assertTrue(Utils.eq(1.0, 1.0 + Utils.epsilon / 2));
        assertFalse(Utils.eq(1.0, 1.0 + 2 * Utils.epsilon));
    }

    @Test
    public void testGt() {
        assertTrue(Utils.gt(2.0, 1.0));
        assertFalse(Utils.gt(1.0, 1.0));
        assertFalse(Utils.gt(1.0, 2.0));
        assertFalse(Utils.gt(1.0, 1.0 + Utils.epsilon / 2));
        assertTrue(Utils.gt(1.0 + 2 * Utils.epsilon, 1.0));
    }

    @Test
    public void testGe() {
        assertTrue(Utils.ge(2.0, 1.0));
        assertTrue(Utils.ge(1.0, 1.0));
        assertFalse(Utils.ge(1.0, 2.0));
        assertTrue(Utils.ge(1.0, 1.0 + Utils.epsilon / 2));
        assertTrue(Utils.ge(1.0 + 2 * Utils.epsilon, 1.0));
    }

    @Test
    public void testParsePoints() {
        Point[] points = Utils.parsePoints("1 2 3 4 5 6");
        assertEquals(3, points.length);
        assertEquals(1, points[0].getX());
        assertEquals(2, points[0].getY());
        assertEquals(3, points[1].getX());
        assertEquals(4, points[1].getY());
        assertEquals(5, points[2].getX());
        assertEquals(6, points[2].getY());
    }
}
