class LongestCommonPrefix {
    public String bruteForceLongestCommonPrefix(String[] strs) {
        String first = strs[0];

        for (int prefixLength = first.length(); prefixLength >= 0; prefixLength--) {
            String candidate = first.substring(0, prefixLength);
            boolean matchesAll = true;

            for (int index = 1; index < strs.length; index++) {
                if (!strs[index].startsWith(candidate)) {
                    matchesAll = false;
                    break;
                }
            }

            if (matchesAll) {
                return candidate;
            }
        }

        return "";
    }

    public String longestCommonPrefix(String[] strs) {
        for (int characterIndex = 0; characterIndex < strs[0].length(); characterIndex++) {
            char expected = strs[0].charAt(characterIndex);

            for (int wordIndex = 1; wordIndex < strs.length; wordIndex++) {
                if (characterIndex == strs[wordIndex].length()
                        || strs[wordIndex].charAt(characterIndex) != expected) {
                    return strs[0].substring(0, characterIndex);
                }
            }
        }

        return strs[0];
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();

        check("brute force finds shared prefix",
                solution.bruteForceLongestCommonPrefix(new String[] {"flower", "flow", "flight"}), "fl");

        check("finds shared prefix", solution.longestCommonPrefix(new String[] {"flower", "flow", "flight"}), "fl");
        check("returns empty string when no prefix matches",
                solution.longestCommonPrefix(new String[] {"dog", "racecar", "car"}), "");
        check("handles one word", solution.longestCommonPrefix(new String[] {"alone"}), "alone");
        check("handles empty word", solution.longestCommonPrefix(new String[] {"", "b"}), "");
        check("stops at shorter matching word", solution.longestCommonPrefix(new String[] {"interview", "interval", "into"}), "int");
    }
}

/*
 * Brute Force:
 * I try every prefix of the first word from longest to shortest and check
 * whether each other word starts with that candidate.
 *
 * Time Complexity: O(n * m^2), where n is the number of strings and m is the
 * length of the first string, because each candidate prefix can be compared
 * across all words.
 * Space Complexity: O(m), because each candidate prefix can contain up to m
 * characters.
 *
 * Optimal Interview Solution:
 * I scan each character position vertically across all words. The first
 * mismatch or exhausted word marks the end of the common prefix.
 *
 * Time Complexity: O(n * m), where n is the number of strings and m is the
 * length of the shortest relevant prefix scan.
 * Space Complexity: O(1), because only loop indices and the expected
 * character are stored beyond the returned prefix.
 */
