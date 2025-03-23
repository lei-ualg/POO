import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PolygonTest {

    @Test
    public void testValidPolygon() {
        String points_string = "4 0 0 4 0 4 4 0 4";
        Polygon p = new Polygon(points_string);
        assertNotNull(p);
    }

    @Test
    public void testInvalidPolygonTooFewPoints() {
        String points_string = "2 0 0 1 1"; // Apenas 2 pontos (inválido para um polígono)
        assertThrows(IllegalArgumentException.class, () -> new Polygon(points_string));
    }

    @Test
    public void testInvalidPolygonCollinearPoints() {
        String points_string = "3 0 0 1 1 2 2"; // Pontos colineares (inválido)
        assertThrows(IllegalArgumentException.class, () -> new Polygon(points_string));
    }

    @Test
    public void testInvalidPolygonIntersectingEdges() {
        String points_string = "4 0 0 2 2 0 2 2 0"; // Auto-intersecção (inválido)
        assertThrows(IllegalArgumentException.class, () -> new Polygon(points_string));
    }

    @Test
    public void testToString() {
        String points_string = "3 0 0 3 0 1 2";
        Polygon p = new Polygon(points_string);
        assertEquals("Poligono de 3 vertices: [(0,0), (3,0), (1,2)]", p.toString());
    }

    @Test
    public void testEqualsSamePolygon() {
        String points_string = "3 0 0 3 0 1 2";
        Polygon p1 = new Polygon(points_string);
        Polygon p2 = new Polygon(points_string);
        assertEquals(p1, p2);
    }

    @Test
    public void testEqualsDifferentPolygon() {
        Polygon p1 = new Polygon("3 0 0 3 0 1 2");
        Polygon p2 = new Polygon("3 1 1 4 1 2 3");
        assertNotEquals(p1, p2);
    }

    @Test
    public void testHashCodeEquality() {
        Polygon p1 = new Polygon("3 0 0 3 0 1 2");
        Polygon p2 = new Polygon("3 0 0 3 0 1 2");
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testTranslate() {
        Polygon original = new Polygon("3 0 0 3 0 1 2");
        Polygon translated = original.translate(2, 3);
        
        Polygon expected = new Polygon("3 2 3 5 3 3 5");
        assertEquals(expected, translated);
    }
    
    @Test
    public void testIntersectsPolygon() {
        Polygon p1 = new Polygon("4 0 0 4 0 4 4 0 4");
        Polygon p2 = new Polygon("4 2 2 6 2 6 6 2 6");
        Polygon p3 = new Polygon("4 5 5 9 5 9 9 5 9");
        
        assertTrue(p1.intersects(p2));
        assertFalse(p1.intersects(p3));
    }
    
    @Test
    public void testIntersectsCircle() {
        Polygon p = new Polygon("4 0 0 4 0 4 4 0 4");
        Circle c1 = new Circle(1, new Point(6, 6));

        assertFalse(p.intersects(c1));
    }

    @Test
    public void testCollidesCircle() {
        Polygon p = new Polygon("4 0 0 4 0 4 4 0 4");
        Circle c1 = new Circle(1, new Point(2, 2));

        assertTrue(p.collides(c1));
    }
    
    @Test
    public void testGetArea() {
        Polygon square = new Polygon("4 0 0 4 0 4 4 0 4");
        assertEquals(16.0, square.getArea());
        
        Polygon triangle = new Polygon("3 0 0 4 0 2 3");
        assertEquals(6.0, triangle.getArea());
    }
    
    @Test
    public void testGetBoundingBox() {
        Polygon p = new Polygon("4 1 1 5 1 5 5 1 5");
        Rectangle bbox = p.getBoundingBox();
        
        assertEquals(new Point(1, 5), bbox.vertices[0]);
        assertEquals(new Point(5, 1), bbox.vertices[2]);
    }
    
    @Test
    public void testIsPointInside() {
        Polygon p = new Polygon("4 0 0 4 0 4 4 0 4");
        
        assertTrue(p.isPointInside(new Point(2, 2)));
        assertFalse(p.isPointInside(new Point(5, 5)));
    }
}
