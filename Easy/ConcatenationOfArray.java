class ConcatenationOfArray {
    public int[] bruteForceGetConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            ans[i + nums.length] = nums[i];
        }

        return ans;
    }

    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }

        return ans;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (actual.length != expected.length) {
            throw new AssertionError(name + " expected length " + expected.length + " but got " + actual.length);
        }

        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expected[i]) {
                throw new AssertionError(name + " expected " + expected[i] + " at index " + i
                    + " but got " + actual[i]);
            }
        }
    }

    private static int[] nums(int... values) {
        return values;
    }

    public static void main(String[] args) {
        ConcatenationOfArray solution = new ConcatenationOfArray();

        check("brute force sample", solution.bruteForceGetConcatenation(nums(1, 2, 1)), nums(1, 2, 1, 1, 2, 1));
        check("brute force increasing", solution.bruteForceGetConcatenation(nums(1, 3, 2, 1)),
            nums(1, 3, 2, 1, 1, 3, 2, 1));

        check("sample", solution.getConcatenation(nums(1, 2, 1)), nums(1, 2, 1, 1, 2, 1));
        check("increasing", solution.getConcatenation(nums(1, 3, 2, 1)), nums(1, 3, 2, 1, 1, 3, 2, 1));
        check("single", solution.getConcatenation(nums(7)), nums(7, 7));
    }
}

/*
 * Brute Force:
 * I copy the original array into the first half of the answer, then make a
 * second pass to copy it into the second half.
 *
 * Time Complexity: O(n), because each number is copied twice.
 * Space Complexity: O(n), because the concatenated answer stores 2n values.
 *
 * Optimal Interview Solution:
 * I fill both matching positions in the concatenated answer during the same
 * pass through the input.
 *
 * Time Complexity: O(n), because each number is processed once.
 * Space Complexity: O(n), because the result array is required for the answer.
 */
