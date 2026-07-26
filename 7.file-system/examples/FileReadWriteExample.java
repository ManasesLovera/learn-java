package examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileReadWriteExample {

    public static void processFile(Path filePath) throws IOException {

        // Write text string to file
        Files.writeString(filePath, "Java 21 NIO.2 features\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        // Read entire content as string
        String content = Files.readString(filePath);
        System.out.println("Content: " + content);
    }
}
