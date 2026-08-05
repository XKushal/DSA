class LongestContinuousIncreasingSubsequence {
    public int bruteForceFindLengthOfLCIS(int[] nums) {
        int longest = 0;

        for (int start = 0; start < nums.length; start++) {
            int length = 1;

            for (int end = start + 1; end < nums.length; end++) {
                if (nums[end] <= nums[end - 1]) {
                    break;
                }

                length++;
            }

            longest = Math.max(longest, length);
        }

        return longest;
    }

    public int findLengthOfLCIS(int[] nums) {
        int longest = 0;
        int current = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                current++;
            } else {
                current = 1;
            }

            longest = Math.max(longest, current);
        }

        return longest;
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
        LongestContinuousIncreasingSubsequence solution =
                new LongestContinuousIncreasingSubsequence();

        check("brute force handles mixed runs",
                solution.bruteForceFindLengthOfLCIS(values(1, 3, 5, 4, 7)), 3);
        check("brute force handles equal values",
                solution.bruteForceFindLengthOfLCIS(values(2, 2, 2, 2, 2)), 1);
        check("brute force handles fully increasing array",
                solution.bruteForceFindLengthOfLCIS(values(1, 2, 3, 4)), 4);

        check("handles mixed runs", solution.findLengthOfLCIS(values(1, 3, 5, 4, 7)), 3);
        check("handles equal values", solution.findLengthOfLCIS(values(2, 2, 2, 2, 2)), 1);
        check("handles fully increasing array", solution.findLengthOfLCIS(values(1, 2, 3, 4)), 4);
        check("handles empty array", solution.findLengthOfLCIS(values()), 0);
        check("handles run at end", solution.findLengthOfLCIS(values(5, 1, 2, 3, 4)), 4);
    }
}

/*
 * Brute Force:
 * I start at each index, extend while the neighboring values keep increasing,
 * and keep the longest continuous run found.
 *
 * Time Complexity: O(n^2), because each starting index can scan forward.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I scan once while tracking the current increasing run length. Whenever the
 * order stops increasing, the current run restarts from the present value.
 *
 * Time Complexity: O(n), because each value is inspected once.
 * Space Complexity: O(1), because only the current and best lengths are stored.
 */
