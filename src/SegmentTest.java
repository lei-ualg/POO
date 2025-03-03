import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SegmentTest {
    @Test
    public void testValidSegment() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 3);
        Segment s = new Segment(p1, p2);
        assertNotNull(s);
        assertEquals(p1, s.getA());
        assertEquals(p2, s.getB());
    }

    @Test
    public void testInvalidSegment() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            new Segment(p1, p2);
        });
    }

    @Test
    public void testIntersectingSegments() {
        // Two segments that intersect in the middle
        Segment s1 = new Segment(new Point(0, 0), new Point(4, 4));
        Segment s2 = new Segment(new Point(0, 4), new Point(4, 0));
        assertTrue(s1.intersects(s2));
    }

    @Test
    public void testNonIntersectingSegments() {
        // Parallel segments
        Segment s1 = new Segment(new Point(1, 1), new Point(1, 5));
        Segment s2 = new Segment(new Point(2, 1), new Point(2, 5));
        assertFalse(s1.intersects(s2));

        // Non-parallel, non-intersecting segments
        Segment s3 = new Segment(new Point(0, 0), new Point(2, 2));
        Segment s4 = new Segment(new Point(3, 0), new Point(5, 2));
        assertFalse(s3.intersects(s4));

        // Two segments that share an endpoint
        Segment s5 = new Segment(new Point(1, 1), new Point(5, 5));
        Segment s6 = new Segment(new Point(5, 5), new Point(5, 1));
        assertFalse(s5.intersects(s6));
    }
}
