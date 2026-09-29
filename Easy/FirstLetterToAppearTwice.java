class FirstLetterToAppearTwice {
    public char bruteForceRepeatedCharacter(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return s.charAt(i);
                }
            }
        }

        return '\0';
    }

    public char repeatedCharacter(String s) {
        boolean[] seen = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            if (seen[index]) {
                return s.charAt(i);
            }

            seen[index] = true;
        }

        return '\0';
    }

    private static void check(String name, char actual, char expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FirstLetterToAppearTwice solution = new FirstLetterToAppearTwice();

        check("brute force sample one", solution.bruteForceRepeatedCharacter("abccbaacz"), 'c');
        check("brute force sample two", solution.bruteForceRepeatedCharacter("abcdd"), 'd');

        check("sample one", solution.repeatedCharacter("abccbaacz"), 'c');
        check("sample two", solution.repeatedCharacter("abcdd"), 'd');
        check("early repeat", solution.repeatedCharacter("zz"), 'z');
        check("later first repeat", solution.repeatedCharacter("abcdefghijklmnopqrstuvwxyza"), 'a');
    }
}

/*
 * Brute Force:
 * I scan each character and compare it with every earlier character, returning
 * the first character whose second appearance I encounter.
 *
 * Time Complexity: O(n^2), because each position can scan all earlier
 * positions.
 * Space Complexity: O(1), because only loop counters are stored.
 *
 * Optimal Interview Solution:
 * I track lowercase letters in a fixed-size seen table and return the first
 * character that has already been marked.
 *
 * Time Complexity: O(n), because the string is scanned once.
 * Space Complexity: O(1), because the seen table has fixed alphabet size.
 */
