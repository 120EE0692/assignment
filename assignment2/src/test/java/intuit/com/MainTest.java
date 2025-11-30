package intuit.com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @TempDir
    Path tempDir;

    @Test
    void testMainOutput() throws Exception {

        // Create temp CSV
        Path csvFile = tempDir.resolve("sales.csv");

        String csvContent = """
                Region,Country,ItemType,SalesChannel,OrderPriority,OrderDate,OrderId,ShipDate,UnitsSold,UnitPrice,UnitCost,TotalRevenue,TotalCost,TotalProfit
                Asia,India,Cosmetics,Online,M,2020-01-01,1,2020-01-05,100,10.0,5.0,1000.0,500.0,500.0
                """;

        Files.writeString(csvFile, csvContent);

        // Capture output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Run Main using temp CSV
        Main.analysis(csvFile.toString());

        System.setOut(System.out);

        String output = out.toString();

        // Assertions
        assertTrue(output.contains("Total Revenue: 1000.0"));
        assertTrue(output.contains("Total Profit: 500.0"));
        assertTrue(output.contains("Revenue by Region: {Asia=1000.0}"));
        assertTrue(output.contains("Units Sold by Item Type: {Cosmetics=100}"));
        assertTrue(output.contains("Average Profit by Country: {India=500.0}"));
        assertTrue(output.contains("Most Profitable Item Type: Cosmetics"));
    }
}
