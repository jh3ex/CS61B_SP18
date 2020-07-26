package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayRingBufferTest {

    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> rb = new ArrayRingBuffer<Integer>(20);
        for (int i = 0; i < 13; i++) {
            rb.enqueue(i);
        }
        int fillCount = rb.fillCount();
        int firstItem = rb.peek();
        assertEquals(13, fillCount);
        assertEquals(0, firstItem);

    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> rb = new ArrayRingBuffer<>(20);
        for (int i = 0; i < 20; i++) {
            rb.enqueue(i);
        }
        for (int i = 0; i < 5; i++) {
            rb.dequeue();
        }

        int fillCount = rb.fillCount();
        assertEquals(15, fillCount);

    }

}
