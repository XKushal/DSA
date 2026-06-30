import java.util.Arrays;

class SquaresOfASortedArray {
    public int[] bruteForceSortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        for (int index = 0; index < nums.length; index++) {
            squares[index] = nums[index] * nums[index];
        }
        Arrays.sort(squares);
        return squares;
    }

    public int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        for (int writeIndex = nums.length - 1; writeIndex >= 0; writeIndex--) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                squares[writeIndex] = leftSquare;
                left++;
            } else {
                squares[writeIndex] = rightSquare;
                right--;
            }
        }

        return squares;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        SquaresOfASortedArray solution = new SquaresOfASortedArray();

        check("brute force squares and sorts",
                solution.bruteForceSortedSquares(new int[] {-4, -1, 0, 3, 10}),
                new int[] {0, 1, 9, 16, 100});

        check("sorts mixed values", solution.sortedSquares(new int[] {-4, -1, 0, 3, 10}),
                new int[] {0, 1, 9, 16, 100});
        check("handles larger negative squares", solution.sortedSquares(new int[] {-7, -3, 2, 3, 11}),
                new int[] {4, 9, 9, 49, 121});
        check("handles only negatives", solution.sortedSquares(new int[] {-5, -3, -1}),
                new int[] {1, 9, 25});
        check("handles only nonnegative values", solution.sortedSquares(new int[] {0, 2, 4}),
                new int[] {0, 4, 16});
        check("handles one value", solution.sortedSquares(new int[] {-2}), new int[] {4});
    }
}

/*
 * Brute Force:
 * I square every value, then sort the resulting array into nondecreasing order.
 *
 * Time Complexity: O(n log n), because sorting dominates the linear squaring
 * pass.
 * Space Complexity: O(n) for the required result array.
 *
 * Optimal Interview Solution:
 * I compare the squares at both ends of the sorted input and write the larger
 * one into the result from right to left.
 *
 * Time Complexity: O(n), because each input value is processed once.
 * Space Complexity: O(n) for the required result array and O(1) auxiliary
 * space beyond it.
 */
