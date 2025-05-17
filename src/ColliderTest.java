import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColliderTest {
    private ITransform defaultTransform;
    private CollPoly square1;
    private CollPoly square2;
    private CollPoly square3;
    private CollCircle circle1;

    @BeforeEach
    public void setUp() {
        // Default transform with no rotation, no scaling, at origin
        defaultTransform = new Transform(new Point(0, 0), 0, 1, 0, null);

        // Create test objects
        square1 = new CollPoly(new Point(-1, 1), new Point(1, -1), defaultTransform);
        square2 = new CollPoly(new Point(0, 2), new Point(2, 0), defaultTransform);
        square3 = new CollPoly(new Point(3, 2), new Point(6, -1), defaultTransform);
        circle1 = new CollCircle(1.0, new Point(0, 0), defaultTransform);
    }

    @Test
    public void testPolygonToPolygonCollision() {
        // Test overlapping squares
        assertTrue(square1.isColliding(square2), "Overlapping squares should collide");
        assertTrue(square2.isColliding(square1), "Collision should be commutative");

        // Test non-overlapping shapes
        assertFalse(square1.isColliding(square3), "Non-overlapping shapes should not collide");
        assertFalse(square3.isColliding(square1), "Collision should be commutative");
    }

    @Test
    public void testCircleToCircleCollision() {
        // Create circles that overlap
        CollCircle circleA = new CollCircle(2.0, new Point(0, 0), defaultTransform);
        CollCircle circleB = new CollCircle(2.0, new Point(3, 0), defaultTransform);
        circleA.onUpdate(); // Update the circle's state
        circleB.onUpdate(); // Update the circle's state

        assertTrue(circleA.isColliding(circleB), "Overlapping circles should collide");
        assertTrue(circleB.isColliding(circleA), "Collision should be commutative");

        // Create circles that don't overlap
        CollCircle circleC = new CollCircle(1.0, new Point(0, 0), defaultTransform);
        CollCircle circleD = new CollCircle(1.0, new Point(3, 0), defaultTransform);

        assertFalse(circleC.isColliding(circleD), "Non-overlapping circles should not collide");
        assertFalse(circleD.isColliding(circleC), "Collision should be commutative");

        // Create circles that touch at exactly one point
        CollCircle circleE = new CollCircle(1.0, new Point(0, 0), defaultTransform);
        CollCircle circleF = new CollCircle(1.0, new Point(2, 0), defaultTransform);
        circleE.onUpdate();
        circleF.onUpdate();

        assertTrue(circleE.isColliding(circleF), "Touching circles should collide");
    }

    @Test
    public void testCircleToPolygonCollision() {
        // Test circle overlapping with square
        assertTrue(circle1.isColliding(square1), "Circle overlapping with square should collide");
        assertTrue(square1.isColliding(circle1), "Collision should be commutative");

        // Test circle not overlapping with rectangle
        System.out.println(circle1);
        System.out.println(square3);
        assertFalse(circle1.isColliding(square3), "Non-overlapping circle and rectangle should not collide");
        assertFalse(square3.isColliding(circle1), "Collision should be commutative");

        // Create a larger square that fully contains the circle
        CollPoly largeSquare = new CollPoly(new Point(-2, 2), new Point(2, -2), defaultTransform);
        largeSquare.onUpdate();

        assertTrue(largeSquare.isColliding(circle1), "Square containing circle should report collision");
        assertTrue(circle1.isColliding(largeSquare), "Collision should be commutative");
    }

    @Test
    public void testTransformedCollisions() {
        // Test with translated objects
        ITransform movedTransform = new Transform(new Point(5, 5), 0, 1, 0, null);
        CollPoly movedSquare = new CollPoly(new Point(-1, 1), new Point(1, -1), movedTransform);
        movedSquare.onUpdate();

        assertFalse(movedSquare.isColliding(square1), "Moved square shouldn't collide with original square");

        // Test with rotation
        ITransform rotatedTransform = new Transform(new Point(0, 0), Math.PI / 4, 1, 0, null);
        CollPoly rotatedSquare = new CollPoly(new Point(-1, 1), new Point(1, -1), rotatedTransform);
        rotatedSquare.onUpdate();

        assertTrue(rotatedSquare.isColliding(square1), "Rotated square should still collide with original square");

        // Test with scaling
        ITransform scaledTransform = new Transform(new Point(0, 0), 0, 2.0, 0, null);
        CollCircle scaledCircle = new CollCircle(1.0, new Point(0, 0), scaledTransform);

        // Create a circle that normally wouldn't collide
        CollCircle distantCircle = new CollCircle(0.5, new Point(2.4, 0), defaultTransform);

        scaledCircle.onUpdate();
        distantCircle.onUpdate();

        assertTrue(scaledCircle.isColliding(distantCircle), "Scaled up circle should now collide with distant circle");
    }

    @Test
    public void testComplexTransformations() {
        // Create a circle with both rotation and scaling
        ITransform complexTransform = new Transform(new Point(1.7, 3.6), Math.PI / 6, 1.5, 0, null);
        CollCircle transformedCircle = new CollCircle(1.0, new Point(0, 0), complexTransform);
        transformedCircle.onUpdate();

        // Create a polygon with both rotation and scaling
        ITransform polyTransform = new Transform(new Point(2, 2), Math.PI * 2, 0.5, 0, null);
        CollPoly transformedPoly = new CollPoly(
                new Point(-2, 2), new Point(2, -2), polyTransform
        );


        // Test interactions
        assertFalse(transformedCircle.isColliding(transformedPoly),
                "Transformed objects should respect their transformations");

        transformedPoly.onUpdate();
        assertTrue(transformedCircle.isColliding(transformedPoly),
                "Transformed circle should collide with transformed polygon");
    }

    @Test
    public void testEdgeCases() {
        // Test very small circles
        CollCircle tinyCircle1 = new CollCircle(0.01, new Point(0, 0), defaultTransform);
        CollCircle tinyCircle2 = new CollCircle(0.01, new Point(0.015, 0), defaultTransform);
        tinyCircle1.onUpdate();
        tinyCircle2.onUpdate();

        assertTrue(tinyCircle1.isColliding(tinyCircle2),
                "Very small overlapping circles should detect collision");

        // Test very large polygons
        CollPoly hugePoly = new CollPoly(new Point(-1000, 1000), new Point(1000, -1000), defaultTransform);
        hugePoly.onUpdate();

        assertTrue(hugePoly.isColliding(circle1),
                "Very large polygon should detect collision with small circle");
    }
}
