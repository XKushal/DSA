import java.util.Arrays;

class ThirdMaximumNumber {
    public int bruteForceThirdMax(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        int distinctCount = 0;
        int previous = 0;
        boolean hasPrevious = false;

        for (int index = sorted.length - 1; index >= 0; index--) {
            int current = sorted[index];

            if (!hasPrevious || current != previous) {
                distinctCount++;
                previous = current;
                hasPrevious = true;
            }

            if (distinctCount == 3) {
                return current;
            }
        }

        return sorted[sorted.length - 1];
    }

    public int thirdMax(int[] nums) {
        Long first = null;
        Long second = null;
        Long third = null;

        for (int num : nums) {
            long current = num;

            if ((first != null && current == first)
                    || (second != null && current == second)
                    || (third != null && current == third)) {
                continue;
            }

            if (first == null || current > first) {
                third = second;
                second = first;
                first = current;
            } else if (second == null || current > second) {
                third = second;
                second = current;
            } else if (third == null || current > third) {
                third = current;
            }
        }

        return (third == null ? first : third).intValue();
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ThirdMaximumNumber solution = new ThirdMaximumNumber();

        check("brute force finds third maximum", solution.bruteForceThirdMax(new int[] {3, 2, 1}), 1);
        check("brute force returns maximum when fewer than three distinct",
                solution.bruteForceThirdMax(new int[] {1, 2}), 2);

        check("finds third maximum", solution.thirdMax(new int[] {3, 2, 1}), 1);
        check("returns maximum when fewer than three distinct", solution.thirdMax(new int[] {1, 2}), 2);
        check("ignores duplicate maximums", solution.thirdMax(new int[] {2, 2, 3, 1}), 1);
        check("handles negative values", solution.thirdMax(new int[] {-1, -2, -3, -4}), -3);
        check("handles integer minimum", solution.thirdMax(new int[] {1, 2, Integer.MIN_VALUE}), Integer.MIN_VALUE);
    }
}

/*
 * Brute Force:
 * I copy and sort the numbers, then walk from largest to smallest while
 * counting distinct values until the third maximum is found.
 *
 * Time Complexity: O(n log n), where n is the number of values, because sorting
 * dominates the work.
 * Space Complexity: O(n), because the copied array stores all values.
 *
 * Optimal Interview Solution:
 * I scan the array once while tracking the largest, second largest, and third
 * largest distinct values seen so far.
 *
 * Time Complexity: O(n), because each number is processed once.
 * Space Complexity: O(1), because only three maximum trackers are stored.
 */
