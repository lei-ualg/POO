import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CircleTest {

    @Test
    public void testConstructorCartesian() {
        Circle c = new Circle(3, new Point(3, 4));
        assertEquals(3, c.getCircleRadius());
        assertEquals(3, c.getCircleCenter().getX());
        assertEquals(4, c.getCircleCenter().getY());
    }

    @Test
    public void testConstructorString() {
        Circle c = new Circle("2 2 1");
        assertEquals(1, c.getCircleRadius());
        assertEquals(2, c.getCircleCenter().getX());
        assertEquals(2, c.getCircleCenter().getY());
    }

    @Test
    public void testGetters() {
        Circle c = new Circle(3, new Point(5.0, 5.0));
        assertEquals(3, c.getCircleRadius());
        assertEquals(5, c.getCircleCenter().getY());
        assertEquals(5, c.getCircleCenter().getX());
        Circle c2 = new Circle(3, new Point(3, 4));
        assertEquals(3, c2.getCircleCenter().getX());
        assertEquals(4, c2.getCircleCenter().getY());
    }

    @Test
    public void testCheckInvariant() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(-1));
    }

    @Test
    public void checkIntersectionTrue() {
        Circle c = new Circle(3, new Point(3.5, 3.5));
        Segment s = new Segment(new Point(2.3, 6.6), new Point(2.3, 6.6));
        assertTrue(c.intersects(s));
    }

    @Test
    public void checkIntersectionFalse() {
        Circle c = new Circle(3, new Point(3.5, 3.5));
        Segment s = new Segment(new Point(2.3, 6.6), new Point(0, 5));
        assertFalse(c.intersects(s));
    }

    @Test
    public void checkPerimeter() {
        Circle c = new Circle(3, new Point(3.5, 3.5));
        assertEquals(18.84955592153876, c.perimeter());
    }

    @Test
    public void checkToString() {
        Circle c = new Circle(1, new Point(2, 2));
        assertEquals("(2.00,2.00) 1.00", c.toString());
    }

    @Test
    public void testTranslate() {
        Circle c = new Circle(1, new Point(2, 2));
        c.translate(1, 1);
        assertEquals("(3.00,3.00) 1.00", c.toString());
    }

    @Test
    public void testPointInside() {
        Circle c = new Circle(6, new Point(10, 10));
        Point p = new Point(13, 13);
        assertTrue(c.isPointInside(p));
    }

    @Test
    public void testPointOutside() {
        Circle c = new Circle(6, new Point(10, 10));
        Point p = new Point(16, 16);
        assertFalse(c.isPointInside(p));
    }

    @Test
    public void testPointOnCircle() {
        Circle c = new Circle(6, new Point(10, 10));
        Point p = new Point(16, 10);
        assertFalse(c.isPointInside(p));
    }

    @Test
    public void testBoundingBox() {
        Circle c = new Circle(6, new Point(10, 10));
        Polygon r = c.getBoundingBox();
        assertEquals("(4.00,16.00) (4.00,4.00) (16.00,4.00) (16.00,16.00)", r.toString());
    }

    @Test
    public void testIntersection() {
        Circle c1 = new Circle(3, new Point(3.5, 3.5));
        Circle c2 = new Circle(3, new Point(3.5, 3.5));
        assertTrue(c1.intersects(c2));
    }

    @Test
    public void testCollision() {
        Circle c1 = new Circle(3, new Point(3.5, 3.5));
        Circle c2 = new Circle(3, new Point(3.2, 3.8));
        assertTrue(c1.collides(c2));
    }
}
