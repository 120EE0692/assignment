# Assignment 2 — CSV Sales Analyzer

Overview
- This assignment reads sales/order data from a CSV file and performs analysis (aggregation, filtering, or reporting) over the records.

File structure
- `src/main/java/intuit/com/CsvReader.java` — Responsible for reading and parsing the CSV file (`sales.csv` in `src/main/resources`). Converts rows into `OrderRecord` objects and handles bad/malformed lines.
- `src/main/java/intuit/com/OrderRecord.java` — Plain data object representing a single order/sales record. Contains fields like order id, date, product, quantity, price, region, etc. Provides parsing helpers or a constructor used by `CsvReader`.
- `src/main/java/intuit/com/SalesAnalyzer.java` — Contains logic to analyze a collection of `OrderRecord` objects. Typical responsibilities include computing totals, averages, grouping by product/region, and identifying top-selling items.
- `src/main/java/intuit/com/Main.java` — Entrypoint: reads the CSV using `CsvReader`, passes records to `SalesAnalyzer`, and prints results or writes reports to stdout.
- `src/main/resources/sales.csv` — Sample data used by the program and tests.

Tests
- `src/test/java/intuit/com/CsvReaderTest.java` — Verifies CSV parsing, header handling, and robust behavior on malformed rows.
- `src/test/java/intuit/com/OrderRecordTest.java` — Tests the `OrderRecord` parsing and data validation.
- `src/test/java/intuit/com/SalesAnalyzerTest.java` — Tests aggregation, grouping, and analysis results for known datasets.
- `src/test/java/intuit/com/MainTest.java` — Light-weight integration test for `Main` (if present).

Logic summary
- CsvReader: opens `sales.csv` from resources, iterates rows, and maps them to `OrderRecord` objects. It should handle header rows, trim whitespace, and convert types (dates, integers, decimals). Error handling should either skip bad rows with a logged warning or throw a well-documented exception depending on design.
- OrderRecord: simple immutable-like POJO representing a single row. Includes getters and may implement `equals`/`hashCode` for testing.
- SalesAnalyzer: accepts a list/stream of `OrderRecord` and computes results such as total revenue, total units sold, per-product or per-region summaries, and top-N lists. Implementation may use Java Streams, collectors, or classic iteration.

How to run
1. Make sure Java (JDK) and Maven are installed and on `PATH`.
2. Run the unit tests:

```powershell
cd assignment2; mvn test
```

3. Run the app (prints analysis to stdout):

```powershell
cd assignment2; mvn exec:java -Dexec.mainClass="intuit.com.Main"
```