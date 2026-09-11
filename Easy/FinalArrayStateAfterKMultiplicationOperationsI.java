import java.util.Arrays;
import java.util.PriorityQueue;

class FinalArrayStateAfterKMultiplicationOperationsI {
    public int[] bruteForceGetFinalState(int[] nums, int k, int multiplier) {
        int[] result = Arrays.copyOf(nums, nums.length);

        for (int operation = 0; operation < k; operation++) {
            int minimumIndex = 0;

            for (int i = 1; i < result.length; i++) {
                if (result[i] < result[minimumIndex]) {
                    minimumIndex = i;
                }
            }

            result[minimumIndex] *= multiplier;
        }

        return result;
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int[] result = Arrays.copyOf(nums, nums.length);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((first, second) -> {
            if (first[0] != second[0]) {
                return Integer.compare(first[0], second[0]);
            }

            return Integer.compare(first[1], second[1]);
        });

        for (int i = 0; i < result.length; i++) {
            minHeap.offer(new int[] {result[i], i});
        }

        for (int operation = 0; operation < k; operation++) {
            int[] current = minHeap.poll();
            int index = current[1];
            result[index] = current[0] * multiplier;
            minHeap.offer(new int[] {result[index], index});
        }

        return result;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        FinalArrayStateAfterKMultiplicationOperationsI solution =
                new FinalArrayStateAfterKMultiplicationOperationsI();

        check("brute force sample", solution.bruteForceGetFinalState(
                new int[] {2, 1, 3, 5, 6}, 5, 2), new int[] {8, 4, 6, 5, 6});
        check("brute force repeated ties", solution.bruteForceGetFinalState(
                new int[] {1, 1, 1}, 2, 3), new int[] {3, 3, 1});

        check("sample", solution.getFinalState(new int[] {2, 1, 3, 5, 6}, 5, 2),
                new int[] {8, 4, 6, 5, 6});
        check("single operation", solution.getFinalState(new int[] {1, 2}, 1, 4),
                new int[] {4, 2});
        check("tie uses first index", solution.getFinalState(new int[] {1, 1, 2}, 1, 5),
                new int[] {5, 1, 2});
        check("no operations", solution.getFinalState(new int[] {4, 3, 2}, 0, 10),
                new int[] {4, 3, 2});
    }
}

/*
 * Brute Force:
 * I copy the array, then repeat k operations by scanning the whole array for
 * the first minimum value and multiplying that position.
 *
 * Time Complexity: O(k * n), where n is the number of values.
 * Space Complexity: O(n), because the returned array is copied.
 *
 * Optimal Interview Solution:
 * I keep each value with its index in a min-heap ordered by value first and
 * index second, so each operation updates the current first minimum directly.
 *
 * Time Complexity: O(n + k log n), where n is the number of values.
 * Space Complexity: O(n), because the heap and returned array store n values.
 */
