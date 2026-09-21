class CheckIfAllCharactersHaveEqualNumberOfOccurrences {
    public boolean bruteForceAreOccurrencesEqual(String s) {
        boolean[] seen = new boolean[26];
        int expectedCount = -1;

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            if (seen[index]) {
                continue;
            }

            seen[index] = true;
            int count = 0;

            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    count++;
                }
            }

            if (expectedCount == -1) {
                expectedCount = count;
            } else if (count != expectedCount) {
                return false;
            }
        }

        return true;
    }

    public boolean areOccurrencesEqual(String s) {
        int[] frequencies = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
        }

        int expectedCount = 0;

        for (int frequency : frequencies) {
            if (frequency == 0) {
                continue;
            }

            if (expectedCount == 0) {
                expectedCount = frequency;
            } else if (frequency != expectedCount) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CheckIfAllCharactersHaveEqualNumberOfOccurrences solution =
                new CheckIfAllCharactersHaveEqualNumberOfOccurrences();

        check("brute force sample one", solution.bruteForceAreOccurrencesEqual("abacbc"), true);
        check("brute force sample two", solution.bruteForceAreOccurrencesEqual("aaabb"), false);

        check("sample one", solution.areOccurrencesEqual("abacbc"), true);
        check("sample two", solution.areOccurrencesEqual("aaabb"), false);
        check("single character", solution.areOccurrencesEqual("z"), true);
        check("all pairs", solution.areOccurrencesEqual("aabbccdd"), true);
        check("late mismatch", solution.areOccurrencesEqual("aabbccd"), false);
    }
}

/*
 * Brute Force:
 * I visit each distinct character and scan the whole string to count how many
 * times that character appears.
 *
 * Time Complexity: O(n * k), where n is the string length and k is the number
 * of distinct characters.
 * Space Complexity: O(1), because the lowercase alphabet size is fixed.
 *
 * Optimal Interview Solution:
 * I count every character once, then compare all nonzero frequencies against
 * the first frequency found.
 *
 * Time Complexity: O(n), where n is the string length.
 * Space Complexity: O(1), because the frequency table has a fixed size of 26.
 */
