import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RectangleTest {

    @Test
    public void testValidRectangle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(4, 3);
        Point p4 = new Point(0, 3);
        List<Point> points = Arrays.asList(p1, p2, p3, p4);
        Rectangle r = new Rectangle(points);
        assertNotNull(r);
    }

    @Test
    public void testInvalidRectangle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(5, 3);  // This makes it not a rectangle
        Point p4 = new Point(0, 3);
        List<Point> points = Arrays.asList(p1, p2, p3, p4);
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(points);
        });
    }

    @Test
    public void testToString() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(4, 3);
        Point p4 = new Point(0, 3);
        List<Point> points = Arrays.asList(p1, p2, p3, p4);
        Rectangle r = new Rectangle(points);
        assertEquals(points.toString(), r.toString());
    }

    @Test
    public void testRotatedRectangle() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 1);
        Point p3 = new Point(4, 4);
        Point p4 = new Point(1, 4);
        List<Point> points = Arrays.asList(p1, p2, p3, p4);
        Rectangle r = new Rectangle(points);
        assertNotNull(r);
    }

    @Test
    public void testIntersectsWithSegment() {
        // Create a rectangle in first quadrant (0,0) to (4,3)
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(4, 3);
        Point p4 = new Point(0, 3);
        List<Point> points = Arrays.asList(p1, p2, p3, p4);
        Rectangle r = new Rectangle(points);

        // Test segment that intersects rectangle (diagonal through rectangle)
        Segment s1 = new Segment(new Point(0, 0), new Point(4, 3));
        assertFalse(r.intersects(s1));

        // Test segment that intersects at edge
        Segment s2 = new Segment(new Point(2, 0), new Point(2, 5));
        assertTrue(r.intersects(s2));

        // Test segment completely outside rectangle
        Segment s3 = new Segment(new Point(5, 0), new Point(5, 5));
        assertFalse(r.intersects(s3));
    }
}
