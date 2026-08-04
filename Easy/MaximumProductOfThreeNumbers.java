class MaximumProductOfThreeNumbers {
    public int bruteForceMaximumProduct(int[] nums) {
        int maximum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    maximum = Math.max(maximum, nums[i] * nums[j] * nums[k]);
                }
            }
        }

        return maximum;
    }

    public int maximumProduct(int[] nums) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int thirdLargest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > largest) {
                thirdLargest = secondLargest;
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest) {
                thirdLargest = secondLargest;
                secondLargest = num;
            } else if (num > thirdLargest) {
                thirdLargest = num;
            }

            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest) {
                secondSmallest = num;
            }
        }

        int threeLargest = largest * secondLargest * thirdLargest;
        int twoSmallestAndLargest = smallest * secondSmallest * largest;

        return Math.max(threeLargest, twoSmallestAndLargest);
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
        MaximumProductOfThreeNumbers solution = new MaximumProductOfThreeNumbers();

        check("brute force handles increasing positives",
                solution.bruteForceMaximumProduct(values(1, 2, 3)), 6);
        check("brute force handles extra positive value",
                solution.bruteForceMaximumProduct(values(1, 2, 3, 4)), 24);
        check("brute force handles negative pair",
                solution.bruteForceMaximumProduct(values(-10, -10, 5, 2)), 500);

        check("handles increasing positives", solution.maximumProduct(values(1, 2, 3)), 6);
        check("handles extra positive value", solution.maximumProduct(values(1, 2, 3, 4)), 24);
        check("handles negative pair", solution.maximumProduct(values(-10, -10, 5, 2)), 500);
        check("handles all negative values", solution.maximumProduct(values(-5, -4, -3, -2)), -24);
        check("handles zero with negatives", solution.maximumProduct(values(-3, -2, -1, 0)), 0);
    }
}

/*
 * Brute Force:
 * I try every group of three numbers and keep the largest product found.
 *
 * Time Complexity: O(n^3), because every triple of values can be checked.
 * Space Complexity: O(1), because only the running maximum is stored.
 *
 * Optimal Interview Solution:
 * I track the three largest values and the two smallest values while scanning
 * once. The answer is either the three largest values multiplied together or
 * the two smallest values with the largest value.
 *
 * Time Complexity: O(n), because every value is inspected once.
 * Space Complexity: O(1), because only five tracked values are stored.
 */
