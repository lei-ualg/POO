import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    @Test
    public void testValidRectangle() {
        String points_string = "0 0 4 0 4 3 0 3";
        Rectangle r = new Rectangle(points_string);
        assertNotNull(r);
    }

    @Test
    public void testInvalidRectangle() {
        String points_string = "0 0 4 0 5 3 0 3";
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(points_string));
    }

    @Test
    public void testToString() {
        String points_string = "0 0 4 0 4 3 0 3";
        Rectangle r = new Rectangle(points_string);
        assertEquals("Retangulo: [(0,0), (4,0), (4,3), (0,3)]", r.toString());
    }

    @Test
    public void testRotatedRectangle() {
        String points_string = "1 1 4 1 4 4 1 4";
        Rectangle r = new Rectangle(points_string);
        assertNotNull(r);
    }

    @Test
    public void testIntersectsWithSegment() {
        // Create a rectangle in first quadrant (0,0) to (4,3)
        String points_string = "0 0 4 0 4 3 0 3";
        Rectangle r = new Rectangle(points_string);

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
