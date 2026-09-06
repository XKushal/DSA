class CountTheNumberOfConsistentStrings {
    public int bruteForceCountConsistentStrings(String allowed, String[] words) {
        int consistent = 0;

        for (String word : words) {
            boolean isConsistent = true;

            for (int i = 0; i < word.length(); i++) {
                if (allowed.indexOf(word.charAt(i)) == -1) {
                    isConsistent = false;
                    break;
                }
            }

            if (isConsistent) {
                consistent++;
            }
        }

        return consistent;
    }

    public int countConsistentStrings(String allowed, String[] words) {
        int allowedMask = 0;

        for (int i = 0; i < allowed.length(); i++) {
            allowedMask |= 1 << (allowed.charAt(i) - 'a');
        }

        int consistent = 0;
        for (String word : words) {
            boolean isConsistent = true;

            for (int i = 0; i < word.length(); i++) {
                int letterMask = 1 << (word.charAt(i) - 'a');

                if ((allowedMask & letterMask) == 0) {
                    isConsistent = false;
                    break;
                }
            }

            if (isConsistent) {
                consistent++;
            }
        }

        return consistent;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountTheNumberOfConsistentStrings solution = new CountTheNumberOfConsistentStrings();

        check("brute force sample", solution.bruteForceCountConsistentStrings(
                "ab", new String[] {"ad", "bd", "aaab", "baa", "badab"}), 2);
        check("brute force all valid", solution.bruteForceCountConsistentStrings(
                "abc", new String[] {"a", "b", "c", "ab", "ac", "bc", "abc"}), 7);

        check("sample", solution.countConsistentStrings(
                "ab", new String[] {"ad", "bd", "aaab", "baa", "badab"}), 2);
        check("all valid", solution.countConsistentStrings(
                "abc", new String[] {"a", "b", "c", "ab", "ac", "bc", "abc"}), 7);
        check("mixed letters", solution.countConsistentStrings(
                "cad", new String[] {"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"}), 4);
        check("single allowed", solution.countConsistentStrings(
                "z", new String[] {"z", "zz", "a", "za"}), 2);
    }
}

/*
 * Brute Force:
 * I inspect each character in each word and search the allowed string directly
 * to decide whether that character is permitted.
 *
 * Time Complexity: O(n * m * a), where n is the number of words, m is the
 * average word length, and a is the length of allowed.
 * Space Complexity: O(1), because only counters and flags are stored.
 *
 * Optimal Interview Solution:
 * I encode the allowed characters in a bitmask, then check each word character
 * with a constant-time bit test.
 *
 * Time Complexity: O(a + n * m), where a is the length of allowed, n is the
 * number of words, and m is the average word length.
 * Space Complexity: O(1), because the alphabet bitmask has fixed size.
 */
