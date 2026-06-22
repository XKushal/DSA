class FindFirstOccurrenceInString {
    public int bruteForceStrStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        int lastStart = haystack.length() - needle.length();

        for (int start = 0; start <= lastStart; start++) {
            int needleIndex = 0;

            while (needleIndex < needle.length()
                    && haystack.charAt(start + needleIndex) == needle.charAt(needleIndex)) {
                needleIndex++;
            }

            if (needleIndex == needle.length()) {
                return start;
            }
        }

        return -1;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        int[] longestPrefixSuffix = buildLongestPrefixSuffix(needle);
        int haystackIndex = 0;
        int needleIndex = 0;

        while (haystackIndex < haystack.length()) {
            if (haystack.charAt(haystackIndex) == needle.charAt(needleIndex)) {
                haystackIndex++;
                needleIndex++;

                if (needleIndex == needle.length()) {
                    return haystackIndex - needleIndex;
                }
            } else if (needleIndex > 0) {
                needleIndex = longestPrefixSuffix[needleIndex - 1];
            } else {
                haystackIndex++;
            }
        }

        return -1;
    }

    private int[] buildLongestPrefixSuffix(String needle) {
        int[] longestPrefixSuffix = new int[needle.length()];
        int prefixLength = 0;
        int index = 1;

        while (index < needle.length()) {
            if (needle.charAt(index) == needle.charAt(prefixLength)) {
                prefixLength++;
                longestPrefixSuffix[index] = prefixLength;
                index++;
            } else if (prefixLength > 0) {
                prefixLength = longestPrefixSuffix[prefixLength - 1];
            } else {
                longestPrefixSuffix[index] = 0;
                index++;
            }
        }

        return longestPrefixSuffix;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindFirstOccurrenceInString solution = new FindFirstOccurrenceInString();

        check("brute force finds needle in middle", solution.bruteForceStrStr("sadbutsad", "sad"), 0);
        check("brute force rejects missing needle", solution.bruteForceStrStr("leetcode", "leeto"), -1);

        check("finds needle at the beginning", solution.strStr("sadbutsad", "sad"), 0);
        check("finds needle after repeated prefix", solution.strStr("mississippi", "issip"), 4);
        check("rejects missing needle", solution.strStr("leetcode", "leeto"), -1);
        check("accepts empty needle", solution.strStr("abc", ""), 0);
    }
}

/*
 * Brute Force:
 * I try every possible starting index in haystack and compare the needle
 * character by character from that position.
 *
 * Time Complexity: O(n * m), where n is the length of haystack and m is the
 * length of needle.
 * Space Complexity: O(1), because only index variables are used.
 *
 * Optimal Interview Solution:
 * I build the KMP longest-prefix-suffix table for the needle, then scan the
 * haystack while reusing matched prefix information after mismatches.
 *
 * Time Complexity: O(n + m), where n is the length of haystack and m is the
 * length of needle.
 * Space Complexity: O(m), because the prefix table stores one value for each
 * needle character.
 */
