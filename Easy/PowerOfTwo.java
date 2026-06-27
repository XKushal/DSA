class PowerOfTwo {
    public boolean bruteForceIsPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        long power = 1;
        while (power <= n) {
            if (power == n) {
                return true;
            }

            power *= 2;
        }

        return false;
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        PowerOfTwo solution = new PowerOfTwo();

        check("brute force accepts sixteen", solution.bruteForceIsPowerOfTwo(16), true);

        check("accepts one", solution.isPowerOfTwo(1), true);
        check("accepts sixteen", solution.isPowerOfTwo(16), true);
        check("rejects three", solution.isPowerOfTwo(3), false);
        check("rejects zero", solution.isPowerOfTwo(0), false);
        check("rejects negative value", solution.isPowerOfTwo(-16), false);
    }
}

/*
 * Brute Force:
 * I generate powers of two from one upward until finding the input or passing
 * it. A long stores the generated value so doubling cannot overflow an int.
 *
 * Time Complexity: O(log n) for positive n, because the candidate doubles each
 * iteration.
 * Space Complexity: O(1), because only the current power is stored.
 *
 * Optimal Interview Solution:
 * A positive power of two contains exactly one set bit. Subtracting one clears
 * that bit and sets every lower bit, so the bitwise AND of both values is zero.
 *
 * Time Complexity: O(1), because the solution performs fixed bit operations.
 * Space Complexity: O(1), because no additional data structure is used.
 */
