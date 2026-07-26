# Java 21 File Systems & I/O Guide

A comprehensive guide to working with file systems, NIO.2 (`java.nio.file`), byte streams, character buffers, and high-performance file operations in Java 21.

## Java I/O Architecture Overview

```text
                     Java I/O Systems
                            │
         ┌──────────────────┴──────────────────┐
         │                                     │
   Classic Stream I/O                     NIO.2 File API
 (java.io.*: Readers,                  (java.nio.file.*: Path,
  Writers, InputStreams)                Files, FileSystem)
```

## Core NIO.2 Concepts & Operations

### 1. The `Path` Interface

#### Path Representation & Resolution

`Path` represents a hierarchical file or directory path. In modern Java, `Path.of()` is the standard factory method replacing `Paths.get()`.

```java
import java.nio.file.Path;

public class PathExample {
    public static void demonstratePaths() {
        Path relativePath = Path.of("data", "config.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        Path parent = absolutePath.getParent();

        System.out.println("Absolute: " + absolutePath);
        System.out.println("Parent: " + parent);
    }
}
```

---

### 2. File Manipulation with `Files` Utility

#### Reading & Writing Text Files

Java 11+ introduced convenient one-liner helper methods on `Files` for small-to-medium files (`Files.readString()`, `Files.writeString()`).

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileReadWriteExample {
    public static void processFile(Path filePath) throws IOException {
        // Write text string to file
        Files.writeString(filePath, "Java 21 NIO.2 Features\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        // Read entire content as String
        String content = Files.readString(filePath);
        System.out.println("Content: " + content);
    }
}
```

---

### 3. Buffers & Large File Handling

#### High-Performance Buffered Reading

For large files that cannot fit entirely in memory, use `Files.newBufferedReader()` or `Files.lines()` with `Stream<String>`.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LargeFileReader {
    public static void processLargeFile(Path filePath) throws IOException {
        // Memory-efficient stream processing with Try-With-Resources
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.filter(line -> line.contains("ERROR"))
                 .forEach(System.out::println);
        }
    }
}
```

---

### 4. Directory Traversals (`Files.walk`)

#### Recursive Directory Walking

`Files.walk()` recursively traverses a directory tree returning a lazy `Stream<Path>`.

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DirectoryWalker {
    public static void findJavaFiles(Path searchDir) throws IOException {
        try (Stream<Path> stream = Files.walk(searchDir)) {
            stream.filter(Files::isRegularFile)
                  .filter(path -> path.toString().endsWith(".java"))
                  .forEach(System.out::println);
        }
    }
}
```

---

## File I/O Method Comparison Cheat Sheet

| Task | Recommended Method | Time & Memory Profile | Notes |
| --- | --- | --- | --- |
| **Small Text File Read** | `Files.readString(path)` | Low latency, reads entire file into heap | Introduced in Java 11 |
| **Small Text File Write** | `Files.writeString(path, text)` | Low latency | Supports `StandardOpenOption` |
| **Large File Processing** | `Files.lines(path)` | Stream-based, $O(1)$ memory buffer | Must close stream with try-with-resources |
| **Directory Search** | `Files.walk(dir)` | Recursive lazy stream | Depth limit customizable |
