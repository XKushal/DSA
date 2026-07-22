import java.util.Arrays;

class SortArrayByParity {
    public int[] bruteForceSortArrayByParity(int[] nums) {
        int[] result = new int[nums.length];
        int writeIndex = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                result[writeIndex] = num;
                writeIndex++;
            }
        }

        for (int num : nums) {
            if (num % 2 != 0) {
                result[writeIndex] = num;
                writeIndex++;
            }
        }

        return result;
    }

    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left] % 2 == 0) {
                left++;
            } else {
                swap(nums, left, right);
                right--;
            }
        }

        return nums;
    }

    private void swap(int[] nums, int first, int second) {
        int temporary = nums[first];
        nums[first] = nums[second];
        nums[second] = temporary;
    }

    private static void checkExact(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    private static void checkParitySorted(String name, int[] actual, int[] original) {
        if (!hasSameValues(actual, original)) {
            throw new AssertionError(name + " changed values from " + Arrays.toString(original)
                    + " to " + Arrays.toString(actual));
        }

        boolean seenOdd = false;
        for (int num : actual) {
            if (num % 2 != 0) {
                seenOdd = true;
            } else if (seenOdd) {
                throw new AssertionError(name + " placed even value after an odd value: "
                        + Arrays.toString(actual));
            }
        }
    }

    private static boolean hasSameValues(int[] actual, int[] expected) {
        int[] actualCopy = Arrays.copyOf(actual, actual.length);
        int[] expectedCopy = Arrays.copyOf(expected, expected.length);

        Arrays.sort(actualCopy);
        Arrays.sort(expectedCopy);

        return Arrays.equals(actualCopy, expectedCopy);
    }

    public static void main(String[] args) {
        SortArrayByParity solution = new SortArrayByParity();

        checkExact("brute force moves evens before odds",
                solution.bruteForceSortArrayByParity(new int[] {3, 1, 2, 4}), new int[] {2, 4, 3, 1});
        checkExact("brute force handles all odds",
                solution.bruteForceSortArrayByParity(new int[] {5, 7, 1}), new int[] {5, 7, 1});

        int[] mixed = {3, 1, 2, 4};
        checkParitySorted("sorts mixed parity values",
                solution.sortArrayByParity(Arrays.copyOf(mixed, mixed.length)), mixed);

        int[] alreadySorted = {2, 4, 6, 1, 3};
        checkParitySorted("keeps valid parity partition",
                solution.sortArrayByParity(Arrays.copyOf(alreadySorted, alreadySorted.length)), alreadySorted);

        int[] allEven = {8, 2, 6};
        checkParitySorted("handles all even values", solution.sortArrayByParity(Arrays.copyOf(allEven, allEven.length)),
                allEven);

        int[] empty = {};
        checkParitySorted("handles empty input", solution.sortArrayByParity(Arrays.copyOf(empty, empty.length)), empty);
    }
}

/*
 * Brute Force:
 * I copy the even values into a new array first, then make a second pass to copy
 * the odd values after them.
 *
 * Time Complexity: O(n), where n is nums.length.
 * Space Complexity: O(n), because the result array stores every value.
 *
 * Optimal Interview Solution:
 * I use two pointers and swap odd values from the left side with values from the
 * right side until every even value appears before every odd value.
 *
 * Time Complexity: O(n), where n is nums.length.
 * Space Complexity: O(1), because the rearrangement happens in place.
 */
