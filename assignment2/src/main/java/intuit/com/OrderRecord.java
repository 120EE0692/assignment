package intuit.com;

public record OrderRecord(
    String region,
    String country,
    String itemType,
    String salesChannel,
    String orderPriority,
    String orderDate, 
    String orderId,
    String shipDate,
    int unitsSold,
    double unitPrice,
    double unitCost,
    double totalRevenue,
    double totalCost,
    double totalProfit
) {}