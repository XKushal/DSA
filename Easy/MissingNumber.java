class MissingNumber {
    public int bruteForceMissingNumber(int[] nums) {
        for (int expected = 0; expected <= nums.length; expected++) {
            boolean found = false;

            for (int num : nums) {
                if (num == expected) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return expected;
            }
        }

        return -1;
    }

    public int missingNumber(int[] nums) {
        int missing = nums.length;

        for (int index = 0; index < nums.length; index++) {
            missing ^= index;
            missing ^= nums[index];
        }

        return missing;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MissingNumber solution = new MissingNumber();

        check("brute force finds missing middle number", solution.bruteForceMissingNumber(new int[] {3, 0, 1}), 2);

        check("finds missing middle number", solution.missingNumber(new int[] {3, 0, 1}), 2);
        check("finds missing last number", solution.missingNumber(new int[] {0, 1}), 2);
        check("finds missing zero", solution.missingNumber(new int[] {1, 2, 3}), 0);
    }
}

/*
 * Brute Force:
 * I try every value from 0 through n and scan the array to see whether that
 * value appears. The first value that is not found is the missing number.
 *
 * Time Complexity: O(n^2), where n is the length of nums.
 * Space Complexity: O(1), because only loop state is stored.
 *
 * Optimal Interview Solution:
 * I XOR every expected index and every actual number together. Matching values
 * cancel out, leaving only the one missing value.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because only one running XOR value is stored.
 */
