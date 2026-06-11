import java.util.Arrays;

class MoveZeroes {
    public int[] bruteForceMoveZeroes(int[] nums) {
        int[] arranged = new int[nums.length];
        int insertIndex = 0;

        for (int num : nums) {
            if (num != 0) {
                arranged[insertIndex] = num;
                insertIndex++;
            }
        }

        return arranged;
    }

    public void moveZeroes(int[] nums) {
        int insertIndex = 0;

        for (int num : nums) {
            if (num != 0) {
                nums[insertIndex] = num;
                insertIndex++;
            }
        }

        while (insertIndex < nums.length) {
            nums[insertIndex] = 0;
            insertIndex++;
        }
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();

        check(
                "brute force moves zeroes behind non-zero values",
                solution.bruteForceMoveZeroes(new int[] {0, 1, 0, 3, 12}),
                new int[] {1, 3, 12, 0, 0});
        check(
                "brute force keeps an array without zeroes the same",
                solution.bruteForceMoveZeroes(new int[] {1, 2, 3}),
                new int[] {1, 2, 3});

        int[] mixed = {0, 1, 0, 3, 12};
        solution.moveZeroes(mixed);
        check("moves zeroes behind non-zero values", mixed, new int[] {1, 3, 12, 0, 0});

        int[] noZeroes = {1, 2, 3};
        solution.moveZeroes(noZeroes);
        check("keeps an array without zeroes the same", noZeroes, new int[] {1, 2, 3});

        int[] allZeroes = {0, 0};
        solution.moveZeroes(allZeroes);
        check("keeps all zeroes in place", allZeroes, new int[] {0, 0});
    }
}

/*
 * Brute Force:
 * I copy every non-zero value into a new array in order. The remaining indexes
 * stay as zero because Java initializes int arrays with zero values.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(n), because a second array is created.
 *
 * Optimal Interview Solution:
 * I keep one pointer for the next position where a non-zero value should go.
 * After copying all non-zero values forward in their original order, I fill the
 * rest of the array with zeroes.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because the changes are made in-place.
 */
