class PlusOne {
    public int[] bruteForcePlusOne(int[] digits) {
        int[] result = new int[digits.length + 1];

        for (int index = 0; index < digits.length; index++) {
            result[index + 1] = digits[index];
        }

        int carry = 1;
        for (int index = result.length - 1; index >= 0; index--) {
            int sum = result[index] + carry;
            result[index] = sum % 10;
            carry = sum / 10;

            if (carry == 0) {
                break;
            }
        }

        if (result[0] == 0) {
            int[] trimmed = new int[digits.length];
            for (int index = 0; index < trimmed.length; index++) {
                trimmed[index] = result[index + 1];
            }
            return trimmed;
        }

        return result;
    }

    public int[] plusOne(int[] digits) {
        for (int index = digits.length - 1; index >= 0; index--) {
            if (digits[index] < 9) {
                digits[index]++;
                return digits;
            }

            digits[index] = 0;
        }

        int[] expanded = new int[digits.length + 1];
        expanded[0] = 1;

        return expanded;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (actual.length != expected.length) {
            throw new AssertionError(name + " expected length " + expected.length + " but got " + actual.length);
        }

        for (int index = 0; index < actual.length; index++) {
            if (actual[index] != expected[index]) {
                throw new AssertionError(name + " differed at index " + index);
            }
        }
    }

    public static void main(String[] args) {
        PlusOne solution = new PlusOne();

        check("brute force increments without carry", solution.bruteForcePlusOne(new int[] {1, 2, 3}), new int[] {1, 2, 4});
        check("brute force grows all nines", solution.bruteForcePlusOne(new int[] {9, 9, 9}), new int[] {1, 0, 0, 0});

        check("increments without carry", solution.plusOne(new int[] {1, 2, 3}), new int[] {1, 2, 4});
        check("carries through trailing nines", solution.plusOne(new int[] {1, 2, 9}), new int[] {1, 3, 0});
        check("grows all nines", solution.plusOne(new int[] {9, 9, 9}), new int[] {1, 0, 0, 0});
    }
}

/*
 * Brute Force:
 * I copy the digits into a result array with one extra leading slot, add one
 * from the right, then trim the leading zero when the number did not grow.
 *
 * Time Complexity: O(n), where n is the number of digits.
 * Space Complexity: O(n), because a separate result array is built.
 *
 * Optimal Interview Solution:
 * I scan from the last digit toward the front, stopping as soon as a digit can
 * be incremented without carrying. If every digit was nine, I return a new
 * array with a leading one.
 *
 * Time Complexity: O(n), where n is the number of digits.
 * Space Complexity: O(1) when the original array can be updated in place, or
 * O(n) only when all digits are nine and a larger array is required.
 */
