public class Main {
    // Custom Exception Class exercise:
    // Create a custom unchecked exception named 'InvalidAgeException' that extends
    // RuntimeException.
    public static class InvalidAgeException extends RuntimeException {

        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static boolean validateAge(int age) throws InvalidAgeException {

        if (age >= 18) {
            return true;
        } else {
            throw new InvalidAgeException("Invalid age");
        }
    }

    public static void main(String[] args) {
        // EXERCISE: Custom Exception & Throwing
        // 1. Create a method validateAge(int age) that throws InvalidAgeException if
        // age < 18.
        // 2. Call validateAge with invalid input inside a try-catch block and print the
        // error message.

        try {
            validateAge(6);
        } catch (InvalidAgeException e) {
            System.out.println("There was an error: " + e.getMessage());
        }
    }
}
