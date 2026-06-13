class ClimbingStairs {
    public int bruteForceClimbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        return bruteForceClimbStairs(n - 1) + bruteForceClimbStairs(n - 2);
    }

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int oneStepBefore = 2;
        int twoStepsBefore = 1;

        for (int step = 3; step <= n; step++) {
            int current = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = current;
        }

        return oneStepBefore;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();

        check("brute force counts two steps", solution.bruteForceClimbStairs(2), 2);
        check("brute force counts three steps", solution.bruteForceClimbStairs(3), 3);

        check("counts two steps", solution.climbStairs(2), 2);
        check("counts three steps", solution.climbStairs(3), 3);
        check("counts five steps", solution.climbStairs(5), 8);
    }
}

/*
 * Brute Force:
 * I try both choices from each step: move one step or move two steps. The
 * total ways for n steps is the sum of the ways for n - 1 and n - 2.
 *
 * Time Complexity: O(2^n), because each call can branch into two more calls.
 * Space Complexity: O(n), because the recursion stack can grow to n calls.
 *
 * Optimal Interview Solution:
 * The number of ways follows the Fibonacci pattern. I keep only the previous
 * two counts and build the answer from the bottom up.
 *
 * Time Complexity: O(n), where n is the number of stairs.
 * Space Complexity: O(1), because only two previous counts are stored.
 */
