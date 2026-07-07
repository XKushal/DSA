class FindTheDifference {
    public char bruteForceFindTheDifference(String s, String t) {
        boolean[] used = new boolean[t.length()];

        for (int sIndex = 0; sIndex < s.length(); sIndex++) {
            boolean matched = false;

            for (int tIndex = 0; tIndex < t.length(); tIndex++) {
                if (!used[tIndex] && s.charAt(sIndex) == t.charAt(tIndex)) {
                    used[tIndex] = true;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                throw new IllegalArgumentException("t must contain every character from s");
            }
        }

        for (int index = 0; index < t.length(); index++) {
            if (!used[index]) {
                return t.charAt(index);
            }
        }

        throw new IllegalArgumentException("t must contain one extra character");
    }

    public char findTheDifference(String s, String t) {
        int result = 0;

        for (int index = 0; index < s.length(); index++) {
            result ^= s.charAt(index);
        }

        for (int index = 0; index < t.length(); index++) {
            result ^= t.charAt(index);
        }

        return (char) result;
    }

    private static void check(String name, char actual, char expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindTheDifference solution = new FindTheDifference();

        check("brute force finds appended character", solution.bruteForceFindTheDifference("abcd", "abcde"), 'e');

        check("finds appended character", solution.findTheDifference("abcd", "abcde"), 'e');
        check("handles repeated letters", solution.findTheDifference("aabb", "ababb"), 'b');
        check("handles single extra character", solution.findTheDifference("", "y"), 'y');
        check("finds extra character at the front", solution.findTheDifference("xyz", "zxyq"), 'q');
    }
}

/*
 * Brute Force:
 * I mark each character in t as used when it matches a character from s. The
 * remaining unused character in t is the added character.
 *
 * Time Complexity: O(n^2), where n is the length of t, because each character
 * from s may scan t to find an unused match.
 * Space Complexity: O(n), because the used array tracks characters in t.
 *
 * Optimal Interview Solution:
 * I XOR every character from both strings. Matching characters cancel out, so
 * only the added character remains.
 *
 * Time Complexity: O(n), where n is the length of t, because both strings are
 * scanned once.
 * Space Complexity: O(1), because only the XOR accumulator is stored.
 */
