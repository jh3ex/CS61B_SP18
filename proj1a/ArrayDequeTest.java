/** Performs some basic linked list tests. */
public class ArrayDequeTest {
	public static void main(String[] args) {
		System.out.println("Running tests.\n");
        ArrayDeque x = new ArrayDeque<Integer>();

        x.addLast(0);
        x.printDeque();
        x.addFirst(1);
        x.printDeque();
        x.addFirst(2);
        x.printDeque();
        System.out.println(x.removeFirst());
        System.out.println(x.get(1));

	}
}

