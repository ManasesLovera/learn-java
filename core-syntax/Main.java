public class Main {

    public static void main(String args[]) {

        // print in console using java
        System.out.println("Hello Java 21 from Ubuntu");

        // Primitive Types
        int age = 22;
        double salary = 3_500.50;
        boolean isActive = true;
        char grade = 'A';

        // Reference Types (String)
        String name = "Manasés";

        // Modern Type Inference (var)
        var language = "Java 21";

        // Formatted Output
        System.out.printf("Developer %s | Language %s%n", name, language);
        System.out.println("Status Active: " + isActive);


        // 1. All Primitives Data Types  & Memory Sizes

        // Type: byte - size 1 byte (8 bits) - range/value -128 to 127
        byte myByte = 120;

        // Type: short - 2 bytes (16 bits) - range/value -32,768 to 32,767
        short myShort = -20_000;

        // Type: int - 4 bytes (32 bits) - range/value -2^31 to 2^31 -1 or ~2.1B 
        int myInt = 2_000_000_000;

        // Type: long 8 bytes (64 bits) - range/value -2^64 to 2^64 -1
        long myLong = 2;

        // Type: float 4 bytes (32 bits) - single precision floating point, needs suffix f
        float myFloat = 3.14f;

        // Type double 8 bytes (64 bits) - double precision floating point.
        double myDouble = 2_100.345689;

        // Type: Char 2 bytes (16 bits) - Single 16 bit unicode character ('\u0000') to ('\uffff')
        char myChar = 'a';

        // Type: boolean 1 byte (JVM dependent) true/false
        boolean myBolean = false;



        
    }
}
