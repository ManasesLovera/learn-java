public class Main {
    // Custom Exception Class exercise:
    // Create a custom unchecked exception named 'InvalidAgeException' that extends RuntimeException.

    public static void main(String[] args) {
        // EXERCISE 1: Custom Exception & Throwing
        // 1. Create a method validateAge(int age) that throws InvalidAgeException if age < 18.
        // 2. Call validateAge with invalid input inside a try-catch block and print the error message.

        // EXERCISE 2: Try-With-Resources & AutoCloseable
        // 1. Create a custom resource class 'DatabaseConnection' implementing AutoCloseable.
        // 2. Implement the close() method to print "Connection closed automatically."
        // 3. Use DatabaseConnection in a try-with-resources statement in main().
    }
}
