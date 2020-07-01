/** Class for double-ended queue using array.
 * Rules: 1. Add and remove must take constant time,
 *           except during resizing operation;
 *        2. Get and size must take constant time.
 *        3. The starting size of array must be 8;
 *        4. For arrays of length 16 or more, usage
 *           factor should always be at least 25%.
 * */

public class ArrayDeque<T> implements Deque<T> {
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int size;

    public ArrayDeque() {
        // Construct a empty queue
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        nextFirst = oneMinusPlus(nextFirst, -1);
        reSize();
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        nextLast = oneMinusPlus(nextLast, 1);
        reSize();
    }

    private int oneMinusPlus(int x, int y) {
        int z = x + y;
        if (z < 0) {
            z = items.length - 1;
        } else if (z >= items.length) {
            z = 0;
        }
        return z;
    }

    private void reSize() {
        double ratio = size / (double) items.length;
        if (ratio > 0.75) {
            // Expand size
            migrate(size * 2);
        } else if (ratio < 0.25) {
            // Reduce size
            if (items.length <= 16) {
                return;
            }
            migrate(Math.max(16, items.length / 2));
        }
    }

    private void migrate(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        // Examine if the loop is divided
        int first = oneMinusPlus(nextFirst, 1);
        int last = oneMinusPlus(nextLast, -1);
        if (first <= last) {
            System.arraycopy(items, first, newItems, 0, size);
        } else {
            // If the loop is divided
            // 2. Copy the first half, from first ~ length-1, total length-nextFirst
            // 2. Copy the second half, from 0 ~ next
            int firstHalfLength = items.length - first;
            int secondHalfLength = last + 1;
            System.arraycopy(items, first, newItems, 0, firstHalfLength);
            System.arraycopy(items, 0, newItems, firstHalfLength, secondHalfLength);
        }
        // Discard the original array
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int first = oneMinusPlus(nextFirst, 1);
        T removed = items[first];
        size--;
        nextFirst = first;
        reSize();
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = oneMinusPlus(nextLast, -1);
        T removed = items[last];
        size--;
        nextLast = last;
        reSize();
        return removed;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index > size) {
            return null;
        }
        int first = oneMinusPlus(nextFirst, 1);
        int indexInArray = first + index;
        if (indexInArray >= items.length) {
            indexInArray = indexInArray - items.length;
        }
        return items[indexInArray];
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int first = oneMinusPlus(nextFirst, 1);
        int last = oneMinusPlus(nextLast, -1);
        if (first <= last) {
            print(first, last);
        } else {
            print(first, items.length - 1);
            print(0, last);
        }
        System.out.print('\n');
    }

    private void print(int printFirst, int printLast) {
        for (int index = printFirst; index <= printLast; index++) {
            System.out.print(items[index]);
            System.out.print(' ');
        }
    }
}

