import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SequencedSet;

public class Main {
    public static void main(String[] args) {
        // EXERCISE 1: Sequenced Collections (Java 21)
        // 1. Create a SequencedSet using LinkedHashSet<String>.
        // 2. Add elements using addLast() and addFirst().
        // 3. Print the first element, last element, and the reversed set view.

        SequencedSet<Byte> mySet = new LinkedHashSet<>();
        mySet.addFirst((byte) 12);
        mySet.addLast((byte) 23);

        System.out.println("First element: " + mySet.getFirst());
        System.out.println("Last element: " + mySet.getLast());

        // EXERCISE 2: Priority Queue (Max-Heap)
        // 1. Create a PriorityQueue<Integer> configured as a Max-Heap using
        // Comparator.reverseOrder().
        // 2. Add numbers 15, 42, 8, 99, 23.
        // 3. Poll and print all elements until the heap is empty to verify descending
        // order output.

        PriorityQueue<Integer> myPQueue = new PriorityQueue<>();
        myPQueue.offer(15);
        myPQueue.offer(42);
        myPQueue.offer(8);
        myPQueue.offer(99);

        System.out.printf("%nReading all elements in queue until empty: %n");
        while (!myPQueue.isEmpty()) {

            var element = myPQueue.poll();
            System.out.printf("Current last element: %d%n", element);
        }

        // var expectedOutput = """
        // Reading all elements in queue until empty:
        // Current last element: 8
        // Current last element: 15
        // Current last element: 42
        // Current last element: 99
        // """;

        // EXERCISE 3: Map Frequency Counter
        // 1. Create a Map<String, Integer> to count word frequencies from a list of
        // words.
        // 2. Use map.getOrDefault() or map.merge() to update word counts.

        List<String> words = List.of(
                "java", "python", "java", "code", "java",
                "python", "data", "structures", "code", "java");

        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {

            if (frequencyMap.containsKey(word)) {
                Integer currentValue = frequencyMap.get(word);
                frequencyMap.put(word, currentValue + 1);
            } else {
                frequencyMap.put(word, 1);
            }
        }

        System.out.printf("%nAll words in Map");
        frequencyMap.forEach((key, value) -> {
            System.out.printf("Key: %s - Value %d%n", key, value);
        });
    }
}
