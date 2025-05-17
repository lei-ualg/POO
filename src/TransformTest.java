import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformTest {
    
    private Transform transform;
    private Point initialPosition;
    
    @BeforeEach
    public void setUp() {
        initialPosition = new Point(10, 20);
        transform = new Transform(initialPosition, 45.0, 2.0, 3, null);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(initialPosition, transform.position(), "Position should match the constructor parameter");
        assertEquals(45.0, transform.angle(), "Angle should match the constructor parameter");
        assertEquals(2.0, transform.scale(), "Scale should match the constructor parameter");
        assertEquals(3, transform.layer(), "Layer should match the constructor parameter");
    }
    
    @Test
    public void testMove() {
        // Test positive movement
        Point delta = new Point(5, 10);
        transform.move(delta, 1);
        
        assertEquals(15, transform.position().getX(), "X position should be updated after move");
        assertEquals(30, transform.position().getY(), "Y position should be updated after move");
        assertEquals(4, transform.layer(), "Layer should be updated after move");
        
        // Test negative movement
        Point negativeDelta = new Point(-7, -12);
        transform.move(negativeDelta, -2);
        
        assertEquals(8, transform.position().getX(), "X position should be updated after negative move");
        assertEquals(18, transform.position().getY(), "Y position should be updated after negative move");
        assertEquals(2, transform.layer(), "Layer should be updated after negative move");
        
        // Test zero movement
        Point zeroDelta = new Point(0, 0);
        transform.move(zeroDelta, 0);
        
        assertEquals(8, transform.position().getX(), "X position should remain unchanged after zero move");
        assertEquals(18, transform.position().getY(), "Y position should remain unchanged after zero move");
        assertEquals(2, transform.layer(), "Layer should remain unchanged after zero move");
    }
    
    @Test
    public void testRotate() {
        // Test positive rotation
        transform.rotate(30);
        assertEquals(75.0, transform.angle(), "Angle should be updated after positive rotation");
        
        // Test negative rotation
        transform.rotate(-15);
        assertEquals(60.0, transform.angle(), "Angle should be updated after negative rotation");
        
        // Test rotation normalization (exceeding 360)
        transform.rotate(330);
        assertEquals(30.0, transform.angle(), "Angle should be normalized to stay within 0-360 range");
        
        // Test rotation normalization (below 0)
        transform.rotate(-40);
        assertEquals(350.0, transform.angle(), "Angle should be normalized to stay within 0-360 range");
    }
    
    @Test
    public void testScale() {
        // Test positive scaling
        transform.scale(1.5);
        assertEquals(3.5, transform.scale(), "Scale should be updated after positive scaling");
        
        // Test negative scaling
        transform.scale(-0.5);
        assertEquals(3.0, transform.scale(), "Scale should be updated after negative scaling");
        
        // Test zero scaling
        transform.scale(0);
        assertEquals(3.0, transform.scale(), "Scale should remain unchanged after zero scaling");
    }
    
    @Test
    public void testPositionGetter() {
        assertEquals(initialPosition, transform.position(), "Position getter should return the correct position");
        // Test that returned position is not a reference to the internal position
        Point returnedPosition = transform.position();
        returnedPosition.translate(5, 5);
        assertEquals(initialPosition, transform.position(), "Modifying returned position should not affect internal state");
    }
    
    @Test
    public void testLayerGetter() {
        assertEquals(3, transform.layer(), "Layer getter should return the correct layer");
    }
    
    @Test
    public void testAngleGetter() {
        assertEquals(45.0, transform.angle(), "Angle getter should return the correct angle");
    }
    
    @Test
    public void testScaleGetter() {
        assertEquals(2.0, transform.scale(), "Scale getter should return the correct scale");
    }
    
    @Test
    public void testCombinedOperations() {
        // Perform a series of operations
        transform.move(new Point(5, 5), 2);  // Position: (15, 25), Layer: 5
        transform.rotate(15);                // Angle: 60
        transform.scale(0.5);                // Scale: 2.5
        
        // Verify final state
        assertEquals(15, transform.position().getX(), "X position should reflect all operations");
        assertEquals(25, transform.position().getY(), "Y position should reflect all operations");
        assertEquals(5, transform.layer(), "Layer should reflect all operations");
        assertEquals(60.0, transform.angle(), "Angle should reflect all operations");
        assertEquals(2.5, transform.scale(), "Scale should reflect all operations");
        
        // More complex movements and rotations
        transform.move(new Point(-10, 15), -3);  // Position: (5, 40), Layer: 2
        transform.rotate(350);                   // Angle: 50 (60 + 350 = 410 -> 410 % 360 = 50)
        transform.scale(-1);                     // Scale: 1.5
        
        // Verify final state after complex operations
        assertEquals(5, transform.position().getX(), "X position should reflect all complex operations");
        assertEquals(40, transform.position().getY(), "Y position should reflect all complex operations");
        assertEquals(2, transform.layer(), "Layer should reflect all complex operations");
        assertEquals(50.0, transform.angle(), "Angle should reflect all complex operations");
        assertEquals(1.5, transform.scale(), "Scale should reflect all complex operations");
    }
}
