package examples;

import java.nio.file.Path;

public class PathExample {

    public static void demonstratePaths() {

        Path relativePath = Path.of("data", "config.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        Path parent = absolutePath.getParent();

        System.out.println("Relative: " + relativePath);
        System.out.println("Absolute: " + absolutePath);
        System.out.println("Parent: " + parent);
    }
}
