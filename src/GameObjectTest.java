import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameObjectTest {

    @Test
    public void testConstructor() {
        GeometricForm shape = new Circle(5, new Point(10,10));
        ITransform transform = new Transform(new Point(0,0), 0, 0, 1);
        ICollider collider = new Collider(transform, shape);
        IGameObject gameObject = new GameObject("TestName01", transform, collider);

        assertEquals("TestName01", gameObject.name());
        assertEquals(collider, gameObject.collider());
        assertEquals(transform, gameObject.transform());
    }

    @Test
    public void testToStringCircle() {
        GeometricForm shape = new Circle(5, new Point(10,10));
        ITransform transform = new Transform(new Point(0,0), 0, 0, 1);
        ICollider collider = new Collider(transform, shape);
        IGameObject gameObject = new GameObject("TestCircle01", transform, collider);

        assertEquals("""
                TestCircle01
                (0.00,0.00) 0 0.00 1.00
                (0.00,0.00) 5.00""", gameObject.toString());
    }

    @Test
    public void testToStringPolygon() {
        GeometricForm shape = new Polygon("4 2 2 2 6 5 6 4 2");
        ITransform transform = new Transform(new Point(0,0), 0, 0, 1);
        ICollider collider = new Collider(transform, shape);
        IGameObject gameObject = new GameObject("TestPoly01", transform, collider);
        assertEquals("""
                TestPoly01
                (0.00,0.00) 0 0.00 1.00
                (-1.25,-2.00) (-1.25,2.00) (1.75,2.00) (0.75,-2.00)""", gameObject.toString());
    }
}