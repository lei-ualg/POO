import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    public void testValidTriangle() {
        String points_string = "0 0 3 0 1 2";
        Triangle t = new Triangle(points_string);
        assertNotNull(t);
    }

    @Test
    public void testInvalidTriangleTooManyPoints() {
        String points_string = "0 0 3 0 1 2 4 5"; // 4 pontos (inválido para um triângulo)
        assertThrows(IllegalArgumentException.class, () -> new Triangle(points_string));
    }

    @Test
    public void testInvalidTriangleTooFewPoints() {
        String points_string = "0 0 3 0"; // Apenas 2 pontos (inválido para um triângulo)
        assertThrows(IllegalArgumentException.class, () -> new Triangle(points_string));
    }

    @Test
    public void testToString() {
        String points_string = "0 0 3 0 1 2";
        Triangle t = new Triangle(points_string);
        assertEquals("Triangulo: [(0,0), (3,0), (1,2)]", t.toString());
    }

    @Test
    public void testDegenerateTriangle() {
        String points_string = "0 0 1 1 2 2"; // Pontos colineares (não formam triângulo válido)
        assertThrows(IllegalArgumentException.class, () -> new Triangle(points_string));
    }

    @Test
    public void testTranslate() {
        String points_string = "0 0 3 0 1 2";
        Triangle t = new Triangle(points_string);
        Triangle t2 = t.translate(1, 1);
        assertEquals("Triangulo: [(1,1), (4,1), (2,3)]", t2.toString());
    }
}
