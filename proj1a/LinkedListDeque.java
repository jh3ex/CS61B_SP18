/** Create double-ended queue via array as requested.
 *
 */

public class LinkedListDeque<T> {
    private int size;
    private ListNode sentinel;

    private class ListNode {
        /** Nested class. */
        private ListNode prev;
        private ListNode next;
        private T item;
        private ListNode(T item0, ListNode prev0, ListNode next0) {
            item = item0;
            prev = prev0;
            next = next0;
        }
    }

    public LinkedListDeque(T item0) {
        // Constructor function
        // 1. create a sentinel node
        // 2. link sentinel node to first actual node
        // 3. update size to 1
        sentinel = new ListNode(null, null, null);
        sentinel.next = new ListNode(item0, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }



    public void addFirst(T item) {
        // Add a new item to the first position of the queue
        // 1. instantiate a new ListNode with provided item
        // 2. insert the new node in between sentinel and first node
        //   2.1 next points to first node (sentinel.next), new node
        //       prev points to sentinel
        //   2.2 first node prev point to new node
        //   2.3 sentinel next point to new node
        //   2.4 increment the size
        ListNode newNode = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        // Add a new item to the last position of the queue
        // 1. instantiate a new ListNode with provided item
        // 2. similar to addFirst
        ListNode newNode = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
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

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return removed;
    }

    public T get(int index) {
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

    public T getRecursive(int index) {
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
    private T getRecursiveForward(int index, ListNode L) {
        if (index == 0) {
            return L.item;
        }
        return getRecursiveForward(index - 1, L.next);
    }

    private T getRecursiveBackward(int index, ListNode L) {
        if (index == 0) {
            return L.item;
        }
        return getRecursiveBackward(index - 1, L.prev);
    }

    private static void main(String[] args) {
        LinkedListDeque x = new LinkedListDeque<Integer>(3);
        x.addFirst(9);
        x.addFirst(6);
        x.addFirst(9);
        x.printDeque();
        System.out.println(x.getRecursive(3));
    }
}

