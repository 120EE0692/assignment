package intuit.com;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesAnalyzer {

    private final List<OrderRecord> orders;

    public SalesAnalyzer(List<OrderRecord> orders) {
        this.orders = orders;
    }

    // 1.total Revenue
    public double totalRevenue() {
        return orders.stream()
                .mapToDouble(OrderRecord::totalRevenue)
                .sum();
    }
    
    // 2.total Profit
    public double totalProfit() {
        return orders.stream()
                .mapToDouble(OrderRecord::totalProfit)
                .sum();
    }

    // 3. Revenue by Region
    public Map<String, Double> revenueByRegion() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        OrderRecord::region,
                        Collectors.summingDouble(OrderRecord::totalRevenue)
                ));
    }

    // 4. Total Units Sold by Item Type
    public Map<String, Integer> unitsSoldByItemType() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        OrderRecord::itemType,
                        Collectors.summingInt(OrderRecord::unitsSold)
                ));
    }

    // 5. Average Profit by Country 
    public Map<String, Double> averageProfitByCountry() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        OrderRecord::country,
                        Collectors.averagingDouble(OrderRecord::totalProfit)
                ));
    }

    // 6. Most Profitable Item Type
    public String mostProfitableItemType() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        OrderRecord::itemType,
                        Collectors.summingDouble(OrderRecord::totalProfit)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

}
