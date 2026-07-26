package models;

public class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Public method accessible outside the package
    public String getGreeting() {

        return String.format("Hello, I am %s and I am %d years old.", name, age);
    }
}
