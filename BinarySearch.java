class BinarySearch {
    public int bruteForceSearch(int[] nums, int target) {
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == target) {
                return index;
            }
        }

        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();

        check("brute force finds an existing target", solution.bruteForceSearch(new int[] {-1, 0, 3, 5, 9, 12}, 9), 4);
        check("brute force returns negative one when target is missing", solution.bruteForceSearch(new int[] {-1, 0, 3, 5, 9, 12}, 2), -1);

        check("finds an existing target", solution.search(new int[] {-1, 0, 3, 5, 9, 12}, 9), 4);
        check("returns negative one when target is missing", solution.search(new int[] {-1, 0, 3, 5, 9, 12}, 2), -1);
        check("finds target at the first index", solution.search(new int[] {5}, 5), 0);
    }
}

/*
 * Brute Force:
 * I check each value from left to right until I find the target. If the scan
 * finishes without a match, the target is not in the array.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because no extra data structure is needed.
 *
 * Optimal Interview Solution:
 * Because the array is sorted, I keep a left and right boundary around the
 * remaining search window. Each comparison removes half of the remaining values
 * until the target is found or the window becomes empty.
 *
 * Time Complexity: O(log n), where n is the length of nums.
 * Space Complexity: O(1), because only index variables are used.
 */
