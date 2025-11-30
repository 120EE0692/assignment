package intuit.com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void testReadCsvSuccess() throws Exception {

        // Temporary CSV file
        Path csvFile = tempDir.resolve("sales.csv");

        String csvContent = """
                Region,Country,ItemType,SalesChannel,OrderPriority,OrderDate,OrderId,ShipDate,UnitsSold,UnitPrice,UnitCost,TotalRevenue,TotalCost,TotalProfit
                Asia,India,Cosmetics,Online,M,2020-01-01,1,2020-01-05,100,10.0,5.0,1000.0,500.0,500.0
                Europe,France,Clothes,Offline,H,2020-01-02,2,2020-01-06,200,20.0,10.0,4000.0,2000.0,2000.0
                """;

        Files.writeString(csvFile, csvContent);

        // Act
        List<OrderRecord> records = CsvReader.readCsv(csvFile.toString());

        // Assert size
        assertEquals(2, records.size());

        // Validate record 1
        OrderRecord r1 = records.get(0);
        assertEquals("Asia", r1.region());
        assertEquals("India", r1.country());
        assertEquals("Cosmetics", r1.itemType());
        assertEquals("Online", r1.salesChannel());
        assertEquals("M", r1.orderPriority());
        assertEquals("2020-01-01", r1.orderDate());
        assertEquals("1", r1.orderId());
        assertEquals("2020-01-05", r1.shipDate());
        assertEquals(100, r1.unitsSold());
        assertEquals(10.0, r1.unitPrice());
        assertEquals(5.0, r1.unitCost());
        assertEquals(1000.0, r1.totalRevenue());
        assertEquals(500.0, r1.totalCost());
        assertEquals(500.0, r1.totalProfit());

        // Validate record 2
        OrderRecord r2 = records.get(1);
        assertEquals("Europe", r2.region());
        assertEquals("France", r2.country());
        assertEquals("Clothes", r2.itemType());
        assertEquals("Offline", r2.salesChannel());
        assertEquals("H", r2.orderPriority());
        assertEquals("2020-01-02", r2.orderDate());
        assertEquals("2", r2.orderId());
        assertEquals("2020-01-06", r2.shipDate());
        assertEquals(200, r2.unitsSold());
        assertEquals(20.0, r2.unitPrice());
        assertEquals(10.0, r2.unitCost());
        assertEquals(4000.0, r2.totalRevenue());
        assertEquals(2000.0, r2.totalCost());
        assertEquals(2000.0, r2.totalProfit());
    }

    @Test
    void testReadCsv_FileNotFound() {
        Exception e = assertThrows(RuntimeException.class, () ->
                CsvReader.readCsv("non_existing_file.csv")
        );

        assertTrue(e.getMessage().contains("Error reading CSV file"));
    }
}
