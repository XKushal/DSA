class FibonacciNumber {
    public int bruteForceFib(int n) {
        if (n <= 1) {
            return n;
        }

        return bruteForceFib(n - 1) + bruteForceFib(n - 2);
    }

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int twoNumbersBefore = 0;
        int oneNumberBefore = 1;

        for (int number = 2; number <= n; number++) {
            int current = twoNumbersBefore + oneNumberBefore;
            twoNumbersBefore = oneNumberBefore;
            oneNumberBefore = current;
        }

        return oneNumberBefore;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FibonacciNumber solution = new FibonacciNumber();

        check("brute force calculates four", solution.bruteForceFib(4), 3);

        check("handles zero", solution.fib(0), 0);
        check("handles one", solution.fib(1), 1);
        check("calculates four", solution.fib(4), 3);
        check("calculates ten", solution.fib(10), 55);
    }
}

/*
 * Brute Force:
 * I recursively calculate the two preceding Fibonacci numbers and add them
 * until reaching the base cases for zero and one.
 *
 * Time Complexity: O(2^n), because most calls branch into two recursive calls.
 * Space Complexity: O(n), because the recursion stack can grow to n calls.
 *
 * Optimal Interview Solution:
 * I build the sequence from zero and one while retaining only the two previous
 * values needed to calculate the next value.
 *
 * Time Complexity: O(n), where n is the requested sequence index.
 * Space Complexity: O(1), because only three integer values are stored.
 */
