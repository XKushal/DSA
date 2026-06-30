class FindPivotIndex {
    public int bruteForcePivotIndex(int[] nums) {
        for (int pivot = 0; pivot < nums.length; pivot++) {
            int leftSum = 0;
            for (int index = 0; index < pivot; index++) {
                leftSum += nums[index];
            }

            int rightSum = 0;
            for (int index = pivot + 1; index < nums.length; index++) {
                rightSum += nums[index];
            }

            if (leftSum == rightSum) {
                return pivot;
            }
        }

        return -1;
    }

    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int number : nums) {
            totalSum += number;
        }

        int leftSum = 0;
        for (int index = 0; index < nums.length; index++) {
            int rightSum = totalSum - leftSum - nums[index];
            if (leftSum == rightSum) {
                return index;
            }
            leftSum += nums[index];
        }

        return -1;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindPivotIndex solution = new FindPivotIndex();

        check("brute force finds middle pivot",
                solution.bruteForcePivotIndex(new int[] {1, 7, 3, 6, 5, 6}), 3);

        check("finds middle pivot", solution.pivotIndex(new int[] {1, 7, 3, 6, 5, 6}), 3);
        check("returns negative one without pivot", solution.pivotIndex(new int[] {1, 2, 3}), -1);
        check("finds first index", solution.pivotIndex(new int[] {2, 1, -1}), 0);
        check("returns leftmost pivot", solution.pivotIndex(new int[] {0, 0, 0}), 0);
        check("handles one element", solution.pivotIndex(new int[] {5}), 0);
    }
}

/*
 * Brute Force:
 * I treat every index as a possible pivot and independently sum the values on
 * its left and right.
 *
 * Time Complexity: O(n^2), because up to n values are summed for each of the n
 * possible pivot indices.
 * Space Complexity: O(1), because only running sums and loop values are stored.
 *
 * Optimal Interview Solution:
 * I calculate the total sum once, then maintain the left sum while scanning.
 * The right sum at each index is the total minus the left sum and pivot value.
 *
 * Time Complexity: O(n), because the array is scanned twice.
 * Space Complexity: O(1), because only the total and left sums are stored.
 */
