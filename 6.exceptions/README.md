# Java 21 Exceptions & Resource Management Guide

A comprehensive guide to exception handling, checked vs. unchecked exceptions, Try-With-Resources, custom exception design, and best practices in Java 21.

## Java Exception Hierarchy Overview

```text
                     java.lang.Throwable
                              │
             ┌────────────────┴────────────────┐
             │                                 │
     java.lang.Error                 java.lang.Exception
  (OutOfMemoryError, etc.)                     │
                               ┌───────────────┴───────────────┐
                               │                               │
                       Checked Exceptions            java.lang.RuntimeException
                     (IOException, SQLException)       (NullPointerException,
                                                       IllegalArgumentException)
```

## Exception Mechanics & Features

### 1. Checked vs. Unchecked Exceptions

#### Checked Exception Mechanics

Checked exceptions inherit from `java.lang.Exception` (excluding `RuntimeException`). The compiler forces the caller to either handle them with a `try-catch` block or declare them in the method signature with `throws`.

```java
import java.io.FileReader;
import java.io.IOException;

public class CheckedExample {
    public static void readFile(String path) throws IOException {
        FileReader reader = new FileReader(path);
        reader.close();
    }
}
```

#### Unchecked Exception Mechanics

Unchecked exceptions inherit from `java.lang.RuntimeException`. They represent programming errors (e.g., array index out of bounds, null references) and do not require explicit compile-time handling.

```java
public class UncheckedExample {
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divider cannot be zero.");
        }
        return a / b;
    }
}
```

---

### 2. Multi-Catch & Exception Rethrowing

#### Multi-Catch Syntax

Introduced to catch multiple unrelated exception types in a single `catch` block using the pipe (`|`) operator.

```java
import java.io.IOException;
import java.sql.SQLException;

public class MultiCatchExample {
    public static void processData() {
        try {
            // Risky operation
        } catch (IOException | SQLException e) {
            System.err.println("I/O or Database Error: " + e.getMessage());
        }
    }
}
```

---

### 3. Try-With-Resources (`AutoCloseable`)

#### Try-With-Resources Mechanics

Resources that implement `java.lang.AutoCloseable` or `java.io.Closeable` are automatically closed at the end of the `try` block, even if an exception is thrown. This eliminates manual cleanup in `finally` blocks and handles suppressed exceptions automatically.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourceExample {
    public static String readFirstLine(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } // br is automatically closed here
    }
}
```

---

### 4. Custom Exception Design

#### Designing Domain Exceptions

Create domain-specific custom exceptions by extending `RuntimeException` (for unchecked business logic errors) or `Exception` (for recoverable business conditions).

```java
public class InsufficientFundsException extends RuntimeException {
    private final double amount;

    public InsufficientFundsException(double amount, String message) {
        super(message);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
```

---

## Exceptions Best Practices Cheat Sheet

| Practice Rule | Description | Incorrect Approach | Correct Approach |
| --- | --- | --- | --- |
| **Never Swallow Exceptions** | Always log or propagate exceptions | `catch (Exception e) {}` | Log exception stack trace or rethrow wrapping exception |
| **Catch Specific Exceptions** | Avoid catching generic `Exception` or `Throwable` | `catch (Exception e)` | `catch (FileNotFoundException e)` |
| **Preserve Stack Trace** | Pass cause exception when wrapping | `throw new CustomException(e.getMessage())` | `throw new CustomException("Failed", e)` |
| **Prefer Try-With-Resources** | Always auto-close closeable streams | Manual `close()` in `finally` block | Use `try (Resource r = ...)` |
