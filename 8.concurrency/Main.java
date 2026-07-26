public class Main {
    public static void main(String[] args) {
        // EXERCISE 1: Thread Synchronization & Shared Counter
        // 1. Create a SharedCounter class with a private int count field.
        // 2. Add synchronized increment() and getCount() methods.
        // 3. Spawn 2 threads that each increment the counter 1,000 times and verify the final count is 2,000.

        // EXERCISE 2: ExecutorService Thread Pool
        // 1. Create a fixed thread pool with 3 threads using Executors.newFixedThreadPool(3).
        // 2. Submit 5 tasks that print the current thread name and task ID.
        // 3. Ensure the executor is properly closed.

        // EXERCISE 3: CompletableFuture Chaining
        // 1. Use CompletableFuture.supplyAsync() to return a greeting String.
        // 2. Chain .thenApply() to convert the string to uppercase.
        // 3. Use .join() or .thenAccept() to print the final result.
    }
}
