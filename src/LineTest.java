import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LineTest {
    @Test
    public void testValidLine() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 3);
        Line l = new Line(p1, p2);
        assertNotNull(l);
        assertTrue(l.has(p1));
        assertTrue(l.has(p2));
    }

    @Test
    public void testInvalidLine() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 2);
        assertThrows(IllegalArgumentException.class, () -> new Line(p1, p2));
    }

    @Test
    public void testPointLocation() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);  // Horizontal line
        Line l = new Line(p1, p2);

        // Point above the line
        Point above = new Point(2, 3);
        assertTrue(l.at(above) != 0);

        // Point below the line
        Point below = new Point(2, 1);
        assertTrue(l.at(below) != 0);

        // Point on the line
        Point onLine = new Point(2, 0);
        assertTrue(l.has(onLine));
    }

    @Test
    public void testOpposedSides() {
        Point p1 = new Point(0, 2);
        Point p2 = new Point(4, 2);  // Horizontal line
        Line l = new Line(p1, p2);

        Point above = new Point(2, 3);
        Point below = new Point(2, 1);
        assertTrue(l.opposedSides(above, below));

        Point onLine = new Point(2, 2);
        assertFalse(l.opposedSides(above, onLine));
        assertFalse(l.opposedSides(onLine, below));
    }

    @Test
    public void testToString() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 3);
        Line l = new Line(p1, p2);
        assertEquals("(3.0x + -4.0y + 0.0 = 0)", l.toString());
    }
}