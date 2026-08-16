class FindNumbersWithEvenNumberOfDigits {
    public int bruteForceFindNumbers(int[] nums) {
        int count = 0;

        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                count++;
            }
        }

        return count;
    }

    public int findNumbers(int[] nums) {
        int count = 0;

        for (int num : nums) {
            if (hasEvenDigits(num)) {
                count++;
            }
        }

        return count;
    }

    private boolean hasEvenDigits(int num) {
        return (num >= 10 && num <= 99) || (num >= 1000 && num <= 9999) || num == 100000;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] nums(int... values) {
        return values;
    }

    public static void main(String[] args) {
        FindNumbersWithEvenNumberOfDigits solution = new FindNumbersWithEvenNumberOfDigits();

        check("brute force mixed digits", solution.bruteForceFindNumbers(nums(12, 345, 2, 6, 7896)), 2);
        check("brute force all even digits", solution.bruteForceFindNumbers(nums(44, 1001, 9000)), 3);

        check("mixed digits", solution.findNumbers(nums(12, 345, 2, 6, 7896)), 2);
        check("single and triple digits", solution.findNumbers(nums(555, 901, 482, 1771)), 1);
        check("all even digits", solution.findNumbers(nums(44, 1001, 9000)), 3);
        check("all odd digits", solution.findNumbers(nums(1, 999, 10000)), 0);
        check("six digits", solution.findNumbers(nums(100000, 23, 4567)), 3);
    }
}

/*
 * Brute Force:
 * I convert each number to text and count it when the resulting digit length is
 * even.
 *
 * Time Complexity: O(n * d), where d is the maximum number of digits converted
 * for each value.
 * Space Complexity: O(d), because each conversion creates a string for one
 * number at a time.
 *
 * Optimal Interview Solution:
 * I use the input bounds to check the even-digit ranges directly, avoiding
 * string allocation and repeated division.
 *
 * Time Complexity: O(n), because each number is checked with constant-time
 * range comparisons.
 * Space Complexity: O(1), because only counters are stored.
 */
