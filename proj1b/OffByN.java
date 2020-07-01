/** Task 5 */

public class OffByN implements CharacterComparator {
    public int N;

    public OffByN(int N0) {
        N = N0;
    }

    @Override
    public boolean equalChars(char x, char y) {
        // Cast x, y to int and get the difference
        int diff = Math.abs(x - y);
        // Return true if the difference is exactly 1
        return diff == N;
    }

    private static void main(String[] args) {
        OffByN offBy5 = new OffByN(5);
        System.out.println(offBy5.equalChars('a', 'f'));
        System.out.println(offBy5.equalChars('f', 'h'));
    }


}

