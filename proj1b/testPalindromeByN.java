import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindromeByN {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testIsPalindrome() {
        OffByN offBy5 = new OffByN(5);
        boolean res1 = palindrome.isPalindrome("abtgf", offBy5);
        assertTrue(res1);
        boolean res2 = palindrome.isPalindrome("mike", offBy5);
        assertFalse(res2);
    }
}

