class KthMissingPositiveNumber {
    public int bruteForceFindKthPositive(int[] arr, int k) {
        int index = 0;
        int current = 1;

        while (true) {
            if (index < arr.length && arr[index] == current) {
                index++;
            } else {
                k--;

                if (k == 0) {
                    return current;
                }
            }

            current++;
        }
    }

    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int missingBeforeMid = arr[mid] - mid - 1;

            if (missingBeforeMid < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left + k;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] values(int... nums) {
        return nums;
    }

    public static void main(String[] args) {
        KthMissingPositiveNumber solution = new KthMissingPositiveNumber();

        check("brute force finds gap inside array", solution.bruteForceFindKthPositive(values(2, 3, 4, 7, 11), 5), 9);
        check("brute force continues past array", solution.bruteForceFindKthPositive(values(1, 2, 3, 4), 2), 6);
        check("brute force handles first missing", solution.bruteForceFindKthPositive(values(3, 5, 8), 1), 1);

        check("finds gap inside array", solution.findKthPositive(values(2, 3, 4, 7, 11), 5), 9);
        check("continues past array", solution.findKthPositive(values(1, 2, 3, 4), 2), 6);
        check("handles first missing", solution.findKthPositive(values(3, 5, 8), 1), 1);
        check("skips several early values", solution.findKthPositive(values(5, 6, 7), 4), 4);
    }
}

/*
 * Brute Force:
 * I count upward from 1, advancing through the sorted array when a value is
 * present and decrementing k whenever the current value is missing.
 *
 * Time Complexity: O(n + k), because the scan advances through the array and
 * the missing values until the kth missing number is reached.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I binary search the first index where the count of missing positive numbers
 * before arr[index] is at least k. After the search, left is the number of
 * array values before the answer, so the kth missing value is left + k.
 *
 * Time Complexity: O(log n), because binary search halves the array each step.
 * Space Complexity: O(1), because the search uses a fixed number of variables.
 */
