class RangeSumQueryImmutable {
    static class BruteForceNumArray {
        private final int[] nums;

        BruteForceNumArray(int[] nums) {
            this.nums = nums;
        }

        int sumRange(int left, int right) {
            int sum = 0;

            for (int index = left; index <= right; index++) {
                sum += nums[index];
            }

            return sum;
        }
    }

    static class NumArray {
        private final int[] prefixSums;

        NumArray(int[] nums) {
            prefixSums = new int[nums.length + 1];

            for (int index = 0; index < nums.length; index++) {
                prefixSums[index + 1] = prefixSums[index] + nums[index];
            }
        }

        int sumRange(int left, int right) {
            return prefixSums[right + 1] - prefixSums[left];
        }
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        BruteForceNumArray bruteForce = new BruteForceNumArray(new int[] {-2, 0, 3, -5, 2, -1});
        check("brute force sums the middle range", bruteForce.sumRange(0, 2), 1);
        check("brute force handles negative values", bruteForce.sumRange(2, 5), -1);

        NumArray solution = new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
        check("sums the first three values", solution.sumRange(0, 2), 1);
        check("sums a range with negatives", solution.sumRange(2, 5), -1);
        check("sums a single element", solution.sumRange(4, 4), 2);
    }
}

/*
 * Brute Force:
 * I keep the original array and scan from left to right for every query.
 *
 * Time Complexity: O(n) per sumRange query, where n is the number of elements
 * in the requested range.
 * Space Complexity: O(1) beyond the input array reference.
 *
 * Optimal Interview Solution:
 * I build prefix sums once so any inclusive range can be answered by subtracting
 * the prefix before the range from the prefix after the range.
 *
 * Time Complexity: O(n) to build the structure and O(1) per sumRange query,
 * where n is the length of the input array.
 * Space Complexity: O(n), because the prefix sum array stores one extra value
 * for each input position boundary.
 */
