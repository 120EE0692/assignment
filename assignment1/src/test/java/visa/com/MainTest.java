package visa.com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {

    @Test
    void testMainEndToEnd() {
        // Capture System.out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        try {
            Main.main(new String[]{});

            String output = out.toString();

            // Verify final consumer output contains all values
            assertTrue(output.contains("Final destination container: [1, 2, 3, 4, 5]"));

            // Also optional: verify producer & consumer printed activity
            assertTrue(output.contains("Produced:"));
            assertTrue(output.contains("Consumed:"));
        } finally {
            System.setOut(original); // restore console
        }
    }
}
