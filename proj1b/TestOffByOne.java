import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars( 'a', 'b'));
        assertFalse(offByOne.equalChars('c', 'g'));
        assertTrue(offByOne.equalChars('c', 'b'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertFalse(offByOne.equalChars('%', ')'));
    }
}
