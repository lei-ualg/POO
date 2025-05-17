import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameObjectTest {

    // Test fixtures
    private String objectName;
    private ITransform transform;
    private ICollider collider;
    private IShape shape;
    private Behaviour behaviour;
    private GameObject gameObject;

    @BeforeEach
    public void setUp() {
        objectName = "TestObject";
        transform = new Transform(new Point(10, 20), 45.0, 2.0, 3, null);

        // Create a simple square collider
        Point topLeft = new Point(0, 10);
        Point bottomRight = new Point(10, 0);
        collider = new CollPoly(topLeft, bottomRight, transform);

        shape = new Shape();
        behaviour = new Behaviour();

        // Pass collider to transform
        if (transform instanceof Transform) {
            ((Transform) transform).collider = collider;
        }

        gameObject = new GameObject(objectName, transform, collider, shape, behaviour);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(objectName, gameObject.name(), "GameObject should have the correct name");
        assertSame(transform, gameObject.transform(), "GameObject should have the correct transform");
        assertSame(collider, gameObject.collider(), "GameObject should have the correct collider");
        assertSame(shape, gameObject.shape(), "GameObject should have the correct shape");
        assertSame(behaviour, gameObject.behaviour(), "GameObject should have the correct behaviour");
    }

    @Test
    public void testToString() {
        String result = gameObject.toString();

        assertTrue(result.contains(objectName), "toString should contain the object name");
        assertTrue(result.contains(transform.toString()), "toString should contain the transform information");
        assertTrue(result.contains(collider.toString()), "toString should contain the collider information");
    }

    @Test
    public void testCollisionDetection() {
        // Create another GameObject with a collider that overlaps
        Transform transform2 = new Transform(new Point(5, 5), 0, 1.0, 3, null);
        CollPoly collider2 = new CollPoly(new Point(0, 10), new Point(10, 0), transform2);
        GameObject gameObject2 = new GameObject("TestObject2", transform2, collider2, shape, behaviour);

        // Test collision
        assertTrue(gameObject.collider().isColliding(gameObject2.collider()),
                "Overlapping objects should detect collision");

        // Update colliders
        gameObject.collider().onUpdate();
        gameObject2.collider().onUpdate();

        // Test collision again after update
        assertFalse(gameObject.collider().isColliding(gameObject2.collider()),
                "Non-overlapping objects should now detect collision after update");
    }

    @Test
    public void testTransformChangesAffectCollider() {
        // Get initial position of collider
        Point initialPosition = new Point(gameObject.transform().position().getX(),
                gameObject.transform().position().getY());

        // Move the GameObject
        gameObject.transform().move(new Point(5, 5), 0);

        // Update the collider
        gameObject.collider().onUpdate();

        // Get the new position
        Point newPosition = gameObject.transform().position();

        // Check if the collider position has been updated
        assertEquals(initialPosition.getX() + 5, newPosition.getX(),
                "Collider X position should update after transform change");
        assertEquals(initialPosition.getY() + 5, newPosition.getY(),
                "Collider Y position should update after transform change");
    }

    @Test
    public void testComplexGameObject() {
        // Create a game object with different transform values
        Transform complexTransform = new Transform(new Point(5, 5), 30.0, 1.5, 2, null);
        CollCircle circleCollider = new CollCircle(3.0, new Point(5, 5), complexTransform);
        complexTransform.collider = circleCollider;

        GameObject complexObject = new GameObject("ComplexObject", complexTransform, circleCollider, shape, behaviour);

        // Check if all components are properly initialized
        assertEquals("ComplexObject", complexObject.name());
        assertEquals(30.0, complexObject.transform().angle());
        assertEquals(1.5, complexObject.transform().scale());
        assertEquals(5.0, complexObject.transform().position().getX());
        assertEquals(5.0, complexObject.transform().position().getY());

        // Test that the collider is properly set up
        assertInstanceOf(CollCircle.class, complexObject.collider());

        // Update and test position changes
        complexObject.transform().move(new Point(2, 2), 0);
        circleCollider.onUpdate();
        assertEquals(7.0, complexObject.transform().position().getX());
        assertEquals(7.0, complexObject.transform().position().getY());
    }

    @Test
    public void testGameObjectInteractions() {
        // Create multiple game objects with different colliders

        // Square object
        Transform squareTransform = new Transform(new Point(0, 0), 0, 1.0, 1, null);
        CollPoly squareCollider = new CollPoly(new Point(-5, 5), new Point(5, -5), squareTransform);
        squareTransform.collider = squareCollider;
        GameObject squareObject = new GameObject("Square", squareTransform, squareCollider, shape, behaviour);

        // Circle object
        Transform circleTransform = new Transform(new Point(7, 0), 0, 1.0, 1, null);
        CollCircle circleCollider = new CollCircle(3.0, new Point(10, 0), circleTransform);
        circleTransform.collider = circleCollider;
        GameObject circleObject = new GameObject("Circle", circleTransform, circleCollider, shape, behaviour);

        // Test collision when objects are close but not colliding
        assertFalse(squareObject.collider().isColliding(circleObject.collider()),
                "Objects should not collide at initial positions");

        // Move circle closer to square to create a collision
        circleTransform.move(new Point(-3, 0), 0);
        circleCollider.onUpdate();

        // Now they should collide
        assertTrue(squareObject.collider().isColliding(circleObject.collider()),
                "Objects should collide after circle is moved closer");
        assertTrue(circleObject.collider().isColliding(squareObject.collider()),
                "Collision detection should be commutative");
    }
}
