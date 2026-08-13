import java.util.Arrays;

class DIStringMatch {
    public int[] bruteForceDiStringMatch(String s) {
        boolean[] used = new boolean[s.length() + 1];
        int[] permutation = new int[s.length() + 1];

        if (!buildPermutation(s, 0, used, permutation)) {
            return new int[0];
        }

        return permutation;
    }

    private boolean buildPermutation(String s, int index, boolean[] used, int[] permutation) {
        if (index == permutation.length) {
            return true;
        }

        for (int value = 0; value < permutation.length; value++) {
            if (used[value] || !matchesPreviousPattern(s, index, permutation, value)) {
                continue;
            }

            used[value] = true;
            permutation[index] = value;

            if (buildPermutation(s, index + 1, used, permutation)) {
                return true;
            }

            used[value] = false;
        }

        return false;
    }

    private boolean matchesPreviousPattern(String s, int index, int[] permutation, int value) {
        if (index == 0) {
            return true;
        }

        char relation = s.charAt(index - 1);

        if (relation == 'I') {
            return permutation[index - 1] < value;
        }

        return permutation[index - 1] > value;
    }

    public int[] diStringMatch(String s) {
        int low = 0;
        int high = s.length();
        int[] permutation = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                permutation[i] = low++;
            } else {
                permutation[i] = high--;
            }
        }

        permutation[s.length()] = low;
        return permutation;
    }

    private static void checkPermutation(String name, String pattern, int[] actual) {
        if (actual.length != pattern.length() + 1) {
            throw new AssertionError(name + " returned length " + actual.length);
        }

        boolean[] seen = new boolean[actual.length];

        for (int value : actual) {
            if (value < 0 || value >= actual.length || seen[value]) {
                throw new AssertionError(name + " returned invalid permutation " + Arrays.toString(actual));
            }

            seen[value] = true;
        }

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'I' && actual[i] >= actual[i + 1]) {
                throw new AssertionError(name + " broke increasing relation with " + Arrays.toString(actual));
            }

            if (pattern.charAt(i) == 'D' && actual[i] <= actual[i + 1]) {
                throw new AssertionError(name + " broke decreasing relation with " + Arrays.toString(actual));
            }
        }
    }

    public static void main(String[] args) {
        DIStringMatch solution = new DIStringMatch();

        checkPermutation("brute force alternating pattern", "IDID", solution.bruteForceDiStringMatch("IDID"));
        checkPermutation("brute force descending pattern", "DDI", solution.bruteForceDiStringMatch("DDI"));
        checkPermutation("brute force increasing pattern", "III", solution.bruteForceDiStringMatch("III"));

        checkPermutation("alternating pattern", "IDID", solution.diStringMatch("IDID"));
        checkPermutation("descending then increasing", "DDI", solution.diStringMatch("DDI"));
        checkPermutation("all increasing", "III", solution.diStringMatch("III"));
        checkPermutation("single decrease", "D", solution.diStringMatch("D"));
        checkPermutation("mixed pattern", "IIDDDI", solution.diStringMatch("IIDDDI"));
    }
}

/*
 * Brute Force:
 * I try every unused value from 0 through n at each index and backtrack when
 * the previous I/D relation is not satisfied.
 *
 * Time Complexity: O((n + 1)!), because the search can inspect every
 * permutation.
 * Space Complexity: O(n), because the recursion, used array, and permutation
 * each scale with the pattern length.
 *
 * Optimal Interview Solution:
 * I keep the smallest and largest unused values. An I safely takes the current
 * low value, while a D safely takes the current high value, leaving enough room
 * for the remaining suffix.
 *
 * Time Complexity: O(n), because the pattern is scanned once.
 * Space Complexity: O(n), because the returned permutation has n + 1 values.
 */
