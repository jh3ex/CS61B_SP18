/** Create double-ended queue via array as requested.
 *
 */

public class LinkedListDeque<Duck> {
    private int size;
    public ListNode sentinel;
    public ListNode prev;
    public ListNode next;

    class ListNode {
        /** Nested class. */
        public ListNode prev;
        public ListNode next;
        public Duck item;
        public ListNode(Duck item0, ListNode prev0, ListNode next0) {
            item = item0;
            prev = prev0;
            next = next0;
        }
    }

    public LinkedListDeque(Duck item0) {
        // Constructor function
        // 1. create a sentinel node
        // 2. link sentinel node to first actual node
        // 3. update size to 1
        sentinel = new ListNode(null, null, null);
        sentinel.next = new ListNode(item0, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public LinkedListDeque() {
        // Construct a null LinkedListDeque
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(Duck item) {
        // Add a new item to the first position of the queue
        // 1. instantiate a new ListNode with provided item
        // 2. insert the new node in between sentinel and first node
        //   2.1 next points to first node (sentinel.next), new node
        //       prev points to sentinel
        //   2.2 first node prev point to new node
        //   2.3 sentinel next point to new node
        //   2.4 increment the size
        ListNode new_node = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = new_node;
        sentinel.next = new_node;
        size++;
    }

    public void addLast(Duck item) {
        // Add a new item to the last position of the queue
        // 1. instantiate a new ListNode with provided item
        // 2. similar to addFirst
        ListNode new_node = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = new_node;
        sentinel.prev = new_node;
        size++;
    }

    public boolean isEmpty() {
        // empty means size is zero
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode L = sentinel.next;
        while (L != sentinel) {
            System.out.print(L.item);
            System.out.print(' ');
            L = L.next;
        }
    }

    public Duck removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Duck removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return removed;
    }

    public Duck removeLast() {
        if (isEmpty()) {
            return null;
        }
        Duck removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return removed;
    }

    public Duck get(int index) {
        // get the index_th item in the queue
        // rule: must use iteration, not recursion
        // 1. copy sentinel to L
        // 2. loop over L to the index_th item
        if (isEmpty()) {
            return null;
        }
        if (index > size - 1) {
            return null;
        }
        ListNode L = sentinel;
        // Loop can  start from either the front or back
        if (index < size / 2) {
            // starting from the start is more efficient
            for (int i = 0; i <= index; i++) {
                L = L.next;
            }
        } else {
            index = size - index - 1;
            for (int i = 0; i <= index; i++) {
                L = L.prev;
            }
        }
        return L.item;
    }

    public Duck getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index > size - 1) {
            return null;
        }
        // should have a helper, since this function only takes one input
        ListNode L = sentinel;
        if (index < size / 2) {
            // starting from the start is more efficient
            return getRecursiveForward(index, L.next);
        } else {
            index = size - index - 1;
            return getRecursiveBackward(index, L.prev);
        }
    }

    /** Helper functions. */
    private Duck getRecursiveForward(int index, ListNode L) {
        if (index == 0) {
            return L.item;
        }
        return getRecursiveForward(index - 1, L.next);
    }

    private Duck getRecursiveBackward(int index, ListNode L) {
        if (index == 0) {
            return L.item;
        }
        return getRecursiveBackward(index - 1, L.prev);
    }

    public static void main(String[] args) {
        LinkedListDeque x = new LinkedListDeque<Integer>(3);
        x.addFirst(9);
        x.addFirst(6);
        x.addFirst(9);
        x.printDeque();
        System.out.println(x.getRecursive(3));

    }

}