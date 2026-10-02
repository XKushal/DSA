class CountNumberOfPairsWithAbsoluteDifferenceK {
    public int bruteForceCountKDifference(int[] nums, int k) {
        int pairs = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    public int countKDifference(int[] nums, int k) {
        int[] frequency = new int[101];
        int pairs = 0;

        for (int num : nums) {
            if (k == 0) {
                pairs += frequency[num];
            } else if (num - k >= 1) {
                pairs += frequency[num - k];
            }
            if (k != 0 && num + k <= 100) {
                pairs += frequency[num + k];
            }

            frequency[num]++;
        }

        return pairs;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountNumberOfPairsWithAbsoluteDifferenceK solution = new CountNumberOfPairsWithAbsoluteDifferenceK();

        check("brute force sample one", solution.bruteForceCountKDifference(new int[] {1, 2, 2, 1}, 1), 4);
        check("brute force sample two", solution.bruteForceCountKDifference(new int[] {1, 3}, 3), 0);

        check("sample one", solution.countKDifference(new int[] {1, 2, 2, 1}, 1), 4);
        check("sample two", solution.countKDifference(new int[] {1, 3}, 3), 0);
        check("sample three", solution.countKDifference(new int[] {3, 2, 1, 5, 4}, 2), 3);
        check("duplicate values", solution.countKDifference(new int[] {4, 4, 4, 4}, 0), 6);
    }
}

/*
 * Brute Force:
 * I compare every pair of indices and count it when the absolute difference is k.
 *
 * Time Complexity: O(n^2), where n is the length of nums.
 * Space Complexity: O(1), because only the pair counter is stored.
 *
 * Optimal Interview Solution:
 * I count values already seen. For each number, the valid earlier partners are
 * exactly num - k and num + k, so those frequencies can be added before storing
 * the current number.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because LeetCode bounds nums[i] to 1 through 100.
 */
