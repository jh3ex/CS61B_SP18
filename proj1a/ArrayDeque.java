/** Class for double-ended queue using array.
 * Rules: 1. Add and remove must take constant time,
 *           except during resizing operation;
 *        2. Get and size must take constant time.
 *        3. The starting size of array must be 8;
 *        4. For arrays of length 16 or more, usage
 *           factor should always be at least 25%.
 * */

public class ArrayDeque<T> {
    private int nextFirst;
    private int nextLast;
    private int first;
    private int last;
    private T[] items;
    private int size;
    // Class parameters


    public ArrayDeque(T item) {
        items = (T[])new Object[8];
        items[0] = item;
        size = 1;
        nextLast = 1;
        nextFirst = 8 - 1;
        first = 0;
        last = 0;
    }

    public ArrayDeque() {
        // Construct a empty queue
        items = (T[])new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
        first = 0;
        last = 0;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        first = nextFirst;
        nextFirst--;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        expandSize();
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        last = nextLast;
        nextLast++;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        expandSize();
    }

    private void expandSize() {
        // Expand the array whenever necessary
        // 1. Inspect the usage ratio
        // 2. If ration >= 50%, expand two times
        // 3. create a new items and copy to it
        double ratio = size / (double) items.length;
        if (ratio > 0.5) {
            T[] newItems = (T[])new Object[size * 2];
            // Examine if the loop is divided
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
            first = 0;
            nextFirst = items.length - 1;
            last = size - 1;
            nextLast = size;
        }

    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[first];
        size--;
        nextFirst = first;
        first++;
        if (first == items.length) {
            first = 0;
        }
        reduceSize();
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[last];
        size--;
        nextLast = last;
        last--;
        if (last == -1) {
            last = items.length - 1;
        }
        reduceSize();
        return removed;
    }

    private void reduceSize() {
        // If the usage falls below 0.25 when array is longer than 16
        // reduce the size by half;
        if (items.length <= 16) {
            return;
        }
        double ratio = size / (double) items.length;
        if (ratio < 0.25) {
            // ratio < 0.25 ensures that new item can contain the previous item
            int newLength = Math.max(items.length / 2, 16);
            T[] newItems = (T[])new Object[newLength];
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
            first = 0;
            nextFirst = items.length - 1;
            last = size - 1;
            nextLast = size;
        }
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index > size) {
            return null;
        }
        int indexInArray = first + index;
        if (indexInArray >= items.length) {
            indexInArray = indexInArray - items.length;
        }
        return items[indexInArray];
    }




    public void printDeque() {
        if (isEmpty()) {
            return;
        }

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



    private static void main(String[] args) {
        ArrayDeque x = new ArrayDeque<Integer>(0);
        for (int i = 0; i <= 10; i++) {
            x.addLast(i);
        }

        x.printDeque();
        System.out.println(x.get(0));
    }
}

