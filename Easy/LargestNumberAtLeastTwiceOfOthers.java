class LargestNumberAtLeastTwiceOfOthers {
    public int bruteForceDominantIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean isDominant = true;

            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] < 2 * nums[j]) {
                    isDominant = false;
                    break;
                }
            }

            if (isDominant) {
                return i;
            }
        }

        return -1;
    }

    public int dominantIndex(int[] nums) {
        int largestIndex = 0;
        int secondLargest = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[largestIndex]) {
                secondLargest = nums[largestIndex];
                largestIndex = i;
            } else if (nums[i] > secondLargest) {
                secondLargest = nums[i];
            }
        }

        return nums[largestIndex] >= 2 * secondLargest ? largestIndex : -1;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LargestNumberAtLeastTwiceOfOthers solution = new LargestNumberAtLeastTwiceOfOthers();

        check("brute force finds dominant maximum", solution.bruteForceDominantIndex(new int[] {3, 6, 1, 0}), 1);
        check("brute force rejects close maximum", solution.bruteForceDominantIndex(new int[] {1, 2, 3, 4}), -1);
        check("brute force handles single value", solution.bruteForceDominantIndex(new int[] {1}), 0);

        check("finds dominant maximum", solution.dominantIndex(new int[] {3, 6, 1, 0}), 1);
        check("rejects close maximum", solution.dominantIndex(new int[] {1, 2, 3, 4}), -1);
        check("handles maximum at start", solution.dominantIndex(new int[] {10, 2, 5, 1}), 0);
        check("handles single value", solution.dominantIndex(new int[] {1}), 0);
    }
}

/*
 * Brute Force:
 * I test every index as the possible dominant number and compare it against
 * every other value to see whether it is at least twice as large.
 *
 * Time Complexity: O(n^2), because each value can be compared with every other
 * value.
 * Space Complexity: O(1), because only loop counters and a flag are stored.
 *
 * Optimal Interview Solution:
 * I track the largest value's index and the second-largest value in one pass,
 * then only compare those two values to decide whether the largest dominates.
 *
 * Time Complexity: O(n), because the array is scanned once.
 * Space Complexity: O(1), because only the largest index and second-largest
 * value are stored.
 */
