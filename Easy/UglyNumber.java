class UglyNumber {
    public boolean bruteForceIsUgly(int n) {
        if (n <= 0) {
            return false;
        }

        int remaining = n;

        for (int factor = 2; factor <= remaining; factor++) {
            while (remaining % factor == 0) {
                if (factor != 2 && factor != 3 && factor != 5) {
                    return false;
                }

                remaining /= factor;
            }
        }

        return true;
    }

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        int remaining = n;
        int[] allowedFactors = {2, 3, 5};

        for (int factor : allowedFactors) {
            while (remaining % factor == 0) {
                remaining /= factor;
            }
        }

        return remaining == 1;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        UglyNumber solution = new UglyNumber();

        check("brute force accepts one", solution.bruteForceIsUgly(1), true);
        check("brute force accepts allowed factors", solution.bruteForceIsUgly(6), true);
        check("brute force rejects other prime factors", solution.bruteForceIsUgly(14), false);
        check("brute force rejects non-positive values", solution.bruteForceIsUgly(0), false);

        check("accepts one", solution.isUgly(1), true);
        check("accepts allowed factors", solution.isUgly(6), true);
        check("accepts combined allowed factors", solution.isUgly(30), true);
        check("rejects other prime factors", solution.isUgly(14), false);
        check("rejects negative values", solution.isUgly(-6), false);
    }
}

/*
 * Brute Force:
 * I factor the number by trying possible factors in increasing order, rejecting
 * the number as soon as any prime factor outside 2, 3, and 5 appears.
 *
 * Time Complexity: O(n), because the loop can try factors up to the remaining
 * value for prime inputs.
 * Space Complexity: O(1), because only counters and the remaining value are
 * stored.
 *
 * Optimal Interview Solution:
 * I divide out all factors of 2, 3, and 5, then check whether the remaining
 * value has been reduced to 1.
 *
 * Time Complexity: O(log n), because each division shrinks the remaining value
 * by at least a factor of 2.
 * Space Complexity: O(1), because only a fixed set of factors and counters are
 * stored.
 */
