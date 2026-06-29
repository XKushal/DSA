class MergeStringsAlternately {
    public String bruteForceMergeAlternately(String word1, String word2) {
        String merged = "";
        int maxLength = Math.max(word1.length(), word2.length());

        for (int index = 0; index < maxLength; index++) {
            if (index < word1.length()) {
                merged += word1.charAt(index);
            }
            if (index < word2.length()) {
                merged += word2.charAt(index);
            }
        }

        return merged;
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder(word1.length() + word2.length());
        int maxLength = Math.max(word1.length(), word2.length());

        for (int index = 0; index < maxLength; index++) {
            if (index < word1.length()) {
                merged.append(word1.charAt(index));
            }
            if (index < word2.length()) {
                merged.append(word2.charAt(index));
            }
        }

        return merged.toString();
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MergeStringsAlternately solution = new MergeStringsAlternately();

        check("brute force alternates equal lengths",
                solution.bruteForceMergeAlternately("abc", "pqr"), "apbqcr");

        check("alternates equal lengths", solution.mergeAlternately("abc", "pqr"), "apbqcr");
        check("appends remaining first word", solution.mergeAlternately("abcd", "pq"), "apbqcd");
        check("appends remaining second word", solution.mergeAlternately("ab", "pqrs"), "apbqrs");
        check("handles single characters", solution.mergeAlternately("a", "z"), "az");
    }
}

/*
 * Brute Force:
 * I alternate characters by repeatedly concatenating them to an immutable
 * String, then append characters from whichever word is longer.
 *
 * Time Complexity: O((m + n)^2), where m and n are the word lengths, because
 * every concatenation can copy the merged characters accumulated so far.
 * Space Complexity: O(m + n) for the returned string.
 *
 * Optimal Interview Solution:
 * I alternate characters into a pre-sized StringBuilder and continue until
 * every character from both words has been appended.
 *
 * Time Complexity: O(m + n), because every input character is appended once.
 * Space Complexity: O(m + n) for the required returned string and O(1)
 * auxiliary space beyond its builder.
 */
