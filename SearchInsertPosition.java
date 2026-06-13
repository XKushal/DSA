class SearchInsertPosition {
    public int bruteForceSearchInsert(int[] nums, int target) {
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] >= target) {
                return index;
            }
        }

        return nums.length;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SearchInsertPosition solution = new SearchInsertPosition();

        check("brute force finds existing target", solution.bruteForceSearchInsert(new int[] {1, 3, 5, 6}, 5), 2);
        check("brute force finds middle insert point", solution.bruteForceSearchInsert(new int[] {1, 3, 5, 6}, 2), 1);

        check("finds existing target", solution.searchInsert(new int[] {1, 3, 5, 6}, 5), 2);
        check("finds middle insert point", solution.searchInsert(new int[] {1, 3, 5, 6}, 2), 1);
        check("finds end insert point", solution.searchInsert(new int[] {1, 3, 5, 6}, 7), 4);
        check("finds beginning insert point", solution.searchInsert(new int[] {1, 3, 5, 6}, 0), 0);
    }
}

/*
 * Brute Force:
 * I scan from left to right and return the first index whose value is greater
 * than or equal to the target. If no such value exists, the target belongs at
 * the end of the array.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because no extra data structure is used.
 *
 * Optimal Interview Solution:
 * Because the array is sorted, I use binary search to find the leftmost index
 * where the value is greater than or equal to the target.
 *
 * Time Complexity: O(log n), where n is the length of nums.
 * Space Complexity: O(1), because only index variables are used.
 */
