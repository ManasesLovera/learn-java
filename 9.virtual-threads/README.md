# Java 21 Virtual Threads & Concurrency Revolution Guide

A comprehensive guide to Virtual Threads (Project Loom), carrier thread mounting/unmounting, thread pinning prevention, Structured Concurrency, and Scoped Values in Java 21.

## Java 21 Virtual Threads Architecture

```text
               Virtual Threads (Millions of User-Mode Threads)
               VT 1      VT 2      VT 3      VT 4 ... VT 1,000,000
                 │         │         │         │
      ═══════════╧═════════╧═════════╧═════════╧══════════ (JVM Scheduler)
                 Carrier OS Threads (Platform Threads = CPU Cores)
                 Carrier 1       Carrier 2       Carrier 3
```

## Virtual Threads Mechanics & Features

### 1. Virtual Threads vs. Platform Threads

#### Mechanics of Virtual Threads

* **Platform Threads:** 1:1 mapping to OS threads. Managed by OS kernel, heavy memory footprint (~1MB stack), slow context switching.
* **Virtual Threads (JDK 21):** $M:N$ user-mode threads managed directly by the JVM runtime. Lightweight memory footprint (~few hundred bytes), near-zero creation cost, instant context switching.
* When a Virtual Thread encounters a blocking I/O operation (e.g., database read, network call, `Thread.sleep`), the JVM **unmounts** it from the underlying carrier OS thread. The carrier thread is immediately freed to execute other virtual threads!

---

### 2. Creating & Starting Virtual Threads

#### Java 21 Virtual Thread Syntax Examples

```java
import java.util.concurrent.Executors;

public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. Using Thread.ofVirtual() factory
        Thread vThread = Thread.ofVirtual().start(() -> {
            System.out.println("Running on Virtual Thread: " + Thread.currentThread());
        });
        vThread.join();

        // 2. Using Virtual Thread Per Task Executor
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    // Simulate blocking I/O call
                    Thread.sleep(100);
                    return taskId;
                });
            }
        } // Automatically waits for all 10,000 tasks to finish!
    }
}
```

---

### 3. Avoiding Thread Pinning

#### Carrier Thread Pinning Prevention

A virtual thread is **pinned** to its carrier OS thread if it executes a blocking operation inside:

1. A `synchronized` block or method.
2. A native method or foreign function call.

When pinned, the carrier OS thread cannot be unmounted, defeating the scalability of virtual threads.

```java
import java.util.concurrent.locks.ReentrantLock;

public class PinningPrevention {
    private final ReentrantLock lock = new ReentrantLock();

    // GOOD: ReentrantLock allows Virtual Thread unmounting!
    public void safeBlockingOperation() {
        lock.lock();
        try {
            // Blocking I/O work here
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
```

---

### 4. Structured Concurrency & Scoped Values

#### Structured Concurrency Mechanics

Treats groups of concurrent tasks running in different threads as a single unit of work, ensuring child tasks are canceled if the main operation fails or completes.

```java
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public class StructuredConcurrencyDemo {
    public record UserProfile(String name, int score) {}

    public static UserProfile fetchUserData() throws Exception {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<String> nameTask = scope.fork(() -> "Alice");
            Supplier<Integer> scoreTask = scope.fork(() -> 95);

            scope.join();           // Wait for both subtasks
            scope.throwIfFailed();  // Propagate exceptions if any subtask failed

            return new UserProfile(nameTask.get(), scoreTask.get());
        }
    }
}
```

---

## Virtual Threads vs. Platform Threads Cheat Sheet

| Feature / Metric | Platform Threads (Traditional) | Virtual Threads (Java 21) |
| --- | --- | --- |
| **OS Thread Ratio** | 1:1 (Direct OS Kernel thread) | $M:N$ (Millions of VTs on few Carrier OS threads) |
| **Memory Cost** | ~1 MB stack memory per thread | ~256 bytes initial stack memory |
| **Max Capacity** | Few thousands per JVM instance | Millions per JVM instance |
| **Blocking I/O Handling** | Blocks OS thread (idle resource waste) | Unmounts VT from Carrier thread (100% CPU utilization) |
| **Best Used For** | CPU-bound computation tasks | I/O-bound tasks (HTTP servers, database queries) |
| **Thread Pooling** | **Mandatory** (`Executors.newFixedThreadPool`) | **Anti-pattern** (Create new VT per task) |
