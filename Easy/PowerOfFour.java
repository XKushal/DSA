class PowerOfFour {
    public boolean bruteForceIsPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }

        long power = 1;

        while (power < n) {
            power *= 4;
        }

        return power == n;
    }

    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        PowerOfFour solution = new PowerOfFour();

        check("brute force accepts one", solution.bruteForceIsPowerOfFour(1), true);
        check("brute force accepts sixty four", solution.bruteForceIsPowerOfFour(64), true);
        check("brute force rejects nearby non-power", solution.bruteForceIsPowerOfFour(8), false);

        check("accepts one", solution.isPowerOfFour(1), true);
        check("accepts four", solution.isPowerOfFour(4), true);
        check("accepts sixty four", solution.isPowerOfFour(64), true);
        check("rejects zero", solution.isPowerOfFour(0), false);
        check("rejects negative value", solution.isPowerOfFour(-4), false);
        check("rejects power of two at odd bit", solution.isPowerOfFour(8), false);
        check("accepts largest int power", solution.isPowerOfFour(1073741824), true);
    }
}

/*
 * Brute Force:
 * I generate powers of four from 1 until the value reaches or passes n, then
 * check whether the generated value is exactly n.
 *
 * Time Complexity: O(log n), because the generated value quadruples after each
 * step.
 * Space Complexity: O(1), because only the current generated power is stored.
 *
 * Optimal Interview Solution:
 * I first verify n is a positive power of two, then use the bit mask 0x55555555
 * to ensure the single set bit is in an even-positioned slot used by powers of
 * four.
 *
 * Time Complexity: O(1), because only constant-time bit operations are used.
 * Space Complexity: O(1), because no extra data structures are used.
 */
