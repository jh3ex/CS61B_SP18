/** Interface for double-ended queue lists. */

public interface Deque<T> {
    /** Add item to the front of the queue. */
    public void addFirst(T item);
    /** Add item to the end of the queue. */
    public void addLast(T item);
    /** Remove one item from the front
     * and return removed item. */
    public T removeFirst();
    /** Remove one item from the front
     * and return removed item. */
    public T removeLast();
    /** Return True if the array is empty. */
    public boolean isEmpty();
    /** Return the size of the array. */
    public int size();
    /** Get the ith item. */
    public T get(int index);
    /** Print the whole array. */
    public void printDeque();
}

