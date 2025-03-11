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
}
