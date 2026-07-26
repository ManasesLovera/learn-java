package examples;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        PathExample.demonstratePaths();
        Path path = Path.of("file.txt");
        try {

            FileReadWriteExample.processFile(path);
        } catch (IOException e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }
}
