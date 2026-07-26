public class Main {
    public static void main(String[] args) {
        // EXERCISE 1: Launching Virtual Threads (Java 21)
        // 1. Create and start a Virtual Thread using Thread.ofVirtual().start(...).
        // 2. Print Thread.currentThread() inside the task to inspect its virtual thread name/carrier.
        // 3. Call thread.join() to wait for completion.

        // EXERCISE 2: High Throughput Virtual Thread Executor
        // 1. Use Executors.newVirtualThreadPerTaskExecutor() in a try-with-resources statement.
        // 2. Submit 1,000 tasks that perform a short Thread.sleep(50) (simulating blocking I/O).
        // 3. Measure total execution time to observe the massive throughput of Virtual Threads!
    }
}
