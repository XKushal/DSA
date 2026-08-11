class CountOddNumbersInAnIntervalRange {
    public int bruteForceCountOdds(int low, int high) {
        int count = 0;

        for (int value = low; value <= high; value++) {
            if (value % 2 != 0) {
                count++;
            }
        }

        return count;
    }

    public int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountOddNumbersInAnIntervalRange solution = new CountOddNumbersInAnIntervalRange();

        check("brute force odd endpoints", solution.bruteForceCountOdds(3, 7), 3);
        check("brute force even endpoints", solution.bruteForceCountOdds(8, 10), 1);
        check("brute force single even", solution.bruteForceCountOdds(4, 4), 0);

        check("odd endpoints", solution.countOdds(3, 7), 3);
        check("even endpoints", solution.countOdds(8, 10), 1);
        check("single even", solution.countOdds(4, 4), 0);
        check("single odd", solution.countOdds(5, 5), 1);
        check("starts at zero", solution.countOdds(0, 9), 5);
    }
}

/*
 * Brute Force:
 * I scan every value in the inclusive range and count the values with an odd
 * remainder when divided by 2.
 *
 * Time Complexity: O(high - low + 1), because every number in the range is
 * checked once.
 * Space Complexity: O(1), because only a counter is stored.
 *
 * Optimal Interview Solution:
 * I count how many odd numbers are at most high, then subtract how many odd
 * numbers are strictly below low.
 *
 * Time Complexity: O(1), because the answer is computed directly.
 * Space Complexity: O(1), because no extra data structures are used.
 */
