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



        // How 'var' works (Local Variable Type Inference)

        // var is NOT dynamic typing (not like JS or Python)
        // - Statically Typed at Compile Time
        // - Immutability of Type

        var count = 10; // Compiler infers type to INT
        count = 20; // valid, 20 is INT

        // count = "Hello";
        // COMPILE ERROR: incompatible types: java.lang.String cannot be converted to int

        // Rules for var: Can only be used for local variables with initializers. 
        // It cannot be used for class fields, method parameters, or return types.



        // Casting and Type Convertions

        // Implicit convertions (Widening / Automatic)
        int myOtherInt = 100;
        long myOtherLong = myOtherInt; // Automatic: int -> long
        double myOtherDouble = myOtherLong; // Automatic: long -> double

        // Explicit convertions (Narrowing / Manual)
        // Required when converting a larger to smaller type.
        int hugeNumber = 130;
        // byte smallByte = (byte) hugeNumber; // Overflow! byte range is -128 to 127 -> result is -126

        // Parsing strings to primitives
        String ageStr = "25";
        int parsedAge = Integer.parseInt(ageStr);

        String priceStr = "49.99";
        double parsedPrice = Double.parseDouble(priceStr);

        String flagStr = "true";
        boolean parsedFlag = Boolean.parseBoolean(flagStr);
    }
}
