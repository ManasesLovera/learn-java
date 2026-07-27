package examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LargeFileReader {

    /**
     * @param filePath
     */
    public static void processLargeFile(Path filePath) throws IOException {

        // Memory efficient stream processing with Try-With-Resources
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.filter(line -> line.contains("ERROR"))
                    .forEach(System.out::println);
        }
    }
}
