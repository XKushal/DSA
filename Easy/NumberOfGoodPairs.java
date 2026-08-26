class NumberOfGoodPairs {
    public int bruteForceNumIdenticalPairs(int[] nums) {
        int pairs = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    public int numIdenticalPairs(int[] nums) {
        int[] counts = new int[101];
        int pairs = 0;

        for (int num : nums) {
            pairs += counts[num];
            counts[num]++;
        }

        return pairs;
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
        NumberOfGoodPairs solution = new NumberOfGoodPairs();

        check("brute force sample", solution.bruteForceNumIdenticalPairs(nums(1, 2, 3, 1, 1, 3)), 4);
        check("brute force no pairs", solution.bruteForceNumIdenticalPairs(nums(1, 2, 3)), 0);

        check("sample", solution.numIdenticalPairs(nums(1, 2, 3, 1, 1, 3)), 4);
        check("all equal", solution.numIdenticalPairs(nums(1, 1, 1, 1)), 6);
        check("no pairs", solution.numIdenticalPairs(nums(1, 2, 3)), 0);
        check("mixed duplicates", solution.numIdenticalPairs(nums(5, 5, 2, 5, 2)), 4);
    }
}

/*
 * Brute Force:
 * I check every pair of indices and count it when both values are equal.
 *
 * Time Complexity: O(n^2), because each index is compared with the indices
 * after it.
 * Space Complexity: O(1), because only the pair counter is stored.
 *
 * Optimal Interview Solution:
 * I keep the frequency seen for each value. Every time a value appears again,
 * it forms one pair with each previous occurrence.
 *
 * Time Complexity: O(n), because each number is processed once.
 * Space Complexity: O(1), because the frequency array has a fixed size for the
 * input value range.
 */
