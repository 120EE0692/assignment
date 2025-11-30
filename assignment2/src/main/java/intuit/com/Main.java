package intuit.com;

public class Main {

    private static final String SALES_CSV = "assignment2/src/main/resources/sales.csv";

    public static void main(String[] args) {
        analysis(SALES_CSV);
    }

    public static void analysis(String csvPath) {
        var orders = CsvReader.readCsv(csvPath);
        SalesAnalyzer analyzer = new SalesAnalyzer(orders);

        System.out.println("\n========== Sales Analysis Results ==========\n");
        System.out.println("Total Revenue: " + analyzer.totalRevenue() + "\n");
        System.out.println("Total Profit: " + analyzer.totalProfit() + "\n");
        System.out.println("Revenue by Region: " + analyzer.revenueByRegion() + "\n");
        System.out.println("Units Sold by Item Type: " + analyzer.unitsSoldByItemType() + "\n");
        System.out.println("Average Profit by Country: " + analyzer.averageProfitByCountry() + "\n");
        System.out.println("Most Profitable Item Type: " + analyzer.mostProfitableItemType() + "\n");
    }
}
