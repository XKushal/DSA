class ValidPerfectSquare {
    public boolean bruteForceIsPerfectSquare(int num) {
        long candidate = 1;

        while (candidate * candidate <= num) {
            if (candidate * candidate == num) {
                return true;
            }

            candidate++;
        }

        return false;
    }

    public boolean isPerfectSquare(int num) {
        long left = 1;
        long right = num;

        while (left <= right) {
            long middle = left + (right - left) / 2;
            long square = middle * middle;

            if (square == num) {
                return true;
            }

            if (square < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return false;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ValidPerfectSquare solution = new ValidPerfectSquare();

        check("brute force accepts perfect square", solution.bruteForceIsPerfectSquare(16), true);
        check("brute force rejects non-square", solution.bruteForceIsPerfectSquare(14), false);

        check("accepts one", solution.isPerfectSquare(1), true);
        check("accepts perfect square", solution.isPerfectSquare(808201), true);
        check("rejects non-square", solution.isPerfectSquare(14), false);
        check("rejects max integer", solution.isPerfectSquare(2147483647), false);
    }
}

/*
 * Brute Force:
 * I test each positive candidate from one upward until the square reaches or
 * passes num.
 *
 * Time Complexity: O(sqrt(n)), where n is num.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I binary search the possible square roots from one through num, comparing
 * each middle value squared against num. Long arithmetic avoids overflow for
 * large int inputs.
 *
 * Time Complexity: O(log n), where n is num.
 * Space Complexity: O(1), because the search uses a fixed number of variables.
 */
