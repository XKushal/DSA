class SmallestRangeI {
    public int bruteForceSmallestRangeI(int[] nums, int k) {
        return bruteForce(nums, k, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private int bruteForce(int[] nums, int k, int index, int currentMin, int currentMax) {
        if (index == nums.length) {
            return currentMax - currentMin;
        }

        int best = Integer.MAX_VALUE;

        for (int adjustment = -k; adjustment <= k; adjustment++) {
            int nextValue = nums[index] + adjustment;
            best = Math.min(best, bruteForce(nums, k, index + 1,
                    Math.min(currentMin, nextValue), Math.max(currentMax, nextValue)));
        }

        return best;
    }

    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return Math.max(0, max - min - 2 * k);
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] values(int... nums) {
        return nums;
    }

    public static void main(String[] args) {
        SmallestRangeI solution = new SmallestRangeI();

        check("brute force single value", solution.bruteForceSmallestRangeI(values(1), 0), 0);
        check("brute force closes small range", solution.bruteForceSmallestRangeI(values(0, 3), 1), 1);
        check("brute force can overlap every value", solution.bruteForceSmallestRangeI(values(1, 3, 6), 3), 0);

        check("single value", solution.smallestRangeI(values(1), 0), 0);
        check("closes small range", solution.smallestRangeI(values(0, 3), 1), 1);
        check("can overlap every value", solution.smallestRangeI(values(1, 3, 6), 3), 0);
        check("leaves uncovered gap", solution.smallestRangeI(values(1, 10), 2), 5);
        check("exactly closes gap", solution.smallestRangeI(values(2, 7, 11), 3), 3);
    }
}

/*
 * Brute Force:
 * I try every integer adjustment from -k to k for each value, then keep the
 * smallest possible difference between the adjusted maximum and minimum.
 *
 * Time Complexity: O((2k + 1)^n), because every adjustment combination is
 * explored.
 * Space Complexity: O(n), because the recursion depth can include every value.
 *
 * Optimal Interview Solution:
 * Each number can move at most k toward the center, so only the original
 * minimum and maximum matter. If their gap is at most 2k the ranges overlap;
 * otherwise the leftover distance is the answer.
 *
 * Time Complexity: O(n), because the array is scanned once.
 * Space Complexity: O(1), because only the minimum and maximum are stored.
 */
