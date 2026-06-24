class IsSubsequence {
    public boolean bruteForceIsSubsequence(String s, String t) {
        return canBuildSubsequence(s, t, 0, 0);
    }

    public boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;

        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }

            tIndex++;
        }

        return sIndex == s.length();
    }

    private boolean canBuildSubsequence(String s, String t, int sIndex, int tIndex) {
        if (sIndex == s.length()) {
            return true;
        }

        if (tIndex == t.length()) {
            return false;
        }

        if (s.charAt(sIndex) == t.charAt(tIndex) && canBuildSubsequence(s, t, sIndex + 1, tIndex + 1)) {
            return true;
        }

        return canBuildSubsequence(s, t, sIndex, tIndex + 1);
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        IsSubsequence solution = new IsSubsequence();

        check("brute force accepts ordered matches", solution.bruteForceIsSubsequence("abc", "ahbgdc"), true);

        check("accepts ordered matches", solution.isSubsequence("abc", "ahbgdc"), true);
        check("rejects out-of-order matches", solution.isSubsequence("axc", "ahbgdc"), false);
        check("accepts an empty subsequence", solution.isSubsequence("", "ahbgdc"), true);
        check("rejects when source is longer", solution.isSubsequence("abcde", "ace"), false);
    }
}

/*
 * Brute Force:
 * I recursively try every possible way to skip or use characters from t until
 * either all characters in s are matched or t is exhausted.
 *
 * Time Complexity: O(2^n), where n is the length of t.
 * Space Complexity: O(n), because the recursive call stack can grow with t.
 *
 * Optimal Interview Solution:
 * I scan both strings with two pointers. A pointer in s only advances when the
 * current character in t matches the next needed subsequence character.
 *
 * Time Complexity: O(n + m), where n is the length of s and m is the length of t.
 * Space Complexity: O(1), because only two indexes are stored.
 */