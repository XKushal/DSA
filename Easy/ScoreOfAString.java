class ScoreOfAString {
    public int bruteForceScoreOfString(String s) {
        int score = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            int current = s.charAt(i);
            int next = s.charAt(i + 1);
            score += Math.abs(current - next);
        }

        return score;
    }

    public int scoreOfString(String s) {
        int score = 0;

        for (int i = 1; i < s.length(); i++) {
            score += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }

        return score;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ScoreOfAString solution = new ScoreOfAString();

        check("brute force sample one", solution.bruteForceScoreOfString("hello"), 13);
        check("brute force sample two", solution.bruteForceScoreOfString("zaz"), 50);

        check("sample one", solution.scoreOfString("hello"), 13);
        check("sample two", solution.scoreOfString("zaz"), 50);
        check("same letters", solution.scoreOfString("aaaa"), 0);
        check("two letters", solution.scoreOfString("az"), 25);
        check("mixed word", solution.scoreOfString("leetcode"), 63);
    }
}

/*
 * Brute Force:
 * I walk through every adjacent pair, convert both characters to their ASCII
 * values, and add their absolute difference to the score.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(1), because no extra storage grows with the input.
 *
 * Optimal Interview Solution:
 * I accumulate the same adjacent differences directly while scanning from the
 * second character, comparing each character with the previous one.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(1), because only the running score is stored.
 */
