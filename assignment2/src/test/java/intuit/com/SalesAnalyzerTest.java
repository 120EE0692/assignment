package intuit.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SalesAnalyzerTest {

    private SalesAnalyzer analyzer;

    @BeforeEach
    void setup() {
        List<OrderRecord> orders = List.of(
                new OrderRecord("Asia", "India", "Cosmetics", "Online", "M",
                        "2020-01-01", "1", "2020-01-05",
                        100, 10.0, 5.0, 1000.0, 500.0, 500.0),

                new OrderRecord("Europe", "France", "Clothes", "Offline", "H",
                        "2020-01-02", "2", "2020-01-06",
                        200, 20.0, 10.0, 4000.0, 2000.0, 2000.0),

                new OrderRecord("Asia", "India", "Cosmetics", "Offline", "L",
                        "2020-01-03", "3", "2020-01-07",
                        50, 10.0, 5.0, 500.0, 250.0, 250.0)
        );

        analyzer = new SalesAnalyzer(orders);
    }

    @Test
    void testTotalRevenue() {
        double total = analyzer.totalRevenue();
        assertEquals(1000.0 + 4000.0 + 500.0, total);
    }

    @Test
    void testTotalProfit() {
        double total = analyzer.totalProfit();
        assertEquals(500.0 + 2000.0 + 250.0, total);
    }

    @Test
    void testRevenueByRegion() {
        Map<String, Double> result = analyzer.revenueByRegion();

        assertEquals(1500.0, result.get("Asia"));     // 1000 + 500
        assertEquals(4000.0, result.get("Europe"));
    }

    @Test
    void testUnitsSoldByItemType() {
        Map<String, Integer> result = analyzer.unitsSoldByItemType();

        assertEquals(150, result.get("Cosmetics"));   // 100 + 50
        assertEquals(200, result.get("Clothes"));
    }

    @Test
    void testAverageProfitByCountry() {
        Map<String, Double> result = analyzer.averageProfitByCountry();

        assertEquals((500.0 + 250.0) / 2, result.get("India"));
        assertEquals(2000.0, result.get("France"));
    }

    @Test
    void testMostProfitableItemType() {
        String result = analyzer.mostProfitableItemType();

        // Cosmetics profit = 500 + 250 = 750
        // Clothes profit = 2000 → highest
        assertEquals("Clothes", result);
    }
}
