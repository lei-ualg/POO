import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void testValidRectangleInput() {
        String input = "0 0 4 0 4 3 0 3";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        Client.main(new String[]{});

        String expectedOutput = "Retângulo: [(0, 0), (4, 0), (4, 3), (0, 3)]\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testInvalidRectangleInput() {
        String input = "0 0 4 0 5 3 0 3";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        Client.main(new String[]{});

        String expectedOutput = "Retângulo:vi\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testRotatedRectangleInput() {
        String input = "1 1 4 1 4 4 1 4";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        Client.main(new String[]{});

        String expectedOutput = "Retângulo: [(1, 1), (4, 1), (4, 4), (1, 4)]\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
