# Java 21 Data Structures & Algorithms (DSA) Guide

A comprehensive guide to Data Structures, the Java Collections Framework (including Java 21 Sequenced Collections), and essential algorithms in Java 21.

## Java Collections Framework Overview

```text
               Iterable<E>
                    │
               Collection<E>
     ┌──────────────┼──────────────┐
     │              │              │
  List<E>        Set<E>         Queue<E> / Deque<E>
  (ArrayList)   (HashSet)       (ArrayDeque, PriorityQueue)
     │              │
  SequencedCollection<E> (Java 21)
     │
  SequencedSet<E> (Java 21)
  (TreeSet, LinkedHashSet)

Map<K, V> (Separate Hierarchy)
 └── SequencedMap<K, V> (Java 21: LinkedHashMap, TreeMap)
```

## Data Structures & Collections

### 1. Dynamic Arrays (`ArrayList`)

#### ArrayList Mechanics & Complexity

Backing array that resizes dynamically (typically by 1.5x capacity when full).

* **Access by index:** $O(1)$
* **Append (add to end):** Amortized $O(1)$
* **Insertion/Deletion at index:** $O(n)$ due to element shifting

#### ArrayList Java 21 Syntax Example

```java
import java.util.ArrayList;
import java.util.List;

List<String> list = new ArrayList<>();
list.add("Java 21");
list.add("DSA");
String item = list.get(0);
```

#### ArrayList Real-World Use Cases

* Random access lookup by index.
* Storing sequential items when reads outnumber middle insertions.

#### ArrayList Related Algorithms

* **Two Pointers Technique:** (e.g., reversing an array, two-sum on sorted arrays).
* **Binary Search:** $O(\log n)$ search on sorted lists using `Collections.binarySearch()`.

---

### 2. Linked Lists (`LinkedList` & Custom Node List)

#### LinkedList Mechanics & Complexity

Doubly-linked nodes containing data, `next`, and `prev` references.

* **Prepend / Append:** $O(1)$
* **Access / Search by index:** $O(n)$ traversal
* **Insertion / Deletion with Node reference:** $O(1)$

#### LinkedList Java 21 Syntax Example

```java
import java.util.LinkedList;

LinkedList<Integer> list = new LinkedList<>();
list.addFirst(10); // SequencedCollection feature
list.addLast(20);
int first = list.getFirst();
```

#### LinkedList Real-World Use Cases

* Implementing Stacks or Deques (though `ArrayDeque` is generally preferred for performance).
* Undo/Redo buffers or LRU cache nodes.

#### LinkedList Related Algorithms

* **Fast and Slow Pointers (Floyd's Cycle Finding):** Detecting loops in linked lists or finding the middle node.
* **In-place List Reversal:** Iteratively re-linking `next` pointers in $O(n)$ time and $O(1)$ space.

---

### 3. Double-Ended Queues & Stacks (`ArrayDeque`)

#### ArrayDeque Mechanics & Complexity

Resizable circular array buffer backing a Deque (Double Ended Queue).

* **Push / Pop / Offer / Poll (Both Ends):** Amortized $O(1)$
* **Memory Efficiency:** Superior cache locality compared to `Stack` or `LinkedList`.

#### ArrayDeque Java 21 Syntax Example

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<String> stack = new ArrayDeque<>();
stack.push("Bottom");
stack.push("Top");
String popped = stack.pop();
```

#### ArrayDeque Real-World Use Cases

* Standard LIFO stack operations (replaces legacy `java.util.Stack`).
* Breadth-First Search (BFS) queues and Sliding Window Max/Min buffers.

#### ArrayDeque Related Algorithms

* **Monotonic Stack:** Finding the Next Greater Element in $O(n)$ time.
* **Breadth-First Search (BFS):** Level-order traversal of trees and unweighted graphs.

---

### 4. Priority Queues & Min/Max Heaps (`PriorityQueue`)

#### PriorityQueue Mechanics & Complexity

Complete Binary Tree stored in a contiguous array representation.

* **Peek Min/Max:** $O(1)$
* **Offer (Insert):** $O(\log n)$
* **Poll (Extract Min/Max):** $O(\log n)$

#### PriorityQueue Java 21 Syntax Example

```java
import java.util.Comparator;
import java.util.PriorityQueue;

// Min-Heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-Heap with custom comparator
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
maxHeap.offer(15);
maxHeap.offer(42);
int highest = maxHeap.poll();
```

#### PriorityQueue Real-World Use Cases

* Task scheduling based on priority.
* Top-K elements problems.
* Pathfinding algorithms (Dijkstra, A*).

#### PriorityQueue Related Algorithms

* **Dijkstra's Shortest Path Algorithm:** Greedy graph traversal finding shortest paths.
* **K-Way Merge / Top-K Elements:** Keeping a min-heap of size $K$ to process streaming data.

---

### 5. Hash Tables (`HashSet` & `HashMap`)

#### HashMap Mechanics & Complexity

Array of buckets using hash codes. In Java, high collision buckets convert from linked lists to Red-Black Trees (treeification when bucket size $\ge 8$).

* **Put / Get / Remove / Contains:** Amortized $O(1)$
* **Space Complexity:** $O(n)$

#### HashMap Java 21 Syntax Example

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> map = new HashMap<>();
map.put("Alice", 90);
map.put("Bob", 85);
int score = map.getOrDefault("Alice", 0);
```

#### HashMap Real-World Use Cases

* Fast lookup tables, frequency counters, and caching.
* Graph adjacency lists.

#### HashMap Related Algorithms

* **Hash Map Frequency Counting:** $O(n)$ counting of array frequencies.
* **Two-Sum Algorithm:** $O(n)$ lookup using complement values.

---

### 6. Balanced Binary Search Trees (`TreeSet` & `TreeMap`)

#### TreeSet Mechanics & Complexity

Self-balancing Red-Black Tree implementation maintaining sorted order.

* **Insert / Delete / Search:** $O(\log n)$
* **Sorted Operations (`first()`, `last()`, `subMap()`):** $O(\log n)$

#### TreeSet Java 21 Syntax Example

```java
import java.util.TreeSet;

TreeSet<Integer> set = new TreeSet<>();
set.add(40);
set.add(10);
set.add(25);

int lowest = set.first(); // 10
int highest = set.last(); // 40
```

#### TreeSet Real-World Use Cases

* Dynamic sorted data sets requiring range queries or predecessor/successor lookups.

#### TreeSet Related Algorithms

* **In-order Tree Traversal:** Extracting sorted elements in $O(n)$ time.
* **Range Searching:** Finding all elements within `[min, max]`.

---

### 7. Sequenced Collections (Java 21 Feature)

#### Sequenced Collections Mechanics & Complexity

Introduced in **JDK 21** (`SequencedCollection`, `SequencedSet`, `SequencedMap`) to provide unified APIs for collections with defined encounter orders.

* Methods: `addFirst()`, `addLast()`, `getFirst()`, `getLast()`, `removeFirst()`, `removeLast()`, `reversed()`.

#### Sequenced Collections Java 21 Syntax Example

```java
import java.util.LinkedHashSet;
import java.util.SequencedSet;

SequencedSet<String> set = new LinkedHashSet<>();
set.addLast("First Added");
set.addLast("Second Added");

String first = set.getFirst(); // "First Added"
String last = set.getLast();   // "Second Added"
```

#### Sequenced Collections Real-World Use Cases

* Maintaining insertion order while performing $O(1)$ operations on first/last elements.
* LRU Caches with `LinkedHashMap`.

---

## Essential Algorithms Cheat Sheet

| Algorithm Category | Algorithm Name | Time Complexity | Primary Data Structure | Common Use Case |
| --- | --- | --- | --- | --- |
| **Searching** | Binary Search | $O(\log n)$ | Sorted `ArrayList` / Array | Searching target element in sorted data |
| **Sorting** | QuickSort / Dual-Pivot | $O(n \log n)$ | Array / `Arrays.sort()` | General-purpose primitive sorting |
| **Sorting** | Timsort | $O(n \log n)$ | `ArrayList` / `Collections.sort()` | Stable reference object sorting |
| **Graph Traversal** | Breadth-First Search (BFS) | $O(V + E)$ | `Queue` (`ArrayDeque`) | Shortest path in unweighted graphs |
| **Graph Traversal** | Depth-First Search (DFS) | $O(V + E)$ | Stack / Recursion | Pathfinding, cycle detection, topological sort |
| **Shortest Path** | Dijkstra's Algorithm | $O((V + E) \log V)$ | `PriorityQueue` + `Map` | Shortest path in weighted non-negative graphs |
