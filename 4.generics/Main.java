
public class Main {
    public static void main(String[] args) {

        Box<String> stringBox = new Box<>();

        stringBox.set("New Box Item");

        System.out.println("Box content: " + stringBox.get());

        Box<Integer> integerBox = new Box<>();
        integerBox.set(42);
        System.out.println("IntegerBox content: " + integerBox.get());

    }
}
