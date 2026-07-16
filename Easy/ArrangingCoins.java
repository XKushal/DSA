class ArrangingCoins {
    public int bruteForceArrangeCoins(int n) {
        int completeRows = 0;
        int nextRowCoins = 1;

        while (n >= nextRowCoins) {
            n -= nextRowCoins;
            completeRows++;
            nextRowCoins++;
        }

        return completeRows;
    }

    public int arrangeCoins(int n) {
        int left = 0;
        int right = n;
        int answer = 0;

        while (left <= right) {
            int rows = left + (right - left) / 2;
            long requiredCoins = (long) rows * (rows + 1) / 2;

            if (requiredCoins <= n) {
                answer = rows;
                left = rows + 1;
            } else {
                right = rows - 1;
            }
        }

        return answer;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ArrangingCoins solution = new ArrangingCoins();

        check("brute force handles partial third row", solution.bruteForceArrangeCoins(5), 2);
        check("brute force handles exact row count", solution.bruteForceArrangeCoins(6), 3);

        check("handles partial third row", solution.arrangeCoins(5), 2);
        check("handles exact row count", solution.arrangeCoins(8), 3);
        check("handles one coin", solution.arrangeCoins(1), 1);
        check("handles no complete positive row", solution.arrangeCoins(0), 0);
        check("handles large input without overflow", solution.arrangeCoins(1804289383), 60070);
    }
}

/*
 * Brute Force:
 * I spend coins row by row, increasing the next row size by one each time, and
 * stop when the remaining coins cannot complete the next row.
 *
 * Time Complexity: O(k), where k is the number of complete rows formed.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I binary search the largest row count whose triangular-number coin total is
 * less than or equal to n.
 *
 * Time Complexity: O(log n), because the possible row count range is halved on
 * each iteration.
 * Space Complexity: O(1), because only a few numeric variables are stored.
 */
