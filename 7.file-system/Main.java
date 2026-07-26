public class Main {
    public static void main(String[] args) {

        // EXERCISE 1: Path & Directory Operations
        // 1. Use Path.of("sandbox", "test.txt") to create a relative Path object.
        // 2. Use Files.createDirectories() to ensure parent directory 'sandbox' exists.

        // EXERCISE 2: Reading & Writing Files with NIO.2
        // 1. Use Files.writeString() to write a multiline String to 'sandbox/test.txt'.
        // 2. Use Files.readString() to read the file contents back into a String and
        // print it.

        // EXERCISE 3: Stream Processing Lines with Try-With-Resources
        // 1. Use Files.lines(path) inside a try-with-resources block.
        // 2. Filter lines that contain a specific keyword and print them to stdout.
    }
}
