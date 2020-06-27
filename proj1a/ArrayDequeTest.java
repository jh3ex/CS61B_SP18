/** Performs some basic linked list tests. */
public class ArrayDequeTest {
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        ArrayDeque x = new ArrayDeque<Integer>();
        for (int i = 0; i < 10000; i++) {
            x.addFirst(i);
        }
        System.out.println(x.size());
        System.out.println(x.items.length);

        for (int i = 0; i < 9999; i++) {
            x.removeLast();
        }

        System.out.println(x.size());
        System.out.println(x.items.length);

        x.printDeque();

    }
}

