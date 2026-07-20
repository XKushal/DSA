class PowerOfThree {
    public boolean bruteForceIsPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        long power = 1;

        while (power < n) {
            power *= 3;
        }

        return power == n;
    }

    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        PowerOfThree solution = new PowerOfThree();

        check("brute force accepts one", solution.bruteForceIsPowerOfThree(1), true);
        check("brute force accepts twenty seven", solution.bruteForceIsPowerOfThree(27), true);
        check("brute force rejects non-power", solution.bruteForceIsPowerOfThree(45), false);

        check("accepts one", solution.isPowerOfThree(1), true);
        check("accepts three", solution.isPowerOfThree(3), true);
        check("accepts twenty seven", solution.isPowerOfThree(27), true);
        check("rejects zero", solution.isPowerOfThree(0), false);
        check("rejects negative number", solution.isPowerOfThree(-3), false);
        check("rejects nearby non-power", solution.isPowerOfThree(45), false);
        check("accepts largest int power", solution.isPowerOfThree(1162261467), true);
    }
}

/*
 * Brute Force:
 * I generate powers of three from 1 until the value reaches or passes n, then
 * check whether the generated value is exactly n.
 *
 * Time Complexity: O(log n), because the generated value triples after each
 * step.
 * Space Complexity: O(1), because only the current generated power is stored.
 *
 * Optimal Interview Solution:
 * I use 1162261467, the largest power of three that fits in a signed 32-bit
 * integer. Any positive integer power of three must divide that value evenly.
 *
 * Time Complexity: O(1), because only a sign check and modulo operation are
 * needed.
 * Space Complexity: O(1), because no extra data structures are used.
 */
