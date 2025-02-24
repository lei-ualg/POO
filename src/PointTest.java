import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PointTest {
    @Test
    public void testConstructorPolar() {
        Point p = new Point(5.0, 45);
        assertEquals(5, p.getRadius());
        assertEquals(45, p.getAngle());
    }

    @Test
    public void testConstructorCartesian() {
        Point p = new Point(3, 4);
        assertEquals(5, p.getRadius());
        assertEquals(53.13010235415598, p.getAngle(), Utils.epsilon);
    }

    @Test
    public void testGetters() {
        Point p = new Point(5.0, 45);
        assertEquals(5, p.getRadius());
        assertEquals(45, p.getAngle());
        Point p2 = new Point(3, 4);
        assertEquals(3, p2.getX());
        assertEquals(4, p2.getY());
    }

    @Test
    public void testCheckInvariant() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Point(5.0, 91);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Point(5.0, -1);
        });
    }

    @Test
    public void testDistance() {
        Point p1 = new Point(5.0, 53.13010235415598);
        Point p2 = new Point(3, 4);
        assertEquals(0, p1.distance(p1));
        assertEquals(0, p2.distance(p2));
        assertEquals(0, p1.distance(p2));
        assertEquals(0, p2.distance(p1));
    }
}
