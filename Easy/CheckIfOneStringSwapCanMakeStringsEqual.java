class CheckIfOneStringSwapCanMakeStringsEqual {
    public boolean bruteForceAreAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        char[] chars = s1.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                swap(chars, i, j);

                if (matches(chars, s2)) {
                    swap(chars, i, j);
                    return true;
                }

                swap(chars, i, j);
            }
        }

        return false;
    }

    public boolean areAlmostEqual(String s1, String s2) {
        int firstMismatch = -1;
        int secondMismatch = -1;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }

            if (firstMismatch == -1) {
                firstMismatch = i;
            } else if (secondMismatch == -1) {
                secondMismatch = i;
            } else {
                return false;
            }
        }

        if (firstMismatch == -1) {
            return true;
        }

        return secondMismatch != -1
                && s1.charAt(firstMismatch) == s2.charAt(secondMismatch)
                && s1.charAt(secondMismatch) == s2.charAt(firstMismatch);
    }

    private void swap(char[] chars, int left, int right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }

    private boolean matches(char[] chars, String target) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != target.charAt(i)) {
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
        CheckIfOneStringSwapCanMakeStringsEqual solution = new CheckIfOneStringSwapCanMakeStringsEqual();

        check("brute force sample one", solution.bruteForceAreAlmostEqual("bank", "kanb"), true);
        check("brute force sample two", solution.bruteForceAreAlmostEqual("attack", "defend"), false);

        check("sample one", solution.areAlmostEqual("bank", "kanb"), true);
        check("sample two", solution.areAlmostEqual("attack", "defend"), false);
        check("sample three", solution.areAlmostEqual("kelb", "kelb"), true);
        check("one mismatch", solution.areAlmostEqual("abcd", "abed"), false);
        check("more than two mismatches", solution.areAlmostEqual("abcd", "badc"), false);
    }
}

/*
 * Brute Force:
 * I try every possible pair of indices in the first string, swap that pair, and
 * compare the result with the second string.
 *
 * Time Complexity: O(n^3), where n is the string length, because there are O(n^2)
 * swaps and each comparison scans the string.
 * Space Complexity: O(n), because the first string is copied into a character
 * array for swapping.
 *
 * Optimal Interview Solution:
 * I scan both strings once, record up to two mismatch positions, and verify that
 * those two characters cross-match after one swap.
 *
 * Time Complexity: O(n), where n is the string length.
 * Space Complexity: O(1), because only two mismatch indices are stored.
 */
