package intuit.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {

    public static List<OrderRecord> readCsv(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(arr -> new OrderRecord(
                            arr[0].trim(),
                            arr[1].trim(),
                            arr[2].trim(),
                            arr[3].trim(),
                            arr[4].trim(),
                            arr[5].trim(),
                            arr[6].trim(),
                            arr[7].trim(),
                            Integer.parseInt(arr[8].trim()),
                            Double.parseDouble(arr[9].trim()),
                            Double.parseDouble(arr[10].trim()),
                            Double.parseDouble(arr[11].trim()),
                            Double.parseDouble(arr[12].trim()),
                            Double.parseDouble(arr[13].trim())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }
    }
}
