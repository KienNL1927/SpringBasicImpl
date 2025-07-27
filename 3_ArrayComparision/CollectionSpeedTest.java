import java.util.*;

/*
    Output:
    Array add: 1 ms
    Array access: 0 ms
    ArrayList add: 68 ms
    ArrayList access: 1 ms
    LinkedList add: 11 ms
    LinkedList access: 5395 ms
 */

public class CollectionSpeedTest {
    private static final int SIZE = 100000;

    public static void main(String[] args) {
        testArray();
        testArrayList();
        testLinkedList();
    }

    public static void testArray() {
        int[] arr = new int[SIZE];
        long start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = i;
        }
        long end = System.nanoTime();
        System.out.println("Array add: " + (end - start) / 1_000_000 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            int x = arr[i];
        }
        end = System.nanoTime();
        System.out.println("Array access: " + (end - start) / 1_000_000 + " ms");
    }

    public static void testArrayList() {
        List<Integer> list = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        long end = System.nanoTime();
        System.out.println("ArrayList add: " + (end - start) / 1_000_000 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            int x = list.get(i);
        }
        end = System.nanoTime();
        System.out.println("ArrayList access: " + (end - start) / 1_000_000 + " ms");
    }

    public static void testLinkedList() {
        List<Integer> list = new LinkedList<>();
        long start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        long end = System.nanoTime();
        System.out.println("LinkedList add: " + (end - start) / 1_000_000 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            int x = list.get(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedList access: " + (end - start) / 1_000_000 + " ms");
    }
}
