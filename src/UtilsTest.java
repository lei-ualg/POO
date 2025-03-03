import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void testEq() {
        assertTrue(Utils.eq(1.0, 1.0));
        assertTrue(Utils.eq(1.0, 1.0 + Utils.epsilon / 2));
        assertFalse(Utils.eq(1.0, 1.0 + 2 * Utils.epsilon));
    }

    @Test
    public void testGt() {
        assertTrue(Utils.gt(2.0, 1.0));
        assertFalse(Utils.gt(1.0, 1.0));
        assertFalse(Utils.gt(1.0, 2.0));
        assertFalse(Utils.gt(1.0, 1.0 + Utils.epsilon / 2));
        assertTrue(Utils.gt(1.0 + 2 * Utils.epsilon, 1.0));
    }

    @Test
    public void testGe() {
        assertTrue(Utils.ge(2.0, 1.0));
        assertTrue(Utils.ge(1.0, 1.0));
        assertFalse(Utils.ge(1.0, 2.0));
        assertTrue(Utils.ge(1.0, 1.0 + Utils.epsilon / 2));
        assertTrue(Utils.ge(1.0 + 2 * Utils.epsilon, 1.0));
    }
}
