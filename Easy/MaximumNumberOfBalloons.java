class MaximumNumberOfBalloons {
    public int bruteForceMaxNumberOfBalloons(String text) {
        int[] counts = new int[26];

        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i) - 'a']++;
        }

        int balloons = 0;

        while (canBuildBalloon(counts)) {
            counts['b' - 'a']--;
            counts['a' - 'a']--;
            counts['l' - 'a'] -= 2;
            counts['o' - 'a'] -= 2;
            counts['n' - 'a']--;
            balloons++;
        }

        return balloons;
    }

    private boolean canBuildBalloon(int[] counts) {
        return counts['b' - 'a'] >= 1
                && counts['a' - 'a'] >= 1
                && counts['l' - 'a'] >= 2
                && counts['o' - 'a'] >= 2
                && counts['n' - 'a'] >= 1;
    }

    public int maxNumberOfBalloons(String text) {
        int[] counts = new int[26];

        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i) - 'a']++;
        }

        int singleLetters = Math.min(counts['b' - 'a'], Math.min(counts['a' - 'a'], counts['n' - 'a']));
        int doubleLetters = Math.min(counts['l' - 'a'] / 2, counts['o' - 'a'] / 2);

        return Math.min(singleLetters, doubleLetters);
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumNumberOfBalloons solution = new MaximumNumberOfBalloons();

        check("brute force builds one balloon", solution.bruteForceMaxNumberOfBalloons("nlaebolko"), 1);
        check("brute force builds two balloons", solution.bruteForceMaxNumberOfBalloons("loonbalxballpoon"), 2);
        check("brute force handles missing letters", solution.bruteForceMaxNumberOfBalloons("leetcode"), 0);

        check("builds one balloon", solution.maxNumberOfBalloons("nlaebolko"), 1);
        check("builds two balloons", solution.maxNumberOfBalloons("loonbalxballpoon"), 2);
        check("handles missing letters", solution.maxNumberOfBalloons("leetcode"), 0);
        check("handles exact repeated letters", solution.maxNumberOfBalloons("balloonballoonballoon"), 3);
    }
}

/*
 * Brute Force:
 * I count every character, then repeatedly consume the letters needed to spell
 * "balloon" until one required count is missing.
 *
 * Time Complexity: O(n + k), where n is the text length and k is the number of
 * balloons that can be built.
 * Space Complexity: O(1), because the alphabet count array has fixed size.
 *
 * Optimal Interview Solution:
 * I count all letters once and take the limiting count among b, a, n, l / 2,
 * and o / 2.
 *
 * Time Complexity: O(n), because the text is scanned once.
 * Space Complexity: O(1), because the alphabet count array has fixed size.
 */
