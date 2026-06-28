import java.util.Arrays;

class CountingBits {
    public int[] bruteForceCountBits(int n) {
        int[] bitCounts = new int[n + 1];

        for (int number = 0; number <= n; number++) {
            int remaining = number;

            while (remaining > 0) {
                bitCounts[number] += remaining % 2;
                remaining /= 2;
            }
        }

        return bitCounts;
    }

    public int[] countBits(int n) {
        int[] bitCounts = new int[n + 1];

        for (int number = 1; number <= n; number++) {
            bitCounts[number] = bitCounts[number >> 1] + (number & 1);
        }

        return bitCounts;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        CountingBits solution = new CountingBits();

        check("brute force counts through two", solution.bruteForceCountBits(2), new int[] {0, 1, 1});

        check("handles zero", solution.countBits(0), new int[] {0});
        check("counts through two", solution.countBits(2), new int[] {0, 1, 1});
        check("counts through five", solution.countBits(5), new int[] {0, 1, 1, 2, 1, 2});
    }
}

/*
 * Brute Force:
 * I inspect every binary digit of each number from zero through n and count
 * how many digits are one.
 *
 * Time Complexity: O(n log n), because each number can have O(log n) binary
 * digits.
 * Space Complexity: O(n) for the result array.
 *
 * Optimal Interview Solution:
 * I reuse the count for number / 2. Shifting right removes the final bit, so
 * the current count is the smaller number's count plus that removed bit.
 *
 * Time Complexity: O(n), because each result is calculated once.
 * Space Complexity: O(n) for the required result array and O(1) auxiliary
 * space.
 */
