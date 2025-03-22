import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void testClient() throws Exception {
        String input = """
                Poligono 4 1 2 5 6 8 7 12 14
                -1 3
                
                """;    // Input to be provided to System.in
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Save original System streams
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            System.setIn(inputStream);
            System.setOut(new PrintStream(outputStream));

            // Run the main method
            Client.main(new String[]{});

            // Flush output stream
            System.out.flush();

            // Assert output
            String expectedOutput = """
                    Poligono de 4 vertices: [(0,5), (4,9), (7,10), (11,17)]
                    """; // Expected output
            assertEquals(expectedOutput, outputStream.toString());

        } finally {
            // Restore original System streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
