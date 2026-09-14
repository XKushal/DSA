class CountPrefixesOfAGivenString {
    public int bruteForceCountPrefixes(String[] words, String s) {
        int count = 0;

        for (String word : words) {
            if (word.length() > s.length()) {
                continue;
            }

            boolean isPrefix = true;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != s.charAt(i)) {
                    isPrefix = false;
                    break;
                }
            }

            if (isPrefix) {
                count++;
            }
        }

        return count;
    }

    public int countPrefixes(String[] words, String s) {
        int count = 0;

        for (String word : words) {
            if (s.startsWith(word)) {
                count++;
            }
        }

        return count;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountPrefixesOfAGivenString solution = new CountPrefixesOfAGivenString();

        check("brute force sample one", solution.bruteForceCountPrefixes(
                new String[] {"a", "b", "c", "ab", "bc", "abc"}, "abc"), 3);
        check("brute force sample two", solution.bruteForceCountPrefixes(
                new String[] {"a", "a"}, "aa"), 2);

        check("sample one", solution.countPrefixes(
                new String[] {"a", "b", "c", "ab", "bc", "abc"}, "abc"), 3);
        check("sample two", solution.countPrefixes(new String[] {"a", "a"}, "aa"), 2);
        check("longer word", solution.countPrefixes(new String[] {"abc", "abcd", "ab"}, "abc"), 2);
        check("no prefixes", solution.countPrefixes(new String[] {"d", "ef", "gh"}, "abc"), 0);
    }
}

/*
 * Brute Force:
 * I compare each candidate word character by character with the beginning of s
 * and count it only when every character matches.
 *
 * Time Complexity: O(n * m), where n is the number of words and m is the
 * maximum word length.
 * Space Complexity: O(1), because only counters and flags are stored.
 *
 * Optimal Interview Solution:
 * I use String.startsWith to test each word as a prefix of s and count the
 * matching words.
 *
 * Time Complexity: O(n * m), where n is the number of words and m is the
 * maximum word length checked by startsWith.
 * Space Complexity: O(1), because no extra storage grows with the input.
 */
