class BuildArrayFromPermutation {
    public int[] bruteForceBuildArray(int[] nums) {
        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }

    public int[] buildArray(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            nums[i] += n * (nums[nums[i]] % n);
        }

        for (int i = 0; i < n; i++) {
            nums[i] /= n;
        }

        return nums;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (actual.length != expected.length) {
            throw new AssertionError(name + " length expected " + expected.length + " but got " + actual.length);
        }

        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expected[i]) {
                throw new AssertionError(name + " index " + i + " expected " + expected[i] + " but got " + actual[i]);
            }
        }
    }

    public static void main(String[] args) {
        BuildArrayFromPermutation solution = new BuildArrayFromPermutation();

        check("brute force sample", solution.bruteForceBuildArray(
            new int[] {0, 2, 1, 5, 3, 4}
        ), new int[] {0, 1, 2, 4, 5, 3});
        check("brute force reordered", solution.bruteForceBuildArray(
            new int[] {5, 0, 1, 2, 3, 4}
        ), new int[] {4, 5, 0, 1, 2, 3});

        check("sample", solution.buildArray(
            new int[] {0, 2, 1, 5, 3, 4}
        ), new int[] {0, 1, 2, 4, 5, 3});
        check("reordered", solution.buildArray(
            new int[] {5, 0, 1, 2, 3, 4}
        ), new int[] {4, 5, 0, 1, 2, 3});
        check("single", solution.buildArray(new int[] {0}), new int[] {0});
    }
}

/*
 * Brute Force:
 * I create a separate result array and place nums[nums[i]] at every index.
 *
 * Time Complexity: O(n), because each index is processed once.
 * Space Complexity: O(n), because the result array stores every answer.
 *
 * Optimal Interview Solution:
 * I encode the old and new values in each array slot at the same time. Since
 * nums is a zero-indexed permutation, modulo n recovers the old value while
 * the divided value becomes the answer.
 *
 * Time Complexity: O(n), because the array is scanned twice.
 * Space Complexity: O(1), because the transformation reuses the input array.
 */
