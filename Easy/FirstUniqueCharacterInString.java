class FirstUniqueCharacterInString {
    public int bruteForceFirstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            boolean unique = true;

            for (int j = 0; j < s.length(); j++) {
                if (i != j && s.charAt(i) == s.charAt(j)) {
                    unique = false;
                    break;
                }
            }

            if (unique) {
                return i;
            }
        }

        return -1;
    }

    public int firstUniqChar(String s) {
        int[] frequencies = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (frequencies[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FirstUniqueCharacterInString solution = new FirstUniqueCharacterInString();

        check("brute force finds first unique character", solution.bruteForceFirstUniqChar("leetcode"), 0);
        check("brute force handles no unique character", solution.bruteForceFirstUniqChar("aabb"), -1);

        check("finds first unique character", solution.firstUniqChar("leetcode"), 0);
        check("skips repeated prefix", solution.firstUniqChar("loveleetcode"), 2);
        check("handles no unique character", solution.firstUniqChar("aabb"), -1);
        check("handles a unique final character", solution.firstUniqChar("aabbc"), 4);
    }
}

/*
 * Brute Force:
 * I check each character against every other character and return the first
 * index that has no duplicate elsewhere in the string.
 *
 * Time Complexity: O(n^2), where n is the length of the string.
 * Space Complexity: O(1), because only loop counters and a flag are used.
 *
 * Optimal Interview Solution:
 * I count each lowercase letter, then scan the string again and return the
 * first index whose character count is exactly one.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(1), because the frequency array has a fixed size of 26.
 */
