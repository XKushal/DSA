import java.util.Arrays;

class ShortestDistanceToACharacter {
    public int[] bruteForceShortestToChar(String s, char c) {
        int[] distances = new int[s.length()];

        for (int index = 0; index < s.length(); index++) {
            int bestDistance = s.length();

            for (int target = 0; target < s.length(); target++) {
                if (s.charAt(target) == c) {
                    bestDistance = Math.min(bestDistance, Math.abs(index - target));
                }
            }

            distances[index] = bestDistance;
        }

        return distances;
    }

    public int[] shortestToChar(String s, char c) {
        int[] distances = new int[s.length()];
        int previous = -s.length();

        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == c) {
                previous = index;
            }

            distances[index] = index - previous;
        }

        previous = 2 * s.length();

        for (int index = s.length() - 1; index >= 0; index--) {
            if (s.charAt(index) == c) {
                previous = index;
            }

            distances[index] = Math.min(distances[index], previous - index);
        }

        return distances;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        ShortestDistanceToACharacter solution = new ShortestDistanceToACharacter();

        check("brute force finds distances around repeated targets",
                solution.bruteForceShortestToChar("loveleetcode", 'e'),
                new int[] {3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0});
        check("brute force handles one target at the start",
                solution.bruteForceShortestToChar("aaba", 'b'),
                new int[] {2, 1, 0, 1});

        check("finds distances around repeated targets",
                solution.shortestToChar("loveleetcode", 'e'),
                new int[] {3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0});
        check("handles one target at the start", solution.shortestToChar("aaba", 'b'), new int[] {2, 1, 0, 1});
        check("handles one target at the end", solution.shortestToChar("aaab", 'b'), new int[] {3, 2, 1, 0});
    }
}

/*
 * Brute Force:
 * I scan the whole string for every index and keep the closest position that
 * contains the target character.
 *
 * Time Complexity: O(n^2), where n is the length of the string.
 * Space Complexity: O(n), because the answer array stores one distance per
 * character.
 *
 * Optimal Interview Solution:
 * I make one left-to-right pass to measure distance from the nearest previous
 * target, then one right-to-left pass to fold in the nearest next target.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(n), because the answer array stores one distance per
 * character.
 */
