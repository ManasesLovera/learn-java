# JVM Architecture & Memory Model

The **Java Virtual Machine (JVM)** is an abstract computing machine that provides a runtime environment for executing Java bytecode. It manages memory allocation, execution optimization, garbage collection, and platform independence.

---

## 🏗️ High-Level Architecture

```text
+-------------------------------------------------------------------+
|                        Class File (.class)                        |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                       CLASSLOADER SUBSYSTEM                       |
|   Bootstrap Loader  --->  Platform Loader  --->  App Loader       |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                        RUNTIME DATA AREAS                         |
|  +---------------------+  +------------------+  +--------------+  |
|  |     Metaspace       |  |   Heap Memory    |  | Stack (Thread|  |
|  |  (Class Metadata)   |  | (Objects & Data) |  |   Frames)    |  |
|  +---------------------+  +------------------+  +--------------+  |
|  +---------------------+  +------------------------------------+  |
|  | PC Registers / Thread| |      Native Method Stacks          |  |
|  +---------------------+  +------------------------------------+  |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                         EXECUTION ENGINE                          |
|  +------------------+   +-------------------+   +--------------+  |
|  |   Interpreter    |   | JIT (C1/C2) Comp. |   | Garbage Coll.|  |
|  +------------------+   +-------------------+   +--------------+  |
+-------------------------------------------------------------------+
```

---

## 1. Compilation & Execution Pipeline

1. **Source Compilation (`javac`):**
   * Human-readable `.java` code is compiled into platform-independent **Bytecode** (`.class` files).
2. **Class Loading:**
   * The JVM dynamically loads `.class` files into memory when referenced for the first time.
3. **Bytecode Verification:**
   * Ensures the loaded bytecode adheres to JVM specifications and does not violate memory access permissions or type safety.
4. **Execution:**
   * The **Interpreter** executes bytecode instruction-by-instruction.
   * As methods are executed repeatedly ("hotspots"), the **JIT (Just-In-Time) Compiler** compiles those bytecode blocks into native machine code for maximum execution performance.

---

## 2. ClassLoader Subsystem

Java uses a **Parent-Delegation Model** for loading classes into memory:

```text
  Bootstrap ClassLoader (C/C++ native code - java.base, java.lang.*)
           ▲
           │ delegates up
  Platform ClassLoader (Standard extensions & platform modules)
           ▲
           │ delegates up
  Application / System ClassLoader (Your application's classpath)
```

* **Delegation Hierarchy:** When a class is requested, the Application ClassLoader first asks its parent (Platform), which asks its parent (Bootstrap). If the parent cannot find the class, the request drops down for the child to load it.
* **Phases of Loading:**
  1. **Loading:** Reads `.class` binary bytes into memory.
  2. **Linking:**
     * *Verification:* Validates bytecode constraints.
     * *Preparation:* Allocates static fields and initializes them to default values (`0`, `null`, `false`).
     * *Resolution:* Replaces symbolic references in the constant pool with direct memory references.
  3. **Initialization:** Executes `static` initializer blocks and assigns explicit values to `static` variables.

---

## 3. Runtime Data Areas (JVM Memory Layout)

Memory inside the JVM is split into **Thread-Shared** and **Thread-Isolated** areas:

```text
       SHARED BY ALL THREADS                 THREAD-ISOLATED (PER THREAD)
+---------------------------------+        +----------------------------+
|           HEAP MEMORY           |        |        THREAD STACK        |
|  Young Gen  |     Old Gen       |        | [Frame: method2()]         |
| (Eden/S0/S1)|  (Tenured Space)  |        | [Frame: method1()]         |
+---------------------------------+        +----------------------------+
|            METASPACE            |        |   PC Register / Native     |
| (Class definitions & Metadata)  |        +----------------------------+
+---------------------------------+
```

### A. Stack Memory (Thread-Isolated)

* Every JVM thread has its own call stack, created when the thread starts.
* Composed of **Stack Frames**: Each method invocation pushes a new frame; returning from a method pops the frame.
* **Contents of a Stack Frame:**
  1. *Local Variable Array:* Holds primitives (`int`, `double`, `boolean`) and reference pointers (memory addresses pointing to objects on the Heap).
  2. *Operand Stack:* Workspace for bytecode instructions to evaluate expressions.
  3. *Frame Data:* References to the constant pool and method return information.

### B. Heap Memory (Thread-Shared)

* All Java objects and arrays created with `new` live on the Heap.
* Divided logically into generational spaces to optimize Garbage Collection:
  * **Young Generation:**
    * **Eden Space:** Where newly instantiated objects are first created.
    * **Survivor Spaces (S0 & S1):** Objects that survive Minor GC cycles are copied between S0 and S1.
  * **Old Generation (Tenured):** Long-lived objects that have survived multiple Minor GC passes are promoted here.

### C. Metaspace (Thread-Shared, Native Memory)

* Replaced `PermGen` (Permanent Generation) starting in Java 8.
* Resides in **Native Host Memory** (outside the JVM heap limit).
* Stores class metadata: runtime constant pool, field definitions, method structures, and method bytecode.
* Dynamically resizes based on available OS memory unless capped (`-XX:MaxMetaspaceSize`).

### D. PC Register & Native Method Stack

* **PC (Program Counter) Register:** Tracks the current JVM instruction address being executed per thread.
* **Native Method Stack:** Contains state for native methods written in C/C++ via JNI (Java Native Interface).

---

## 4. Execution Engine & Tiered Compilation

The JVM balances **quick application startup** with **maximum execution throughput** using Tiered Compilation.

* **Interpreter:** Low latency at startup. Interprets instructions one-by-one.
* **JIT Compiler:** High throughput long-term. Monitors "hot" methods via invocation counters and compiles them directly to native assembly.
  * **Tier 0:** Interpreter execution.
  * **Tier 1–3 (C1 Compiler / Client):** Fast compilation with light profiling/optimizations.
  * **Tier 4 (C2 Compiler / Server):** Deep optimizations (method inlining, loop unrolling, escape analysis, dead-code elimination) for maximum execution speed.

---

## 5. Garbage Collection (GC) Mechanics

Java automatically manages memory allocation and deallocation using Garbage Collection.

### A. Reachability Analysis (GC Roots)

GC starts from a set of **GC Roots** (references on Stack frames, static variables, active JNI references) and traces object graphs:

* **Reachable:** An object connected to a GC Root by at least one reference path is kept alive.
* **Unreachable (Garbage):** Objects disconnected from GC Roots are eligible for collection.

### B. The Generational Hypothesis

> *"Most objects die shortly after creation."*

Because 90%+ of objects are short-lived, JVM GC isolates the Young Generation to collect garbage rapidly without inspecting the entire Heap.

* **Minor GC:** Triggered when Eden Space is full. Scans Young Gen, moves live objects to a Survivor space or promotes them to Old Gen.
* **Major / Full GC:** Scans both Young and Old Generations. Involves longer Stop-The-World (STW) pauses.

### C. Modern Garbage Collectors

1. **G1GC (Garbage-First):** Default since JDK 9. Divides the heap into hundreds of equal-sized region blocks. Prioritizes regions with the most garbage ("Garbage First") to meet user-configured pause-time targets (`-XX:MaxGCPauseMillis`).
2. **ZGC (Z Garbage Collector):** Ultra-low latency collector introduced for production in Java 15+. Designed for large heaps (gigabytes to terabytes) with sub-millisecond pause times using colored pointers and load barriers.
