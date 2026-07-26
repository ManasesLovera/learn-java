# Java Generics: Concepts, Mechanics & Best Practices

Generics allow types (classes, interfaces, and methods) to be parameterized. They bring compile-time type safety and eliminate manual type casting.

## Why Generics Matter

Prior to Java 5, collections stored raw `Object` references, requiring manual casting and risking `ClassCastException` at runtime.

* **Compile-Time Type Checking:** Detects type mismatches during compilation rather than failing at runtime.
* **Elimination of Casts:** Reduces boilerplate and improves code readability.
* **Code Reusability:** Enables writing generic algorithms and data structures that work across different reference types.

## Core Syntax & Patterns

### 1. Generic Classes

A generic class declares one or more type parameters in angle brackets (`<T>`).

```java
public class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
```

### 2. Generic Methods

A generic method introduces its own type parameter before the return type.

```java
public class Utility {
    public static <T> void printElement(T element) {
        System.out.println("Element: " + element);
    }
}
```

### 3. Bounded Type Parameters

You can restrict the types that can be passed as type arguments using `extends`.

```java
// T must be Number or a subclass of Number (e.g., Integer, Double)
public class NumericBox<T extends Number> {
    private T number;

    public double doubleValue() {
        return number.doubleValue();
    }
}
```

### 4. Wildcards (`?`) & The PECS Principle

Wildcards represent an unknown type in Java generics.

* **Upper Bounded Wildcard (`? extends T`):** Accepts `T` or any subtype of `T`. Useful when reading data (**Producer Extends**).
* **Lower Bounded Wildcard (`? super T`):** Accepts `T` or any supertype of `T`. Useful when writing data (**Consumer Super**).
* **Unbounded Wildcard (`?`):** Accepts any type. Useful when only `Object` methods or type-agnostic operations are needed.

```java
// Read-only (Producer): Can read elements as Number
public static double sumOfList(List<? extends Number> list) {
    double sum = 0.0;
    for (Number n : list) {
        sum += n.doubleValue();
    }
    return sum;
}

// Write-only (Consumer): Can add Integers safely
public static void addNumbers(List<? super Integer> list) {
    list.add(10);
    list.add(20);
}
```

## JVM Mechanics: Type Erasure

Java generics are implemented via **Type Erasure** to maintain backward compatibility with older Java versions:

1. The compiler replaces type parameters with their bounds (or `Object` if unbounded).
2. The compiler inserts synthetic type casts where necessary.
3. Type metadata is erased from compiled bytecode (`.class` files).

### Consequences of Type Erasure

* **No Primitives:** Generic type parameters cannot be primitive types (`List<int>` is invalid; use `List<Integer>`).
* **No `new T()`:** You cannot directly instantiate generic types (`new T()`) because `T` is unknown at runtime.
* **No Generic Arrays:** Cannot create generic arrays directly (`new T[10]` or `new List<String>[10]`).
* **No `instanceof T`:** Cannot check runtime type parameter (`obj instanceof T` is invalid).

## Java Generics Best Practices

* **Avoid Raw Types:** Always specify generic parameters (e.g., use `List<String>` instead of raw `List`).
* **Follow Naming Conventions:**
  * `T`: General Type
  * `E`: Element (Collections)
  * `K`: Key
  * `V`: Value
  * `N`: Number
* **Use Diamond Operator (`<>`):** Let the compiler infer type arguments during instantiation (`List<String> list = new ArrayList<>();`).
* **Apply PECS:** Remember **P**roducer **E**xtends, **C**onsumer **S**uper when designing API parameters with wildcards.
