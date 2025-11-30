package intuit.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderRecordTest {

    @Test
    void testOrderRecordFields() {

        OrderRecord record = new OrderRecord(
                "Asia",
                "India",
                "Cosmetics",
                "Online",
                "M",
                "2020-01-01",
                "12345",
                "2020-01-05",
                100,
                10.0,
                5.0,
                1000.0,
                500.0,
                500.0
        );

        assertEquals("Asia", record.region());
        assertEquals("India", record.country());
        assertEquals("Cosmetics", record.itemType());
        assertEquals("Online", record.salesChannel());
        assertEquals("M", record.orderPriority());
        assertEquals("2020-01-01", record.orderDate());
        assertEquals("12345", record.orderId());
        assertEquals("2020-01-05", record.shipDate());
        assertEquals(100, record.unitsSold());
        assertEquals(10.0, record.unitPrice());
        assertEquals(5.0, record.unitCost());
        assertEquals(1000.0, record.totalRevenue());
        assertEquals(500.0, record.totalCost());
        assertEquals(500.0, record.totalProfit());
    }

    @Test
    void testEqualsAndHashCode() {
        OrderRecord r1 = new OrderRecord(
                "Asia", "India", "Cosmetics", "Online", "M", "2020-01-01",
                "12345", "2020-01-05", 100, 10, 5, 1000, 500, 500
        );

        OrderRecord r2 = new OrderRecord(
                "Asia", "India", "Cosmetics", "Online", "M", "2020-01-01",
                "12345", "2020-01-05", 100, 10, 5, 1000, 500, 500
        );

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        OrderRecord record = new OrderRecord(
                "Asia", "India", "Cosmetics", "Online", "M", "2020-01-01",
                "12345", "2020-01-05", 100, 10, 5, 1000, 500, 500
        );

        String s = record.toString();

        assertTrue(s.contains("Asia"));
        assertTrue(s.contains("India"));
        assertTrue(s.contains("Cosmetics"));
        assertTrue(s.contains("12345"));
        assertTrue(s.contains("1000.0"));
    }
}
