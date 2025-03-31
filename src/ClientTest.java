import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void testClient() throws Exception {
        String input = """
                Monster
                3 6 0 90 1
                0 0 0 2 2 2 2 0
                move 1 1 0
                rotate -90
                
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
                    Monster
                    (4.00,7.00) 0 0.00 1.00
                    (3.00,6.00) (3.00,8.00) (5.00,8.00) (5.00,6.00)
                    """; // Expected output
            assertEquals(expectedOutput, outputStream.toString());

        } finally {
            // Restore original System streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
