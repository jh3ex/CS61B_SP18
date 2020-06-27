/** Performs some basic linked list tests. */
public class ArrayDequeTest {
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        ArrayDeque x = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            x.addFirst(i);
        }
        System.out.println(x.size());

        for (int i = 0; i < 59; i ++) {
            x.removeLast();
        }
        System.out.println(x.size());
        System.out.println(x.size());

        for (int i = 0; i < 10; i++) {
            x.addLast(i);
        }
        x.printDeque();

    }
}

