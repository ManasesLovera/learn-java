# Java Concurrency & Multithreading Guide

A comprehensive guide to traditional platform threads, thread synchronization, memory visibility, thread pools (`ExecutorService`), and asynchronous programming (`CompletableFuture`).

## Java Concurrency Architecture Overview

```text
                     Java Concurrency Model
                               │
         ┌─────────────────────┴─────────────────────┐
         │                                           │
  Platform Threads                           Concurrency Utilities
(1:1 Mapping to OS Threads)              (java.util.concurrent: Executors,
                                          Locks, Atomics, CompletableFuture)
```

## Core Concurrency Concepts & Mechanics

### 1. Platform Threads & Creation

#### Platform Thread Lifecycle

Platform threads in Java are thin wrappers around native operating system threads. They carry a heavy stack allocation (~1MB memory per thread).

```java
public class ThreadExample {
    public static void runThread() {
        Thread thread = new Thread(() -> {
            System.out.println("Running on thread: " + Thread.currentThread().getName());
        });
        thread.start();
    }
}
```

---

### 2. Synchronization & Memory Visibility

#### Intrinsic Locks & `volatile`

* **`synchronized`:** Ensures mutual exclusion and memory visibility by acquiring an intrinsic monitor lock on an object.
* **`volatile`:** Guarantees memory visibility (flushes reads/writes to main memory) without acquiring a lock, but does not guarantee atomicity for compound operations (e.g. `count++`).

```java
public class SharedCounter {
    private int count = 0;
    private volatile boolean running = true;

    // Thread-safe increment
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public void stop() {
        running = false; // Immediately visible to other threads
    }
}
```

---

### 3. Thread Pools (`ExecutorService`)

#### Managing Thread Pools

Instead of manually spawning OS threads, use `ExecutorService` thread pools to reuse worker threads and limit resource consumption.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void processTasks() {
        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                });
            }
        } // ExecutorService automatically shuts down at end of try-with-resources (Java 19+)
    }
}
```

---

### 4. Asynchronous Pipeline with `CompletableFuture`

#### Non-Blocking Futures

`CompletableFuture<T>` enables asynchronous task chaining, composition, and non-blocking callback handling.

```java
import java.util.concurrent.CompletableFuture;

public class AsyncExample {
    public static void runAsyncPipeline() {
        CompletableFuture.supplyAsync(() -> "User Data")
            .thenApply(data -> data + " -> Processed")
            .thenAccept(result -> System.out.println("Result: " + result))
            .join(); // Blocks until pipeline completes
    }
}
```

---

## Concurrency Primitives Comparison Cheat Sheet

| Primitive | Mechanism | Primary Use Case | Overheads / Caveats |
| --- | --- | --- | --- |
| **`synchronized`** | Intrinsic Monitor Lock | Mutual exclusion on critical sections | Thread blocking, potential deadlocks |
| **`volatile`** | Memory Fence / Barrier | Single variable visibility | No atomicity for compound updates (`count++`) |
| **`AtomicInteger`** | Lock-free CAS (Compare-And-Swap) | Thread-safe counter updates | Hardware CPU level instructions |
| **`ReentrantLock`** | Explicit Lock (`java.util.concurrent`) | Advanced locking (tryLock, timed lock) | Must unlock in `finally` block |
