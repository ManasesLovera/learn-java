# Java 21 Mastery Roadmap (Pre-Frameworks)

## 1. Core Syntax & Modern Features

- **Type System & Operators:**

  - Primitive types (`int`, `double`, `boolean`, `char`, etc.) vs. Reference types.
  - Autoboxing and unboxing primitives (`int` <-> `Integer`).
  - Literal formats (Text Blocks `"""`, underscored numbers `1_000_000`).
- **Object-Oriented Syntax:**
  - Classes, interfaces (default methods, static methods, private interface methods), abstract classes.
  - Enums (with custom methods, fields, and constructors).
  - Scope & Modifiers: `public`, `protected`, package-private (default), `private`, `final`, `static`.
- **Modern Java Features (JDK 11–21):**
  - `var` local variable type inference.
  - **Records:** Shallowly immutable data carriers (`public record User(String name, int age) {}`).
  - **Sealed Classes:** Restricting class hierarchies (`public sealed class Shape permits Circle, Square`).
  - **Pattern Matching:** `instanceof` binding (`if (obj instanceof String s)`) and `switch` expressions with pattern matching and guards (`when`).

## 2. JVM Architecture & Memory Model

- **Compilation Pipeline:**

  - Source code (`.java`) -> Bytecode (`.class`) via `javac` -> Execution via JVM JIT Compiler.
- **JVM Components:**
  - **ClassLoaders:** Bootstrap, Extension/Platform, and Application class loaders.
  - **Execution Engine:** Interpreter vs. JIT Compiler (C1, C2) and Tiered Compilation.
- **Memory Management (Runtime Data Areas):**
  - **Stack Memory:** Frame-based storage for primitive variables and local references (thread-isolated).
  - **Heap Memory:** Object allocations, Young Generation (Eden, Survivor spaces), and Old Generation.
  - **Metaspace:** Native memory storage for class metadata.
- **Garbage Collection (GC):**
  - How GC identifies garbage (Reachability, Root Set Analysis).
  - Standard Collectors: G1GC (default), ZGC (ultra-low latency).

## 3. Collections Framework & Generics

- **Generics System:**

  - Type parameters, bounded type parameters (`<T extends Number>`).
  - Wildcards (`? super T` vs. `? extends T`) and Type Erasure mechanics.
- **Core Data Structures (`java.util`):**
  - **Lists:** `ArrayList` (contiguous array) vs. `LinkedList`.
  - **Sets:** `HashSet` (hash table backed), `TreeSet` (Red-Black tree ordering).
  - **Maps:** `HashMap` (bucket arrays, treeification under high collision) vs. `TreeMap`.
  - **Sequenced Collections (JDK 21):** `SequencedCollection`, `SequencedSet`, `SequencedMap` for explicit first/last element operations.
- **Functional Programming & Streams (`java.util.stream`):**
  - Functional Interfaces: `Function`, `Predicate`, `Consumer`, `Supplier`.
  - Stream pipeline construction: intermediate operations (`map`, `filter`, `flatMap`) vs. terminal operations (`collect`, `reduce`, `findFirst`).
  - Collectors (`Collectors.toList()`, `Collectors.groupingBy()`).

## 4. Exceptions, I/O & Standard Library

- **Exception Handling:**

  - Checked exceptions (`Exception`) vs. Unchecked exceptions (`RuntimeException`).
  - `try-catch-finally` and **Try-With-Resources** (`AutoCloseable` interface).
- **I/O & File Systems (`java.nio`):**
  - Working with paths and files (`java.nio.file.Path`, `Files`).
  - Streams (`InputStream`/`OutputStream`) vs. Buffers (`BufferedReader`/`BufferedWriter`).
- **Concurrency Primitives & Utilities:**
  - Core Java reflection concepts (`java.lang.reflect`).
  - Functional date/time API (`java.time.*`: `Instant`, `LocalDateTime`, `ZonedDateTime`).

## 5. Concurrency & Java 21 Multithreading

- **Traditional Thread Model:**

  - Platform Threads (1:1 mapping to OS threads).
  - Synchronization mechanics (`synchronized` blocks, `volatile` keyword).
  - High-level concurrency (`java.util.concurrent`): `ExecutorService`, `ThreadPoolExecutor`, `CompletableFuture`.
- **Java 21 Concurrency Revolution:**
  - **Virtual Threads (Project Loom):** Lightweight user-mode threads managed by the JVM (M:N mapping onto carrier threads).
  - Creating Virtual Threads (`Thread.ofVirtual().start(...)` and `Executors.newVirtualThreadPerTaskExecutor()`).
  - Structured Concurrency for task lifecycle management.
  - Scoped Values (`ScopedValue`) as a modern, lightweight alternative to `ThreadLocal`.
