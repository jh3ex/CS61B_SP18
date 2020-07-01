/** Project 1b Task 2. */


public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        // Build a new array
        Deque<Character> wordArray = new ArrayDeque<Character>();
        // Loop over the word and get each char
        for (int i = 0; i < word.length(); i += 1) {
            wordArray.addLast(word.charAt(i));
        }
        return wordArray;
    }

    /** Determines if a word is palindrome. */
    public boolean isPalindrome(String word) {
        // 1. Convert the word to array
        // 2. Use a helper to implement recursion
        //    Observation: Front = Last
        Deque<Character> wordArray = wordToDeque(word);
        return isPalindrome(wordArray);
    }

    private boolean isPalindrome(Deque<Character> word) {
        // Use removeFirst and removeLast
        // and compare the removed items
        if (word.size() <= 1) {
            return true;
        }
        char first = word.removeFirst();
        char last = word.removeLast();
        if (first != last) {
            return false;
        }
        return isPalindrome(word);
    }

    /** Task 3:
     *      return true if the word is a palindrome according
     *      to the character comparison test provided by the
     *      CharacterComparator passed in as argument cc
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordArray = wordToDeque(word);
                return isOboPalindrome(wordArray, cc);
    }

    private boolean isOboPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        }
        char first = word.removeFirst();
        char last = word.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return isOboPalindrome(word, cc);
    }
}

