import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SegmentTest {
    @Test
    public void testValidSegment() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Segment s = new Segment(p1, p2);
        assertNotNull(s);
        assertEquals(p1, s.getA());
        assertEquals(p2, s.getB());
    }

    @Test
    public void testInvalidSegment() {
        Point p = new Point(0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Segment(p, p);
        });
    }

    @Test
    public void testToString() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Segment s = new Segment(p1, p2);
        assertEquals("<" + p1.toString() + ", " + p2.toString() + ">", s.toString());
    }
}
