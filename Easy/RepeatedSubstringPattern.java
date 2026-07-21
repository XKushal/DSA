class RepeatedSubstringPattern {
    public boolean bruteForceRepeatedSubstringPattern(String s) {
        int length = s.length();

        for (int patternLength = 1; patternLength <= length / 2; patternLength++) {
            if (length % patternLength != 0) {
                continue;
            }

            boolean repeated = true;
            for (int index = patternLength; index < length; index++) {
                if (s.charAt(index) != s.charAt(index % patternLength)) {
                    repeated = false;
                    break;
                }
            }

            if (repeated) {
                return true;
            }
        }

        return false;
    }

    public boolean repeatedSubstringPattern(String s) {
        int[] longestPrefixSuffix = buildLongestPrefixSuffix(s);
        int repeatedPrefixLength = longestPrefixSuffix[s.length() - 1];
        int patternLength = s.length() - repeatedPrefixLength;

        return repeatedPrefixLength > 0 && s.length() % patternLength == 0;
    }

    private int[] buildLongestPrefixSuffix(String s) {
        int[] longestPrefixSuffix = new int[s.length()];
        int prefixLength = 0;
        int index = 1;

        while (index < s.length()) {
            if (s.charAt(index) == s.charAt(prefixLength)) {
                prefixLength++;
                longestPrefixSuffix[index] = prefixLength;
                index++;
            } else if (prefixLength > 0) {
                prefixLength = longestPrefixSuffix[prefixLength - 1];
            } else {
                index++;
            }
        }

        return longestPrefixSuffix;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        RepeatedSubstringPattern solution = new RepeatedSubstringPattern();

        check("brute force accepts repeated pair", solution.bruteForceRepeatedSubstringPattern("abab"), true);
        check("brute force rejects no repeat", solution.bruteForceRepeatedSubstringPattern("aba"), false);
        check("brute force accepts repeated triple", solution.bruteForceRepeatedSubstringPattern("abcabcabc"), true);

        check("accepts repeated pair", solution.repeatedSubstringPattern("abab"), true);
        check("accepts single character pattern", solution.repeatedSubstringPattern("aaaa"), true);
        check("accepts repeated triple", solution.repeatedSubstringPattern("abcabcabc"), true);
        check("rejects no repeat", solution.repeatedSubstringPattern("aba"), false);
        check("rejects almost repeated string", solution.repeatedSubstringPattern("abac"), false);
        check("rejects one character", solution.repeatedSubstringPattern("a"), false);
    }
}

/*
 * Brute Force:
 * I try every possible prefix length that divides the string length, then
 * compare the rest of the string against that prefix by modulo index.
 *
 * Time Complexity: O(n^2), where n is the length of s.
 * Space Complexity: O(1), because no extra data structure scales with input.
 *
 * Optimal Interview Solution:
 * I build the KMP longest-prefix-suffix table. If the final prefix-suffix length
 * leaves a smaller pattern length that divides the full string, that smaller
 * pattern repeats to form the original string.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(n), because the prefix-suffix table stores one value per
 * character.
 */
