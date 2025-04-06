import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void testClient() throws Exception {
        String input = """
                3
                2
                fastBall
                1 4 0 0 1
                1 1 1
                3 0 0 0 0
                rotorBat
                8.5 2.5 0 0 1
                8 1 8 4 9 4 9 1
                0 0 0 45 0
                
                
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
                    fastBall rotorBat
                    rotorBat fastBall
                    """; // Expected output
            assertEquals(expectedOutput, outputStream.toString());

        } finally {
            // Restore original System streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
