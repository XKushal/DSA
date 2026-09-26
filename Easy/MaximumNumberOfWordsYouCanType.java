class MaximumNumberOfWordsYouCanType {
    public int bruteForceCanBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        int typeable = 0;

        for (String word : words) {
            boolean canType = true;

            for (int i = 0; i < word.length() && canType; i++) {
                for (int j = 0; j < brokenLetters.length(); j++) {
                    if (word.charAt(i) == brokenLetters.charAt(j)) {
                        canType = false;
                        break;
                    }
                }
            }

            if (canType) {
                typeable++;
            }
        }

        return typeable;
    }

    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];

        for (int i = 0; i < brokenLetters.length(); i++) {
            broken[brokenLetters.charAt(i) - 'a'] = true;
        }

        int typeable = 0;
        boolean canTypeCurrentWord = true;

        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ' ') {
                if (canTypeCurrentWord) {
                    typeable++;
                }

                canTypeCurrentWord = true;
            } else if (broken[text.charAt(i) - 'a']) {
                canTypeCurrentWord = false;
            }
        }

        return typeable;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumNumberOfWordsYouCanType solution = new MaximumNumberOfWordsYouCanType();

        check("brute force sample one", solution.bruteForceCanBeTypedWords("hello world", "ad"), 1);
        check("brute force sample two", solution.bruteForceCanBeTypedWords("leet code", "lt"), 1);

        check("sample one", solution.canBeTypedWords("hello world", "ad"), 1);
        check("sample two", solution.canBeTypedWords("leet code", "lt"), 1);
        check("sample three", solution.canBeTypedWords("leet code", "e"), 0);
        check("no broken letters", solution.canBeTypedWords("a b cdef", ""), 3);
        check("last word broken", solution.canBeTypedWords("abc def ghi", "iz"), 2);
    }
}

/*
 * Brute Force:
 * I split the text into words, then compare each character in a word with each
 * broken letter before deciding whether that word can be typed.
 *
 * Time Complexity: O(n * b), where n is the text length and b is the number of
 * broken letters.
 * Space Complexity: O(w), where w is the number of words created by splitting
 * the text.
 *
 * Optimal Interview Solution:
 * I mark broken letters in a constant-size lookup table, then scan the text once
 * while tracking whether the current word contains a broken character.
 *
 * Time Complexity: O(n + b), where n is the text length and b is the number of
 * broken letters.
 * Space Complexity: O(1), because the lookup table has fixed alphabet size.
 */
