package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class arrayIterator implements Iterator<T> {
        private int ptr;
        private int index;

        public arrayIterator() {
            ptr = 0;
            index = first;
        }

        public boolean hasNext() {
            return ptr < fillCount;
        }

        public T next() {
            T nextItem = rb[index];
            ptr += 1;
            index = incremental(index);
            return nextItem;
        }
    }


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    @Override
    public Iterator<T> iterator() {
        return new arrayIterator();
    }

    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        last = incremental(last);
        fillCount += 1;
    }

    private int incremental(int index) {
        index += 1;
        if (index == capacity) {
            return 0;
        }
        return index;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T removedItem = rb[first];
        first = incremental(first);
        fillCount -= 1;
        return removedItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return rb[first];
    }

}
