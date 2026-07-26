import models.Person;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hola mundo en java");

        ArrayList<Person> people = new ArrayList<>();

        people.add(new Person("Manases", 22));
        people.add(new Person("Ashley", 22));

        for (int i = 0; i < people.size(); i++) {

            System.out.printf("Person: %d%n", i);
            System.out.println(people.get(i).getGreeting());

        }

    }
}
