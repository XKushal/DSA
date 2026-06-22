class LengthOfLastWord {
    public int bruteForceLengthOfLastWord(String s) {
        String trimmed = s.trim();

        if (trimmed.isEmpty()) {
            return 0;
        }

        String[] words = trimmed.split(" ");

        return words[words.length - 1].length();
    }

    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;

        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }

        int length = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            length++;
            index--;
        }

        return length;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LengthOfLastWord solution = new LengthOfLastWord();

        check("brute force finds last word length", solution.bruteForceLengthOfLastWord("Hello World"), 5);
        check("brute force handles trailing spaces", solution.bruteForceLengthOfLastWord("   fly me   to   the moon  "), 4);

        check("finds last word length", solution.lengthOfLastWord("Hello World"), 5);
        check("handles trailing spaces", solution.lengthOfLastWord("   fly me   to   the moon  "), 4);
        check("handles a single word", solution.lengthOfLastWord("luffy"), 5);
    }
}

/*
 * Brute Force:
 * I trim the outer spaces, split the remaining string into words, then return
 * the length of the final word.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(n), because splitting creates an array of words.
 *
 * Optimal Interview Solution:
 * I scan from the end, skip trailing spaces, then count characters until the
 * previous space or the beginning of the string is reached.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(1), because only counters are used.
 */
