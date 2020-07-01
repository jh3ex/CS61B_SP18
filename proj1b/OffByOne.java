/** Task 4.1 */

public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        // Cast x, y to int and get the difference
        int diff = Math.abs(x - y);
        // Return true if the difference is exactly 1
        return diff == 1;
    }

    private static void main(String[] args) {
        // Actually, we didn't define a constructor
        OffByOne obo = new OffByOne();
        System.out.println(obo.equalChars('a', 'b'));
        System.out.println(obo.equalChars('z', 'y'));
    }
}

