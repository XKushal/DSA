import java.util.Arrays;

class LargestPerimeterTriangle {
    public int bruteForceLargestPerimeter(int[] nums) {
        int best = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (canFormTriangle(nums[i], nums[j], nums[k])) {
                        best = Math.max(best, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }

        return best;
    }

    private boolean canFormTriangle(int first, int second, int third) {
        return first + second > third
                && first + third > second
                && second + third > first;
    }

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        return 0;
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
        LargestPerimeterTriangle solution = new LargestPerimeterTriangle();

        check("brute force finds the only triangle", solution.bruteForceLargestPerimeter(values(2, 1, 2)), 5);
        check("brute force rejects impossible sides", solution.bruteForceLargestPerimeter(values(1, 2, 1, 10)), 0);
        check("brute force prefers largest perimeter", solution.bruteForceLargestPerimeter(values(3, 6, 2, 3)), 8);

        check("finds the only triangle", solution.largestPerimeter(values(2, 1, 2)), 5);
        check("rejects impossible sides", solution.largestPerimeter(values(1, 2, 1, 10)), 0);
        check("prefers largest perimeter", solution.largestPerimeter(values(3, 6, 2, 3)), 8);
        check("uses the three largest valid sides", solution.largestPerimeter(values(1, 1, 2, 3, 5)), 0);
    }
}

/*
 * Brute Force:
 * I try every group of three side lengths, keep the valid triangle with the
 * largest perimeter, and return 0 if no group can form a triangle.
 *
 * Time Complexity: O(n^3), because every triplet is checked once.
 * Space Complexity: O(1), because only the current best perimeter is stored.
 *
 * Optimal Interview Solution:
 * I sort the side lengths, then scan from the largest side downward. The first
 * adjacent triple that satisfies the triangle inequality has the largest
 * possible perimeter.
 *
 * Time Complexity: O(n log n), because the array is sorted before one scan.
 * Space Complexity: O(1), ignoring the sorting implementation's internal space.
 */
