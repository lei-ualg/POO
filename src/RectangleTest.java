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
    public void testTranslate() {
        String points_string = "0 0 4 0 4 3 0 3";
        Rectangle r = new Rectangle(points_string);
        Rectangle r2 = r.translate(1, 1);
        assertEquals("Retangulo: [(1,1), (5,1), (5,4), (1,4)]", r2.toString());
    }
}
