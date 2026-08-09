class BuddyStrings {
    public boolean bruteForceBuddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        char[] letters = s.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            for (int j = i + 1; j < letters.length; j++) {
                swap(letters, i, j);

                if (matches(letters, goal)) {
                    swap(letters, i, j);
                    return true;
                }

                swap(letters, i, j);
            }
        }

        return false;
    }

    private void swap(char[] letters, int first, int second) {
        char temp = letters[first];
        letters[first] = letters[second];
        letters[second] = temp;
    }

    private boolean matches(char[] letters, String goal) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != goal.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            return hasDuplicateLetter(s);
        }

        int firstMismatch = -1;
        int secondMismatch = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (firstMismatch == -1) {
                    firstMismatch = i;
                } else if (secondMismatch == -1) {
                    secondMismatch = i;
                } else {
                    return false;
                }
            }
        }

        return secondMismatch != -1
                && s.charAt(firstMismatch) == goal.charAt(secondMismatch)
                && s.charAt(secondMismatch) == goal.charAt(firstMismatch);
    }

    private boolean hasDuplicateLetter(String s) {
        boolean[] seen = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            if (seen[index]) {
                return true;
            }

            seen[index] = true;
        }

        return false;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        BuddyStrings solution = new BuddyStrings();

        check("brute force swaps two letters", solution.bruteForceBuddyStrings("ab", "ba"), true);
        check("brute force rejects equal unique letters", solution.bruteForceBuddyStrings("ab", "ab"), false);
        check("brute force accepts equal duplicate letters", solution.bruteForceBuddyStrings("aa", "aa"), true);

        check("swaps two letters", solution.buddyStrings("ab", "ba"), true);
        check("rejects equal unique letters", solution.buddyStrings("ab", "ab"), false);
        check("accepts equal duplicate letters", solution.buddyStrings("aa", "aa"), true);
        check("rejects different lengths", solution.buddyStrings("abcd", "abc"), false);
        check("rejects more than two mismatches", solution.buddyStrings("abcd", "badc"), false);
        check("handles separated swap", solution.buddyStrings("aaaaaaabc", "aaaaaaacb"), true);
    }
}

/*
 * Brute Force:
 * I try every pair of indices, swap the two characters, and compare the
 * resulting character array with the goal string.
 *
 * Time Complexity: O(n^3), because there are O(n^2) swaps and each comparison
 * scans up to n characters.
 * Space Complexity: O(n), because the string is copied into a character array.
 *
 * Optimal Interview Solution:
 * I handle equal strings by checking for any duplicate letter that can be
 * swapped without changing the string. Otherwise, exactly two mismatched
 * positions must cross-match after a single swap.
 *
 * Time Complexity: O(n), because each string position is scanned a constant
 * number of times.
 * Space Complexity: O(1), because the duplicate check uses a fixed alphabet
 * array.
 */
