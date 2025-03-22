import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CircleTest {

    @Test
    public void testConstructorPolar() {
        Circle c = new Circle(3, new Point(5.0, 45.0));
        assertEquals(3, c.getCircleRadius());
        assertEquals(45, c.getAngle());
        assertEquals(5, c.getRadius());
    }

    @Test
    public void testConstructorCartesian() {
        Circle c = new Circle(3, new Point(3, 4));
        assertEquals(3, c.getCircleRadius());
        assertEquals(53.13010235415598, c.getAngle());
        assertEquals(5, c.getRadius());
    }

    @Test
    public void testConstructorString() {
        Circle c = new Circle("2 2 1");
        assertEquals(1, c.getCircleRadius());
        assertEquals(2, c.getX());
        assertEquals(2, c.getY());
    }

    @Test
    public void testGetters() {
        Circle c = new Circle(3, new Point(5.0, 45.0));
        assertEquals(3, c.getCircleRadius());
        assertEquals(45, c.getAngle());
        assertEquals(5, c.getRadius());
        Circle c2 = new Circle(3, new Point(3, 4));
        assertEquals(3, c2.getX());
        assertEquals(4, c2.getY());
    }

    @Test
    public void testCheckInvariant() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(-1, new Point(5.0, 45.0)));
        assertThrows(IllegalArgumentException.class, () -> new Circle(6, new Point(5.0, 91)));
        assertThrows(IllegalArgumentException.class, () -> new Circle(6, new Point(5.0, -1)));
        assertThrows(IllegalArgumentException.class, () -> new Circle(6, new Point(6, 5)));
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

    @Test
    public void checkToString() {
        Circle c = new Circle(1, new Point(2, 2));
        assertEquals("Circulo: (2,2) 1", c.toString());
    }

    @Test
    public void testTranslate() {
        Circle c = new Circle(1, new Point(2, 2));
        Circle c2 = c.translate(1, 1);
        assertEquals("Circulo: (3,3) 1", c2.toString());
    }
}
