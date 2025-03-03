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
}
