import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameEngineTest {

    // Test fixtures
    private IGameEngine gameEngine;
    private GameObject gameObject1;
    private GameObject gameObject2;
    private GameObject gameObject3;
    private Transform transform1;
    private Transform transform2;
    private Transform transform3;
    private CollPoly collider1;
    private CollPoly collider2;
    private CollCircle collider3;
    private Behaviour behaviour1;
    private Behaviour behaviour2;
    private Behaviour behaviour3;

    @BeforeEach
    public void setUp() {
        gameEngine = new GameEngine();
        
        // Setup first game object
        transform1 = new Transform(new Point(10, 10), 0, 1.0, 1, null);
        collider1 = new CollPoly(new Point(5, 15), new Point(15, 5), transform1);
        transform1.collider = collider1;
        behaviour1 = new Behaviour();
        gameObject1 = new GameObject("Object1", transform1, collider1, new Shape(), behaviour1);
        
        // Setup second game object
        transform2 = new Transform(new Point(30, 30), 0, 1.0, 1, null);
        collider2 = new CollPoly(new Point(25, 35), new Point(35, 25), transform2);
        transform2.collider = collider2;
        behaviour2 = new Behaviour();
        gameObject2 = new GameObject("Object2", transform2, collider2, new Shape(), behaviour2);
        
        // Setup third game object with circle collider (different layer)
        transform3 = new Transform(new Point(20, 20), 0, 1.0, 2, null);
        collider3 = new CollCircle(5.0, new Point(20, 20), transform3);
        transform3.collider = collider3;
        behaviour3 = new Behaviour();
        gameObject3 = new GameObject("Object3", transform3, collider3, new Shape(), behaviour3);
    }

    @Test
    public void testAddEnabledAndList() {
        gameEngine.addEnabled(gameObject1);
        
        List<IGameObject> enabled = gameEngine.getEnabled();
        assertEquals(1, enabled.size());
        assertTrue(enabled.contains(gameObject1));
        assertTrue(gameEngine.isEnabled(gameObject1));
    }
    
    @Test
    public void testAddDisabledAndList() {
        gameEngine.addDisabled(gameObject1);
        
        List<IGameObject> disabled = gameEngine.getDisabled();
        assertEquals(1, disabled.size());
        assertTrue(disabled.contains(gameObject1));
        assertTrue(gameEngine.isDisabled(gameObject1));
    }
    
    @Test
    public void testEnableDisable() {
        // Add and disable an object
        gameEngine.addEnabled(gameObject1);
        gameEngine.disable(gameObject1);
        
        assertFalse(gameEngine.isEnabled(gameObject1));
        assertTrue(gameEngine.isDisabled(gameObject1));
        
        // Enable it again
        gameEngine.enable(gameObject1);
        
        assertTrue(gameEngine.isEnabled(gameObject1));
        assertFalse(gameEngine.isDisabled(gameObject1));
    }
    
    @Test
    public void testDestroyObject() {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addEnabled(gameObject2);
        
        assertEquals(2, gameEngine.getEnabled().size());
        
        gameEngine.destroy(gameObject1);
        
        assertEquals(1, gameEngine.getEnabled().size());
        assertFalse(gameEngine.isEnabled(gameObject1));
        assertTrue(gameEngine.isEnabled(gameObject2));
    }
    
    @Test
    public void testDestroyAll() {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addEnabled(gameObject2);
        gameEngine.addDisabled(gameObject3);
        
        gameEngine.destroyAll();
        
        assertEquals(0, gameEngine.getEnabled().size());
        assertEquals(0, gameEngine.getDisabled().size());
    }
    
    @Test
    public void testCheckCollisions() {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addEnabled(gameObject2);
        
        // Objects should be far apart initially - move them closer to create a collision
        transform2.move(new Point(-20, -20), 0);
        collider1.onUpdate();
        collider2.onUpdate();
        
        // Now run collision detection
        gameEngine.checkCollisions();
        
        // Note: We can only test that the method runs without errors
        // since we're using the standard Behaviour class
    }
    
    @Test
    public void testCollisionLayerSeparation() {
        // Add objects on different layers
        gameEngine.addEnabled(gameObject1); // Layer 1
        gameEngine.addEnabled(gameObject3); // Layer 2
        
        // Move them to overlap
        transform3.move(new Point(-10, -10), 0);
        collider1.onUpdate();
        collider3.onUpdate();
        
        // Run collision detection - should not detect collision between different layers
        gameEngine.checkCollisions();
        
        // Change layer and check again
        transform3.move(new Point(0, 0), -1); // Change to layer 1
        collider3.onUpdate();
        
        gameEngine.checkCollisions();
    }
}
