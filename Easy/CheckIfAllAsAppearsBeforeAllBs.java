class CheckIfAllAsAppearsBeforeAllBs {
    public boolean bruteForceCheckString(String s) {
        for (int left = 0; left < s.length(); left++) {
            for (int right = left + 1; right < s.length(); right++) {
                if (s.charAt(left) == 'b' && s.charAt(right) == 'a') {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkString(String s) {
        boolean seenB = false;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == 'b') {
                seenB = true;
            } else if (seenB) {
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
        CheckIfAllAsAppearsBeforeAllBs solution = new CheckIfAllAsAppearsBeforeAllBs();

        check("brute force valid", solution.bruteForceCheckString("aaabbb"), true);
        check("brute force invalid", solution.bruteForceCheckString("abab"), false);

        check("valid split", solution.checkString("aaabbb"), true);
        check("invalid alternating", solution.checkString("abab"), false);
        check("all a", solution.checkString("aaaa"), true);
        check("all b", solution.checkString("bbbb"), true);
        check("late a", solution.checkString("bbbba"), false);
    }
}

/*
 * Brute Force:
 * I compare every ordered pair of positions and reject the string if a b
 * appears before a later a.
 *
 * Time Complexity: O(n^2), because every pair of positions can be checked.
 * Space Complexity: O(1), because only loop counters are stored.
 *
 * Optimal Interview Solution:
 * I scan once, remember whether a b has appeared, and reject the first a that
 * appears after that point.
 *
 * Time Complexity: O(n), because the string is scanned once.
 * Space Complexity: O(1), because only one boolean state is stored.
 */
