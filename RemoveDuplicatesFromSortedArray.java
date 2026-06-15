import java.util.Arrays;

class RemoveDuplicatesFromSortedArray {
    public int bruteForceRemoveDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] unique = new int[nums.length];
        int length = 0;

        unique[length] = nums[0];
        length++;

        for (int index = 1; index < nums.length; index++) {
            if (nums[index] != nums[index - 1]) {
                unique[length] = nums[index];
                length++;
            }
        }

        for (int index = 0; index < length; index++) {
            nums[index] = unique[index];
        }

        return length;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int insertIndex = 1;

        for (int index = 1; index < nums.length; index++) {
            if (nums[index] != nums[index - 1]) {
                nums[insertIndex] = nums[index];
                insertIndex++;
            }
        }

        return insertIndex;
    }

    private static void check(String name, int[] nums, int length, int[] expected) {
        if (length != expected.length) {
            throw new AssertionError(name + " expected length " + expected.length + " but got " + length);
        }

        int[] actual = Arrays.copyOf(nums, length);
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();

        int[] bruteMixed = {1, 1, 2};
        check("brute force removes repeated value", bruteMixed, solution.bruteForceRemoveDuplicates(bruteMixed), new int[] {1, 2});

        int[] bruteLong = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        check("brute force keeps one of each value", bruteLong, solution.bruteForceRemoveDuplicates(bruteLong), new int[] {0, 1, 2, 3, 4});

        int[] mixed = {1, 1, 2};
        check("removes repeated value", mixed, solution.removeDuplicates(mixed), new int[] {1, 2});

        int[] longer = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        check("keeps one of each value", longer, solution.removeDuplicates(longer), new int[] {0, 1, 2, 3, 4});

        int[] single = {7};
        check("keeps single value", single, solution.removeDuplicates(single), new int[] {7});
    }
}

/*
 * Brute Force:
 * I copy the first occurrence of each value into a temporary array, then copy
 * those unique values back into the front of nums.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(n), because the temporary array can store every value.
 *
 * Optimal Interview Solution:
 * Because the array is sorted, duplicates are adjacent. I keep an insert index
 * for the next unique value and overwrite duplicates as I scan from left to right.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because the changes are made in-place.
 */