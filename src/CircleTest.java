import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CircleTest {

    @Test
    public void testConstructorPolar() {
        Circle c = new Circle(3, new Point(5.0, 45.0));
        assertEquals(3, c.getRadius());
        assertEquals(45, c.getCenter().getAngle());
        assertEquals(5, c.getCenter().getRadius());
    }

    @Test
    public void testConstructorCartesian() {
        Circle c = new Circle(3, new Point(3, 4));
        assertEquals(3, c.getRadius());
        assertEquals(53.13010235415598, c.getCenter().getAngle());
        assertEquals(5, c.getCenter().getRadius());
    }

    @Test
    public void testGetters() {
        Circle c = new Circle(3, new Point(5.0, 45.0));
        assertEquals(3, c.getRadius());
        assertEquals(45, c.getCenter().getAngle());
        assertEquals(5, c.getCenter().getRadius());
        Circle c2 = new Circle(3, new Point(3, 4));
        assertEquals(3, c2.getCenter().getX());
        assertEquals(4, c2.getCenter().getY());
    }

    @Test
    public void testCheckInvariant() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Circle(-1, new Point(5.0, 45.0));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Circle(6, new Point(5.0, 91));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Circle(6, new Point(5.0, -1));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Circle(6, new Point(6, 5));
        });
    }

    @Test
    public void checkIntersectionTrue() {
        Circle c = new Circle(3, new Point(5.0, 45));
        Segment s = new Segment(new Point(7.0, 71), new Point(7.0, 19));
        assertTrue(c.intersects(s));
    }

    @Test
    public void checkIntersectionFalse() {
        Circle c = new Circle(3, new Point(5.0, 45));
        Segment s = new Segment(new Point(7.0, 71), new Point(5.0, 90));
        assertFalse(c.intersects(s));
    }

    @Test
    public void checkPerimeter() {
        Circle c = new Circle(3, new Point(5.0, 45));
        assertEquals(18.84955592153876, c.perimeter());
    }
}
